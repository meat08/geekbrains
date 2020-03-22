package ru.geekbrains.java2.clientserver;

public enum CommandType {
    AUTH,
    ERROR,
    PRIVATE_MESSAGE,
    BROADCAST_MESSAGE,
    MESSAGE,
    UPDATE_USER_LIST,
    END
}
