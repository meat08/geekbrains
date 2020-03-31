package ru.geekbrains.java2.server.database;

import java.sql.*;

public class DatabaseService {
    private Connection connection = null;
    private String URL = "jdbc:sqlite:NetworkServerChat/src/ru/geekbrains/java2/server/database/ServerChat.sqlite";

    public void start() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("База данных подключена");
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void stop() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUsernameByLoginAndPassword(String login, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT nickname FROM users WHERE login = ? AND password = ?"
            );
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("nickname");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean nicknameIsBusy(String nickname) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id FROM users WHERE nickname = ?"
            );
            statement.setString(1, nickname);
            ResultSet result = statement.executeQuery();
            if (result.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean wordsIsCurse(String word) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT word FROM curse_words WHERE word = ?"
            );
            statement.setString(1, word);
            ResultSet result = statement.executeQuery();
            if (result.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changeNickname(String oldNickname, String newNickname) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET nickname = ? WHERE nickname = ?"
            );
            statement.setString(1, newNickname);
            statement.setString(2, oldNickname);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
