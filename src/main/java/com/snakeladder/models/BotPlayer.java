package com.snakeladder.models;

import com.snakeladder.interfaces.Player;

public class BotPlayer extends BasePlayer {
    
    public BotPlayer(String name) {
        super(name);
    }
    
    public String getType() { return "Bot"; }
    
    public Player copy() {
        BotPlayer copy = new BotPlayer(this.name);
        copy.position = this.position;
        copy.started = this.started;
        copy.consecutiveSixes = this.consecutiveSixes;
        copy.skipTurn = this.skipTurn;
        return copy;
    }
}
