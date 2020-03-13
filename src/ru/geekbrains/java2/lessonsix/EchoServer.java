package ru.geekbrains.java2.lessonsix;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EchoServer {
    private static int clientNumber = 0;
    private DataInputStream inputStream;
    private Socket socket;
    private String sender;
    private static Map<Integer, Socket> clientSocket = new HashMap<>();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public EchoServer(Socket socket, String sender) throws IOException {
        this.socket = socket;
        this.sender = sender;
        inputStream = new DataInputStream(socket.getInputStream());
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Сервер запущен. Ожидаем клиентов.");

            new Thread(() -> messageSendBroadcast(clientSocket)).start();

            while (true) {
                clientSocket.put(clientNumber, serverSocket.accept());
                System.out.println("Клиент " + clientNumber + " подключен.");
                EchoServer client = new EchoServer(clientSocket.get(clientNumber), "Клиент " + clientNumber);
                new Thread(client::messageGet).start();

                clientNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void messageGet() {
        while (true) {
            try {
                String message = inputStream.readUTF();
                System.out.println(sender + " написал: " + message);
            } catch (IOException e) {
                removeClient(socket);
                //e.printStackTrace();
                break;
            }
        }
    }

    public static void messageSendBroadcast(Map<Integer, Socket> clients) {
        while (true) {
            try {
                String message = reader.readLine();
                if(message.equalsIgnoreCase("/exit")) System.exit(0);
                if(clients.size() != 0 & !message.trim().isEmpty()) {
                    for(Map.Entry<Integer, Socket> client : clients.entrySet()) {
                        DataOutputStream out = new DataOutputStream(client.getValue().getOutputStream());
                        out.writeUTF(message);
                    }
                    System.out.println("Сообщение отправлено.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void removeClient(Socket socket) {
        for(Map.Entry<Integer, Socket> client : clientSocket.entrySet()) {
            if (client.getValue().equals(socket)) {
                clientSocket.remove(client.getKey());
                System.out.println("Клиент " + client.getKey() + " отключен.");
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
