package com.snakeladder.strategies;

import com.snakeladder.interfaces.TurnContinuationStrategy;

public class RollAgainOnSixStrategy implements TurnContinuationStrategy {
    
    public boolean shouldContinueTurn(int roll) {
        return roll == 6;
    }
    
    public String getDescription() {
        return "Roll again on 6";
    }
}
