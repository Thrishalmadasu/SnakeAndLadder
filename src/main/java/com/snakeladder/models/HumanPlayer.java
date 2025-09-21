package com.snakeladder.models;

import com.snakeladder.interfaces.Player;

public class HumanPlayer extends BasePlayer {
    
    public HumanPlayer(String name) {
        super(name);
    }
    
    public String getType() { return "Human"; }
    
    public Player copy() {
        HumanPlayer copy = new HumanPlayer(this.name);
        copy.position = this.position;
        copy.started = this.started;
        copy.consecutiveSixes = this.consecutiveSixes;
        copy.skipTurn = this.skipTurn;
        return copy;
    }
}
