package com.snakeladder.strategies;

import com.snakeladder.interfaces.GameStartStrategy;

public class NormalStartStrategy implements GameStartStrategy {
    
    public boolean canStart(int roll) {
        return true;
    }
    
    public String getRule() {
        return "Any roll can start the game";
    }
}
