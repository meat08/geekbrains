package ru.geekbrains.java2.client.model;

import ru.geekbrains.java2.client.controller.AuthEvent;
import ru.geekbrains.java2.client.controller.ClientController;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class NetworkService {
    private final String host;
    private final int port;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Consumer<String> messageHandler;
    private AuthEvent successfulAuthEvent;
    private ClientController controller;
    private String nickname;
    private String[] userList;

    public NetworkService(String serverName, int serverPort) {
        this.host = serverName;
        this.port = serverPort;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        runReadThread();
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    private void runReadThread() {
        new Thread(() -> {
            while (true) {
                try {
                    String message = in.readUTF();
                    if(message.startsWith("/auth")) {
                        String[] messageParts = message.split("\\s+", 2);
                        nickname = messageParts[1];
                        successfulAuthEvent.authIsSuccessful(nickname);
                    }
                    else if (message.startsWith("/error")) {
                        JOptionPane.showMessageDialog(null, "Неверный логин или пароль");
                    }
                    else if (message.startsWith("/duplicate")) {
                        JOptionPane.showMessageDialog(null, "Пользователь с таким логином уже вошел в чат.");
                    }
                    else if (message.startsWith("/list")) {
                        String[] messageParts = message.split("\\s+", 2);
                        userList = messageParts[1].split("\\s+");
                        controller.updateUserList(userList);
                    }
                    else if (messageHandler != null) {
                        messageHandler.accept(message);
                    }
                } catch (IOException e) {
                    System.out.println("Поток чтения прерван.");
                    JOptionPane.showMessageDialog(null, "Сервер недоступен. Программа будет закрыта.");
                    System.exit(0);
                    return;
                }
            }
        }).start();
    }

    public void sendAuthMessage(String login, String password) throws IOException {
        out.writeUTF(String.format("/auth %s %s", login, password));
    }

    public void setSuccessfulAuthEvent(AuthEvent successfulAuthEvent) {
        this.successfulAuthEvent = successfulAuthEvent;
    }
    public void setMessageHandler(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}