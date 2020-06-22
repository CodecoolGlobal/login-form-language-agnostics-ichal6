package com.lechowicz;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println( Objects.hash("Michał Kuk", "1234"));
        System.out.println( Objects.hash("Jan Truskolaski", "1234"));
    }
}
