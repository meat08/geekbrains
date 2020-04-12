package ru.geekbrains.java1.lessoneight;


import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private int x1 = 0, x2 = 500, x3 = 250;
    private int y1 = 0, y2 = 0, y3 = 500;
    private volatile boolean paint = false;
    private DrawPanel dPanel;

    public Window() {
        setTitle("Окошко");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 200, 60);
        setVisible(true);
        JButton button = new JButton("Нажми меня");
        setLayout(new BorderLayout());
        add(button, BorderLayout.NORTH);
        setResizable(false);
        dPanel = new DrawPanel();
        add(dPanel);

        button.addActionListener(e -> {
            setBounds(300, 300, 600, 600);
            System.out.println("Button pressed...");
            paint = true;
            remove(button);
        });
//        while (!paint) {
//            Thread.onSpinWait();
//        }
        paintOval();
    }

    public static void main(String[] args) {
        new Window();
    }
    private void paintOval () {
        for(int i = 0; i<220; i++){
            x1++;
            y1++;
            x2--;
            y2++;
            y3--;
            dPanel.repaint();
            try {
                Thread.sleep(50);
            }catch (Exception ignored){}
        }
    }

    private class DrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            if(paint) {
                g.setColor(Color.WHITE);
                g.fillRect(0,0,this.getWidth(),this.getHeight());

                g.setColor(new Color(255, 0, 0, 150));
                g.fillOval(x1,y1,100,100);
                g.setColor(new Color(0, 255, 0, 150));
                g.fillOval(x2,y2,100,100);
                g.setColor(new Color(0, 0, 255, 150));
                g.fillOval(x3,y3,100,100);

            }
        }
    }
}
