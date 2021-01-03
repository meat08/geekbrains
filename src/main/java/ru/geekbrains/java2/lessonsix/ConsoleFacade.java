package ru.geekbrains.java2.lessonsix;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleFacade {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private DataInputStream inputStream;

    public String getMessageFromConsole() throws IOException {
        String message = reader.readLine();
        if(!message.trim().isEmpty()) {
            if (message.equalsIgnoreCase("/exit")) {
                System.exit(0);
            }
            return message;
        }
        return null;
    }

    public String getMessageFromSocket(Callback callback) {
        try {
            return inputStream != null ? inputStream.readUTF() : null;
        } catch (IOException e) {
            callback.callback();
            return null;
        }
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }
}
