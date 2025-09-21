package com.snakeladder.strategies;

import com.snakeladder.interfaces.WinningStrategy;

public class ExactWinStrategy implements WinningStrategy {
    
    public boolean hasWon(int position, int roll, int boardSize) {
        return position + roll == boardSize;
    }
    
    public int getNewPosition(int position, int roll, int boardSize) {
        int newPos = position + roll;
        return newPos == boardSize ? newPos : (newPos > boardSize ? position : newPos);
    }
}
