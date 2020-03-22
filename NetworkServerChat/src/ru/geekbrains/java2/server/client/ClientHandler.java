package ru.geekbrains.java2.server.client;

import ru.geekbrains.java2.clientserver.Command;
import ru.geekbrains.java2.clientserver.CommandType;
import ru.geekbrains.java2.clientserver.command.AuthCommand;
import ru.geekbrains.java2.clientserver.command.BroadcastMessageCommand;
import ru.geekbrains.java2.clientserver.command.PrivateMessageCommand;
import ru.geekbrains.java2.server.NetworkServer;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private final NetworkServer networkServer;
    private final Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
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
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream((socket.getInputStream()));

            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    System.out.println("Соединение с клиентом " + nickname + " закрыто.");
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            networkServer.unsubscribe(this);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessages() throws IOException {
        while(true) {
            Command command = readCommand();
            if(command == null) continue;
            switch (command.getType()) {
                case END: {
                    System.out.println("Получена команда 'END'");
                    return;
                }
                case PRIVATE_MESSAGE: {
                    PrivateMessageCommand commandData = (PrivateMessageCommand) command.getData();
                    String receiver = commandData.getReceiver();
                    String message = commandData.getMessage();
                    networkServer.sendMessage(receiver, Command.messageCommand(nickname, message));
                    break;
                }
                case BROADCAST_MESSAGE: {
                    BroadcastMessageCommand commandData = (BroadcastMessageCommand) command.getData();
                    String message = commandData.getMessage();
                    networkServer.broadcastMessage(Command.messageCommand(nickname, message), this);
                    break;
                }
                default:
                    System.err.println("Неизвестный тип комманды: " + command.getType());
            }
        }
    }

    private Command readCommand() throws IOException {
        try {
            return (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            String errorMessage = "Неизвестный тип объекта от клиента.";
            System.err.println(errorMessage);
            e.printStackTrace();
            sendMessage(Command.errorCommand(errorMessage));
            return null;
        }
    }

    private void authentication() throws IOException {
        while (true) {
            Command command = readCommand();
            if(command == null) continue;
            if(command.getType() == CommandType.AUTH) {
                boolean successfulAuth = processAuthCommand(command);
                if(successfulAuth) return;
            }
            else System.out.println("Неизвестный тип комманды процесса аутефикации: " + command.getType());
        }
    }

    private boolean processAuthCommand(Command command) throws IOException {
        AuthCommand commandData = (AuthCommand) command.getData();
        String login = commandData.getLogin();
        String password = commandData.getPassword();
        String username = networkServer.getAuthService().getUsernameByLoginAndPassword(login, password);
        if(username == null) {
            Command errorCommand = Command.errorCommand("Неверный логин или пароль");
            sendMessage(errorCommand);
            return false;
        }
        else if (networkServer.isNicknameBusy(username)) {
            Command authErrorCommand = Command.errorCommand("Пользователь с таким логином уже вошел в чат.");
            sendMessage(authErrorCommand);
            return false;
        }
        else {
            nickname = username;
            String message = nickname + " зашел в чат.";
            networkServer.broadcastMessage(Command.messageCommand(null, message), this);
            commandData.setUsername(nickname);
            sendMessage(command);
            networkServer.subscribe(this);
            return true;
        }
    }

    public String getNickname() {return nickname;}

    public void sendMessage(Command command) throws IOException {
        out.writeObject(command);
    }
}