package com.snakeladder.services;

import com.snakeladder.models.Dice;

public class DiceService {
    private final int diceCount;
    private final Dice dice = Dice.INSTANCE;
    
    public DiceService(int diceCount) {
        this.diceCount = diceCount;
    }
    
    public int roll() {
        return dice.rollMultiple(diceCount);
    }
    
    public int[] rollIndividual() {
        return dice.rollIndividual(diceCount);
    }
    
    public boolean contains(int number) {
        int[] rolls = rollIndividual();
        for (int roll : rolls) {
            if (roll == number) return true;
        }
        return false;
    }
    
    public int getDiceCount() {
        return diceCount;
    }
}
