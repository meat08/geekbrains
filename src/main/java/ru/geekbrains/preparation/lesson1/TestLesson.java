package ru.geekbrains.preparation.lesson1;

import ru.geekbrains.preparation.lesson1.task1.Person;
import ru.geekbrains.preparation.lesson1.task1.PersonBuilder;
import ru.geekbrains.preparation.lesson1.task2.Engine;
import ru.geekbrains.preparation.lesson1.task2.Lorry;
import ru.geekbrains.preparation.lesson1.task2.LorryCarFactory;
import ru.geekbrains.preparation.lesson1.task3.Circle;
import ru.geekbrains.preparation.lesson1.task3.Shape;
import ru.geekbrains.preparation.lesson1.task3.Square;
import ru.geekbrains.preparation.lesson1.task3.Triangle;

public class TestLesson {
    public static void main(String[] args) {
        //Task 1
        Person person = new PersonBuilder()
                .addFirstName("Bob")
                .addLastName("Doe")
                .addAge(33)
                .build();
        System.out.println(person.toString());

        //Task 2
        Lorry lorry = LorryCarFactory.makeCar("blue", "MAN", new Engine());
        lorry.open();
        lorry.move();
        lorry.stop();

        //Task 3
        Shape circle = new Circle(12);
        Shape square = new Square(3);
        Shape triangle = new Triangle(9, 11);
        System.out.println(circle.calculateArea());
        System.out.println(square.calculateArea());
        System.out.println(triangle.calculateArea());
    }
}
