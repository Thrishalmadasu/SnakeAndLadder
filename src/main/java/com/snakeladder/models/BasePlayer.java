package com.snakeladder.models;

import com.snakeladder.interfaces.Player;

public abstract class BasePlayer implements Player {
    protected String name;
    protected int position = 0;
    protected boolean started = false;
    protected int consecutiveSixes = 0;
    protected boolean skipTurn = false;
    
    public BasePlayer(String name) {
        this.name = name;
    }
    
    protected BasePlayer(BasePlayer other) {
        this.name = other.name;
        this.position = other.position;
        this.started = other.started;
        this.consecutiveSixes = other.consecutiveSixes;
        this.skipTurn = other.skipTurn;
    }
    
    public String getName() { return name; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public boolean hasStarted() { return started; }
    public void setStarted(boolean started) { this.started = started; }
    public int getConsecutiveSixes() { return consecutiveSixes; }
    public void setConsecutiveSixes(int count) { this.consecutiveSixes = count; }
    public void incrementSixes() { consecutiveSixes++; }
    public void resetSixes() { consecutiveSixes = 0; }
    public boolean shouldSkipTurn() { return skipTurn; }
    public void setSkipTurn(boolean skip) { this.skipTurn = skip; }
    
    public void reset() {
        position = 0;
        started = false;
        consecutiveSixes = 0;
        skipTurn = false;
    }
    
    public String toString() {
        return name + " (" + getType() + ") at " + position;
    }
}
