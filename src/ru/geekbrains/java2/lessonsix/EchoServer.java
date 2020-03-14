package ru.geekbrains.java2.lessonsix;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EchoServer {
    private int clientNumber;
    private DataInputStream inputStream;
    private Socket socket;
    private static Map<Integer, Socket> clientSocket = new HashMap<>();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public EchoServer(Socket socket, int clientNumber) throws IOException {
        this.socket = socket;
        this.clientNumber = clientNumber;
        inputStream = new DataInputStream(socket.getInputStream());
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Сервер запущен. Ожидаем клиентов.");
            int counter = 0;
            new Thread(() -> messageSendBroadcast(clientSocket)).start();

            while (true) {
                clientSocket.put(counter, serverSocket.accept());
                System.out.println("Клиент " + counter + " подключен.");
                EchoServer client = new EchoServer(clientSocket.get(counter), counter);
                new Thread(client::messageGet).start();
                counter++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void messageGet() {
        while (true) {
            try {
                String message = inputStream.readUTF();
                System.out.println("Клиент " + clientNumber + " написал: " + message);
            } catch (IOException e) {
                removeClient(clientNumber);
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

    public void removeClient(int clientNumber) {
        clientSocket.remove(clientNumber);
        System.out.println("Клиент " + clientNumber + " отключен.");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
