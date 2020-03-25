package ru.geekbrains.java2.server.client;

import ru.geekbrains.java2.server.NetworkServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final NetworkServer networkServer;
    private final Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;

    public ClientHandler(NetworkServer networkServer, Socket clientSocket) {
        this.networkServer = networkServer;
        this.clientSocket = clientSocket;
    }

    public void run() {
        doHandle(clientSocket);
    }

    private void doHandle(Socket socket) {
        try {
            in = new DataInputStream((socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    System.out.println("Соединение с клиентом " + nickname + " закрыто.");
                } finally {
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void authentication() throws IOException {
        while (true) {
            String message = in.readUTF();
            boolean duplicate = false;
            if(message.startsWith("/auth")) {
                String[] messageParts = message.split("\\s+", 3);
                String login = messageParts[1];
                String password = messageParts[2];
                String username = networkServer.getAuthService().getUsernameByLoginAndPassword(login, password);
                for (ClientHandler client : networkServer.getClients()) {
                    if (client.getNickname().equals(username)) {
                        duplicate = true;
                        break;
                    }
                }
                if(username == null) {
                    sendMessage("/error");
                    System.out.println("Ошибка авторизации");
                } else if (duplicate) {
                    sendMessage("/duplicate");
                } else {
                    nickname = username;
                    networkServer.broadcastMessage(nickname + " вошел в чат.", this);
                    sendMessage("/auth " + nickname);
                    networkServer.subscribe(this);
                    break;
                }
            }
        }
    }

    private void readMessages() throws IOException {
        while(true) {
            String message = in.readUTF();
            System.out.printf("Сообщение от %s: %s%n", nickname, message);
            if("/end".equalsIgnoreCase(message)) return;
            if(message.startsWith("/w")) {
                String[] parts = message.split("\\s+", 3);
                networkServer.privateMessage(parts[2], parts[1]);
            }
            else networkServer.broadcastMessage(nickname + ": " + message, this);
        }
    }

    private void closeConnection() throws IOException {
        networkServer.unsubscribe(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {return nickname;}

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }
}