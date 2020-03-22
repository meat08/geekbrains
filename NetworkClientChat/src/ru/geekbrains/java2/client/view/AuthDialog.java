package ru.geekbrains.java2.client.view;

import ru.geekbrains.java2.client.controller.ClientController;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class AuthDialog extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField loginText;
    private JPasswordField passwordText;
    private JLabel timerLabel;
    public Timer timer;
    private int count = 120;

    private ClientController controller;

    public AuthDialog(ClientController controller) {
        this.controller = controller;
        setTitle("Авторизация");
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setSize(400, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        timer = new Timer(1000, e -> {
            if (count > 0) {
                timerLabel.setText(String.valueOf(count--));
            } else {
                ((Timer) (e.getSource())).stop();
                controller.disconnectFromServer();
            }
        });
        timer.setInitialDelay(0);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void onOK() {
        String login = loginText.getText().trim();
        String pass = new String(passwordText.getPassword()).trim();
        try {
            controller.sendAuthMessage(login, pass);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при попытки аутентификации");
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void onCancel() {
        System.exit(0);
    }

}
