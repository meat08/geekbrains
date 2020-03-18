package ru.geekbrains.java2.client.controller;

import ru.geekbrains.java2.client.model.NetworkService;
import ru.geekbrains.java2.client.view.AuthDialog;
import ru.geekbrains.java2.client.view.ClientChat;

import javax.swing.*;
import java.io.IOException;

public class ClientController {
    private final NetworkService networkService;
    private final ClientChat clientChat;
    private final AuthDialog authDialog;
    private String nickname;

    public ClientController(String serverName, int serverPort) {
        this.networkService = new NetworkService(serverName, serverPort);
        networkService.setController(this);
        this.authDialog = new AuthDialog(this);
        this.clientChat = new ClientChat(this);
    }

    public void runApplication() throws IOException {
        connectToServer();
        runAuthProcess();
    }

    private void connectToServer() throws IOException {
        try {
            networkService.connect();
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу.");
            JOptionPane.showMessageDialog(null, "Ошибка подключения к серверу.");
            throw e;
        }
    }

    private void runAuthProcess() {
        networkService.setSuccessfulAuthEvent(nickname -> {
            setUserName(nickname);
            openChat();
        });
        authDialog.setVisible(true);
    }

    public void updateUserList(String[] userList){
        if(userList.length > 0) clientChat.setUsersList(userList);
    }

    private void openChat() {
        authDialog.dispose();
        networkService.setMessageHandler(clientChat::appendMessage);
        clientChat.setTitle("Сетевой чат. Вы вошли как: " + nickname);
        clientChat.setVisible(true);
    }

    private void setUserName(String nickname) {
        this.nickname = nickname;
    }

    public void sendAuthMessage(String login, String password) throws IOException {
        networkService.sendAuthMessage(login, password);
    }

    public void shutdown() {
        networkService.close();
    }

    public String getUsername() {
        return nickname;
    }

    public void sendMessage(String message) {
        try {
            networkService.sendMessage(message);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения.");
            e.printStackTrace();
        }
    }
}