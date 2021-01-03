package ru.geekbrains.java2.lessonsix;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private static final ConsoleFacade facade = new ConsoleFacade();

    public EchoClient(Socket socket) throws IOException {
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        facade.setInputStream(inputStream);
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888)) {
            System.out.println("Клиент запущен.");
            EchoClient client = new EchoClient(socket);
            Thread getMessage = new Thread(client::messageGet);
            Thread sendMessage = new Thread(client::messageSend);
            sendMessage.start();
            getMessage.start();
            getMessage.join();
            sendMessage.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void messageSend() {
        while (true) {
            try {
                String message = facade.getMessageFromConsole();
                if (message != null) {
                    outputStream.writeUTF(message);
                    System.out.println("Сообщение отправлено.");
                }

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void messageGet() {
        while (true) {
            String message = facade.getMessageFromSocket(() -> {
                System.out.println("Сервер недоступен.");
                System.exit(0);
            });
            System.out.println("Сервер написал: " + message);
        }
    }


}
