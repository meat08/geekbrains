package ru.geekbrains.java2.client.controller;

import ru.geekbrains.java2.client.model.NetworkService;
import ru.geekbrains.java2.client.view.AuthDialog;
import ru.geekbrains.java2.client.view.ClientChat;
import ru.geekbrains.java2.clientserver.Command;

import java.io.IOException;
import java.util.List;

public class ClientController {
    private final NetworkService networkService;
    private final ClientChat clientChat;
    private final AuthDialog authDialog;
    private String nickname;

    public ClientController(String serverName, int serverPort) {
        this.networkService = new NetworkService(serverName, serverPort);
        this.authDialog = new AuthDialog(this);
        this.clientChat = new ClientChat(this);
    }

    public void runApplication() throws IOException {
        connectToServer();
        runAuthProcess();
    }

    private void runAuthProcess() {
        networkService.setSuccessfulAuthEvent(nickname -> {
            ClientController.this.setUserName(nickname);
            clientChat.setTitle("Сетевой чат. Вы вошли как: " + nickname);
            ClientController.this.openChat();
        });
        authDialog.setVisible(true);
    }

    private void openChat() {
        authDialog.dispose();
        networkService.setMessageHandler(clientChat::appendMessage);
        clientChat.setVisible(true);
    }

    private void setUserName(String nickname) {
        this.nickname = nickname;
    }

    private void connectToServer() throws IOException {
        try {
            networkService.connect(this);
            authDialog.timer.start();
        } catch (IOException e) {
            clientChat.showError("Ошибка подключения к серверу.");
            throw e;
        }
    }

    public void disconnectFromServer() {
        networkService.close();
        System.exit(0);
    }

    public void sendAuthMessage(String login, String password) throws IOException {
        networkService.sendCommand(Command.authCommand(login, password));
    }

    public void sendMessageToAllUsers(String message) {
        try {
            networkService.sendCommand(Command.broadcastMessageCommand(message));
        } catch (IOException e) {
            clientChat.showError("Ошибка отправки сообщения.");
            e.printStackTrace();
        }
    }

    public void shutdown() {
        networkService.close();
    }

    public String getUsername() {
        return nickname;
    }

    public void sendPrivateMessage(String username, String message) {
        try {
            networkService.sendCommand(Command.privateMessageCommand(username, message));
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    public void showErrorMessage(String errorMessage) {
        if (clientChat.isActive()) {
            clientChat.showError(errorMessage);
        }
        else if (authDialog.isActive()) {
            authDialog.showError(errorMessage);
        }
        System.err.println(errorMessage);
    }


    public void updateUserList(List<String> users){
        users.remove(nickname);
        users.add(0, "All");
        clientChat.updateUsers(users);
    }

    public void stopTimer() {
        authDialog.timer.stop();
    }
}
