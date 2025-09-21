package com.snakeladder.models;

import java.util.Random;

public enum Dice {
    INSTANCE;
    
    private final Random random = new Random();
    
    public int roll() {
        return random.nextInt(6) + 1;
    }
    
    public int rollMultiple(int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += roll();
        }
        return sum;
    }
    
    public int[] rollIndividual(int count) {
        int[] results = new int[count];
        for (int i = 0; i < count; i++) {
            results[i] = roll();
        }
        return results;
    }
}
