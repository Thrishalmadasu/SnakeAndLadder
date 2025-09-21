package com.snakeladder.interfaces;

public interface WinningStrategy {
    boolean hasWon(int position, int roll, int boardSize);
    int getNewPosition(int position, int roll, int boardSize);
}
