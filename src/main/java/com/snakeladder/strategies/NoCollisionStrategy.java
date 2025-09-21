package com.snakeladder.strategies;

import com.snakeladder.interfaces.CollisionStrategy;
import com.snakeladder.interfaces.Player;
import java.util.List;

public class NoCollisionStrategy implements CollisionStrategy {
    
    public void handle(Player current, List<Player> all, int position) {
        // Do nothing
    }
    
    public String getRule() {
        return "Players can share the same position";
    }
}
