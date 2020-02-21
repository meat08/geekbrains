package ru.geekbrains.java2.lessonone;

public class Wall implements Barriers {
    private int height;

    public Wall(int height) { this.height = height; }

    @Override
    public boolean doAction(Members members) {
        System.out.print(" Высота стены " + height + ". ");
        return members.jump() >= height;
    }
}
