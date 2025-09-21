package com.snakeladder.interfaces;

import com.snakeladder.interfaces.Player;

public interface SpecialRuleStrategy {
    boolean apply(Player player, int consecutiveSixes, int roll);
    String getDescription();
}
