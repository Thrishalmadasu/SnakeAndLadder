package com.snakeladder.interfaces;

import java.util.List;

public interface CollisionStrategy {
    void handle(Player current, List<Player> all, int position);
    String getRule();
}
