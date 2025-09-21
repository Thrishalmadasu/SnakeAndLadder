package com.snakeladder.strategies;

import com.snakeladder.interfaces.SpecialRuleStrategy;
import com.snakeladder.interfaces.Player;

public class RestartOnThreeSixesStrategy implements SpecialRuleStrategy {
    
    public boolean apply(Player player, int consecutiveSixes, int roll) {
        if (consecutiveSixes >= 3) {
            player.setPosition(0);
            player.setStarted(false);
            player.resetSixes();
            return false;
        }
        return true;
    }
    
    public String getDescription() {
        return "Three consecutive 6s restart player";
    }
}
