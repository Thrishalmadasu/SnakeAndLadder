package com.snakeladder;

import com.snakeladder.factories.BoardFactory;
import com.snakeladder.models.*;
import com.snakeladder.services.DiceService;
import com.snakeladder.strategies.*;

public class GameDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Snake and Ladder Game ===\n");
        
        Board board = BoardFactory.createMediumBoard();
        
        DiceService dice = new DiceService(1);
        
        Game game = Game.builder()
            .withBoard(board)
            .addPlayer(new HumanPlayer("Alice"))
            .addPlayer(new HumanPlayer("Bob"))
            .addPlayer(new BotPlayer("Bot A"))
            .withDiceService(dice)
            .withWinningStrategy(new ExactWinStrategy())
            .withStartStrategy(new SixToStartStrategy())
            .withSpecialRule(new RestartOnThreeSixesStrategy())
            .withCollisionStrategy(new KillCollisionStrategy())
            .withTurnContinuationStrategy(new RollAgainOnSixStrategy())
            .build();
        
        game.play();
        
        System.out.println("\n=== Game with Different Rules ===\n");
        
        Game game2 = Game.builder()
            .withBoard(BoardFactory.createEasyBoard())
            .addPlayer(new HumanPlayer("Charlie"))
            .addPlayer(new BotPlayer("Bot2"))
            .withDiceService(new DiceService(2))
            .withWinningStrategy(new OvershootWinStrategy())
            .withStartStrategy(new NormalStartStrategy())
            .withSpecialRule(new SkipTurnOnThreeSixesStrategy())
            .withCollisionStrategy(new NoCollisionStrategy())
            .withTurnContinuationStrategy(new NoExtraTurnsStrategy())
            .build();
        
        game2.play();
    }
}
