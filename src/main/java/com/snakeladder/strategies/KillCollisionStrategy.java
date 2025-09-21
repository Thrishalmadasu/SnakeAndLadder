package com.snakeladder.strategies;

import com.snakeladder.interfaces.CollisionStrategy;
import com.snakeladder.interfaces.Player;
import java.util.List;

public class KillCollisionStrategy implements CollisionStrategy {
    
    public void handle(Player current, List<Player> all, int position) {
        for (Player p : all) {
            if (p != current && p.getPosition() == position) {
                p.setPosition(0);
                p.setStarted(false);
            }
        }
    }
    
    public String getRule() {
        return "Landing on another player sends them back to start";
    }
}
