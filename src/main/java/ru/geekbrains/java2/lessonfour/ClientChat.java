package ru.geekbrains.java2.lessonfour;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ClientChat extends Application {

    public TextField textMessage;
    public ListView<String> memberList;
    public ListView<String> messageViewList;
    public Button sendButton;

    public static void main(String[] args) {
        Application.launch();
    }

    private void sendMessage() {
        String text = textMessage.getText();
        if(!text.equals("")) {
            messageViewList.getItems().add(text);
            textMessage.clear();
            messageViewList.scrollTo(messageViewList.getItems().size() - 1);
        }
    }

    @FXML
    private void initialize () {
        ObservableList<String> members = FXCollections.observableArrayList("Вася", "Петя", "Катя", "Даша");
        memberList.setItems(members);
        textMessage.setOnMouseEntered(e -> textMessage.end());
        textMessage.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) sendMessage();
        });
        sendButton.setOnMouseClicked(e -> sendMessage());
        memberList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> textMessage.setText("@" + newValue + ", "));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ClientChat.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Сетевой чат.");
        stage.setScene(scene);
        stage.show();
    }
}