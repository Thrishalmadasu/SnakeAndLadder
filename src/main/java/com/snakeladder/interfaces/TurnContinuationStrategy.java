package com.snakeladder.interfaces;

public interface TurnContinuationStrategy {
    boolean shouldContinueTurn(int roll);
    String getDescription();
}
