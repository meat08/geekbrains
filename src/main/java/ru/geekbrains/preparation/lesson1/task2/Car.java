package ru.geekbrains.preparation.lesson1.task2;

//Изменил модификатор видимости для поля engine и метода open()
abstract class Car {
    private Engine engine;
    private String color;
    private String name;

    protected void start() {
        System.out.println("Car starting");
    }

    public abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
