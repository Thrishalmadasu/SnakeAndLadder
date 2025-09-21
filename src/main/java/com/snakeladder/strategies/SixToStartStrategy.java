package com.snakeladder.strategies;

import com.snakeladder.interfaces.GameStartStrategy;

public class SixToStartStrategy implements GameStartStrategy {
    
    public boolean canStart(int roll) {
        return roll == 6;
    }
    
    public String getRule() {
        return "Must roll a 6 to start";
    }
}
