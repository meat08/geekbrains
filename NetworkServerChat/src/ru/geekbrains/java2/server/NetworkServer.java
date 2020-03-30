package ru.geekbrains.java2.server;

import ru.geekbrains.java2.clientserver.Command;
import ru.geekbrains.java2.server.database.DatabaseService;
import ru.geekbrains.java2.server.client.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NetworkServer {
    private final int port;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final DatabaseService databaseService;


    public NetworkServer(int port) {
        this.port = port;
        this.databaseService = new DatabaseService();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту " + port);
            databaseService.start();
            while (true) {
                System.out.println("Ожидание подключения клиента.");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился.");
                createClientHandler(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка сервера.");
            e.printStackTrace();
        } finally {
            databaseService.stop();
        }
    }

    private void createClientHandler(Socket clientSocket) {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.run();
    }

    public List<ClientHandler> getClients() { return clients; }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public /*synchronized*/ void broadcastMessage(Command message, ClientHandler owner) throws IOException {
        for (ClientHandler client : clients) {
            if(client != owner) client.sendMessage(message);
        }
    }

    public /*synchronized*/ void subscribe(ClientHandler clientHandler) throws IOException {
        clients.add(clientHandler);
        sendUserList();
    }

    public /*synchronized*/ void unsubscribe(ClientHandler clientHandler) throws IOException {
        clients.remove(clientHandler);
        sendUserList();
    }

    private List<String> getAllUserNames() {
        List<String> usernames = new LinkedList<>();
        for (ClientHandler clientHandler : clients) {
            usernames.add(clientHandler.getNickname());
        }
        return usernames;
    }

    public void sendUserList() throws IOException {
        List<String> users = getAllUserNames();
        broadcastMessage(Command.updateUsersListCommand(users), null);
    }

    public /*synchronized*/ void sendMessage(String receiver, Command commandMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(receiver)) {
                client.sendMessage(commandMessage);
                break;
            }
        }
    }

    public boolean isNicknameBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(username)) {
                return true;
            }
        }
        return false;
    }
}