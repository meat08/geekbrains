package ru.geekbrains.java3.lessonfour;

public class ControlThread {
    private boolean aTurn = true;
    private boolean bTurn = false;
    private boolean cTurn = false;

    public static void main(String[] args) {
        ControlThread controlThread = new ControlThread();
        new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                controlThread.printA();
            }
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                controlThread.printB();
            }
        }).start();
        new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                controlThread.printC();
            }
        }).start();
    }

    private synchronized void printA() {
        try {
            while(!aTurn) this.wait();
            System.out.println("A");
            aTurn = false;
            bTurn = true;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void printB() {
        try {
            while(!bTurn) this.wait();
            System.out.println("B");
            bTurn = false;
            cTurn = true;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void printC() {
        try {
            while (!cTurn) this.wait();
            System.out.println("C");
            cTurn = false;
            aTurn = true;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
