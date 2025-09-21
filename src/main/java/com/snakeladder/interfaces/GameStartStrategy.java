package com.snakeladder.interfaces;

public interface GameStartStrategy {
    boolean canStart(int roll);
    String getRule();
}
