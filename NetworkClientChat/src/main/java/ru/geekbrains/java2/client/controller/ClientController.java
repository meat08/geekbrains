package ru.geekbrains.java2.client.controller;

import ru.geekbrains.java2.client.model.NetworkService;
import ru.geekbrains.java2.client.view.AuthDialog;
import ru.geekbrains.java2.client.view.ClientChat;
import ru.geekbrains.java2.clientserver.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private final NetworkService networkService;
    private final ClientChat clientChat;
    private final AuthDialog authDialog;
    private String nickname;
    private RandomAccessFile fileHistory;
    private List<Long> fileIndex;

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
            setClientChatTitle(nickname);
            ClientController.this.openChat();
        });
        authDialog.setVisible(true);
    }

    public void setClientChatTitle(String nickname) {
        clientChat.setTitle("Сетевой чат. Вы вошли как: " + nickname);
    }

    private void openChat() {
        authDialog.dispose();
        networkService.setMessageHandler(clientChat::appendMessage);
        openHistoryFile();
        loadMessageFromHistoryToChat();
        clientChat.setVisible(true);
    }

    private void openHistoryFile() {
        try {
            fileHistory = new RandomAccessFile("history_" + nickname + ".txt", "rw");
            fileIndex = createIndexHistoryFile(fileHistory);
        } catch (FileNotFoundException e) {
            System.out.println("Файл истории не найден!");
            e.printStackTrace();
        }
    }

    private void closeHistoryFile() {
        try {
            fileHistory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Long> createIndexHistoryFile(RandomAccessFile file) {
        long pointer = 0;
        ArrayList<Long> list = new ArrayList<>();
        try {
            while(pointer < file.length()) {
                list.add(pointer);
                file.seek(pointer);
                file.readUTF();
                pointer = file.getFilePointer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void loadMessageFromHistoryToChat() {
        try {
            int startIndex = (fileIndex.size() >= 100) ? fileIndex.size() - 100 : 0;
            for (int i = startIndex; i < fileIndex.size(); i++) {
                fileHistory.seek(fileIndex.get(i));
                clientChat.addHistoryToChat(fileHistory.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessageToHistory(String message) {
        try {
            fileHistory.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUserName(String nickname) {
        this.nickname = nickname;
    }

    private void connectToServer() throws IOException {
        try {
            networkService.connect(this);
            authDialog.getTimer().start();
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

    public void changeNick(String newNickName) {
        try {
            networkService.sendCommand(Command.changeNickname(newNickName));
            nickname = newNickName;
        } catch (IOException e) {
            showErrorMessage("Ошибка смены ника.");
            e.printStackTrace();
        }
    }

    public void shutdown() {
        networkService.close();
        closeHistoryFile();
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
        if (clientChat.isVisible()) {
            clientChat.showError(errorMessage);
        }
        else if (authDialog.isVisible()) {
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
        authDialog.getTimer().stop();
    }
}
