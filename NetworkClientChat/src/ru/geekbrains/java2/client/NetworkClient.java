package ru.geekbrains.java2.client;

import ru.geekbrains.java2.client.controller.ClientController;
import java.io.IOException;

public class NetworkClient {

    private final static int SERVER_PORT = 8888;
    private static final String SERVER_NAME = "localhost";

    public static void main(String[] args) {
        try {
            ClientController clientController = new ClientController(SERVER_NAME, SERVER_PORT);
            clientController.runApplication();
        } catch (IOException e) {
            System.err.println("Ошибка подключения к серверу. Проверьте сетевое подключение.");
        }
    }
}