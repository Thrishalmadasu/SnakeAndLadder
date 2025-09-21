package com.snakeladder.strategies;

import com.snakeladder.interfaces.TurnContinuationStrategy;

public class NoExtraTurnsStrategy implements TurnContinuationStrategy {
    
    public boolean shouldContinueTurn(int roll) {
        return false;
    }
    
    public String getDescription() {
        return "No extra turns";
    }
}
