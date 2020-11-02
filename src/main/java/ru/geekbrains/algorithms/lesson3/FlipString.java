package ru.geekbrains.algorithms.lesson3;

import ru.geekbrains.algorithms.base.mystack.Stack;
import ru.geekbrains.algorithms.base.mystack.StackImpl;

public class FlipString {
    public static void main(String[] args) {
        String line = "ABCDEF";
        Stack<Character> chars = new StackImpl<>(line.length());
        line.chars().forEach(ch -> chars.push((char)ch));
        while (!chars.isEmpty()) {
            System.out.print(chars.pop());
        }
    }
}
