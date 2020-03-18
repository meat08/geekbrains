package ru.geekbrains.java2.server.auth;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {

    private static class UserData {
        private String login;
        private String password;
        private String username;

        public UserData(String login, String password, String username) {
            this.login = login;
            this.password = password;
            this.username = username;
        }


    }

    private static final List<UserData> USER_DATA = new ArrayList<>();
    {
        USER_DATA.add(new UserData("login1", "password1", "nick1"));
        USER_DATA.add(new UserData("login2", "password2", "nick2"));
        USER_DATA.add(new UserData("login3", "password3", "nick3"));
        USER_DATA.add(new UserData("login4", "password4", "nick4"));
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (UserData userDatum : USER_DATA) {
            if(userDatum.login.equals(login) & userDatum.password.equals(password)) {
                return userDatum.username;
            }
        } return null;
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }
}