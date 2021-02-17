package ru.geekbrains.preparation.lesson6;

import java.io.IOException;

public class TestLesson6 {
    public static void main(String[] args) throws IOException {
        HTTPSocketRequest httpSocketRequest = new HTTPSocketRequest();

        System.out.println("Get product with id 1");
        System.out.println(httpSocketRequest.sendGetRequest());
        System.out.println("Create product");
        System.out.println(httpSocketRequest.sendPostRequest());
        System.out.println("Delete product");
        System.out.println(httpSocketRequest.sendDeleteRequest());
    }
}
