package ru.geekbrains.java2.lessonone;

public class Lane implements Barriers {
    private final int length;

    public Lane(int length) { this.length = length; }

    @Override
    public boolean doAction(Members members) {
        System.out.print(" Длина дорожки " + length + ". ");
        return members.run() >= length;
    }
}
