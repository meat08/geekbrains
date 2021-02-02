package ru.geekbrains.preparation.lesson1.task2;

//Добавил фабрику, для создания грузовых машин (по аналогии можно создать такую же и для легковых)
public class LorryCarFactory {
    public static Lorry makeCar(String color, String name, Engine engine) {
        Lorry lorry = new Lorry();
        lorry.setColor(color);
        lorry.setName(name);
        lorry.setEngine(engine);
        return lorry;
    }
}
