package ru.geekbrains.java2.clientserver.command;

import java.io.Serializable;

public class ChangeNicknameCommand implements Serializable {
    private String newNickname;


    public ChangeNicknameCommand(String newNickname) {
        this.newNickname = newNickname;
    }

    public String getNewNickname() {
        return newNickname;
    }
}
