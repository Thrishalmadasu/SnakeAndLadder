package com.snakeladder.interfaces;

public interface Player {
    String getName();
    int getPosition();
    void setPosition(int position);
    boolean hasStarted();
    void setStarted(boolean started);
    int getConsecutiveSixes();
    void setConsecutiveSixes(int count);
    void incrementSixes();
    void resetSixes();
    boolean shouldSkipTurn();
    void setSkipTurn(boolean skip);
    void reset();
    String getType();
    Player copy();
}
