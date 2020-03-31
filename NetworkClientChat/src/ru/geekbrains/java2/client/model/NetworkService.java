package ru.geekbrains.java2.client.model;

import ru.geekbrains.java2.client.controller.AuthEvent;
import ru.geekbrains.java2.client.controller.ClientController;
import ru.geekbrains.java2.clientserver.Command;
import ru.geekbrains.java2.clientserver.command.*;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.function.Consumer;

public class NetworkService {
    private final String host;
    private final int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Consumer<String> messageHandler;
    private AuthEvent successfulAuthEvent;
    private ClientController controller;
    private String nickname;

    public NetworkService(String serverName, int serverPort) {
        this.host = serverName;
        this.port = serverPort;
    }

    public void connect( ClientController controller) throws IOException {
        this.controller = controller;
        socket = new Socket(host, port);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        runReadThread();
    }

    private void runReadThread() {
        new Thread(() -> {
            while (true) {
                try {
                    Command command = (Command) in.readObject();
                    switch (command.getType()) {
                        case AUTH: {
                            AuthCommand commandData = (AuthCommand) command.getData();
                            nickname = commandData.getUsername();
                            successfulAuthEvent.authIsSuccessful(nickname);
                            controller.stopTimer();
                            break;
                        }
                        case MESSAGE: {
                            MessageCommand commandData = (MessageCommand) command.getData();
                            if(messageHandler != null) {
                                String message = commandData.getMessage();
                                String username = commandData.getUsername();
                                if(username != null) message = username + ": " + message;
                                messageHandler.accept(message);
                                controller.writeMessageToHistory(message + System.lineSeparator());
                                break;
                            }
                        }
                        case CHANGE_NICKNAME: {
                            ChangeNicknameCommand commandData = (ChangeNicknameCommand) command.getData();
                            nickname = commandData.getNewNickname();
                            controller.setClientChatTitle(nickname);
                            break;
                        }
                        case ERROR: {
                            ErrorCommand commandData = (ErrorCommand) command.getData();
                            controller.showErrorMessage(commandData.getErrorMessage());
                            break;
                        }
                        case UPDATE_USER_LIST: {
                            UpdateUsersListCommand commandData = (UpdateUsersListCommand) command.getData();
                            List<String> users = commandData.getUsers();
                            controller.updateUserList(users);
                            break;
                        }
                        default:
                            System.err.println("Неизвестный тип комманды: " + command.getType());
                    }
                } catch (IOException e) {
                    System.out.println("Поток чтения прерван.");
                    controller.showErrorMessage("Сервер недоступен. Программа будет закрыта.");
                    System.exit(0);
                    return;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendCommand(Command command) throws IOException {
        out.writeObject(command);
    }

    public void setSuccessfulAuthEvent(AuthEvent successfulAuthEvent) {
        this.successfulAuthEvent = successfulAuthEvent;
    }
    public void setMessageHandler(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
