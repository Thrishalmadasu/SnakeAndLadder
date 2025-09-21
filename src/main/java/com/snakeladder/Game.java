package com.snakeladder;

import com.snakeladder.interfaces.*;
import com.snakeladder.models.Board;
import com.snakeladder.services.DiceService;
import java.util.*;

public final class Game {
    private final Board board;
    private final List<Player> players;
    private final DiceService diceService;
    private final WinningStrategy winStrategy;
    private final GameStartStrategy startStrategy;
    private final SpecialRuleStrategy specialRule;
    private final CollisionStrategy collisionStrategy;
    private final TurnContinuationStrategy turnContinuationStrategy;
    
    private int currentPlayerIndex = 0;
    private Player winner;
    private boolean gameOver = false;
    
    private Game(Builder builder) {
        this.board = builder.board;
        this.players = deepCopyPlayers(builder.players);
        this.diceService = builder.diceService;
        this.winStrategy = builder.winStrategy;
        this.startStrategy = builder.startStrategy;
        this.specialRule = builder.specialRule;
        this.collisionStrategy = builder.collisionStrategy;
        this.turnContinuationStrategy = builder.turnContinuationStrategy;
    }
    
    private List<Player> deepCopyPlayers(List<Player> originalPlayers) {
        List<Player> copiedPlayers = new ArrayList<>();
        for (Player player : originalPlayers) {
            copiedPlayers.add(player.copy());
        }
        return copiedPlayers;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Board board;
        private List<Player> players = new ArrayList<>();
        private DiceService diceService;
        private WinningStrategy winStrategy;
        private GameStartStrategy startStrategy;
        private SpecialRuleStrategy specialRule;
        private CollisionStrategy collisionStrategy;
        private TurnContinuationStrategy turnContinuationStrategy;
        
        private Builder() {}
        
        public Builder withBoard(Board board) {
            this.board = board;
            return this;
        }
        
        public Builder withPlayers(List<Player> players) {
            this.players = new ArrayList<>(players);
            return this;
        }
        
        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }
        
        public Builder withDiceService(DiceService diceService) {
            this.diceService = diceService;
            return this;
        }
        
        public Builder withWinningStrategy(WinningStrategy winStrategy) {
            this.winStrategy = winStrategy;
            return this;
        }
        
        public Builder withStartStrategy(GameStartStrategy startStrategy) {
            this.startStrategy = startStrategy;
            return this;
        }
        
        public Builder withSpecialRule(SpecialRuleStrategy specialRule) {
            this.specialRule = specialRule;
            return this;
        }
        
        public Builder withCollisionStrategy(CollisionStrategy collisionStrategy) {
            this.collisionStrategy = collisionStrategy;
            return this;
        }
        
        public Builder withTurnContinuationStrategy(TurnContinuationStrategy turnContinuationStrategy) {
            this.turnContinuationStrategy = turnContinuationStrategy;
            return this;
        }
        
        public Game build() {
            validate();
            return new Game(this);
        }
        
        private void validate() {
            if (board == null) throw new IllegalStateException("Board is required");
            if (players.isEmpty()) throw new IllegalStateException("At least one player is required");
            if (diceService == null) throw new IllegalStateException("DiceService is required");
            if (winStrategy == null) throw new IllegalStateException("WinningStrategy is required");
            if (startStrategy == null) throw new IllegalStateException("GameStartStrategy is required");
            if (specialRule == null) throw new IllegalStateException("SpecialRuleStrategy is required");
            if (collisionStrategy == null) throw new IllegalStateException("CollisionStrategy is required");
            if (turnContinuationStrategy == null) throw new IllegalStateException("TurnContinuationStrategy is required");
        }
    }
    
    public void play() {
        System.out.println("Starting game with " + players.size() + " players");
        System.out.println("Board size: " + board.getSize());
        System.out.println("Start rule: " + startStrategy.getRule());
        System.out.println();
        
        while (!gameOver) {
            playTurn();
        }
        
        System.out.println("\nGame Over! Winner: " + winner.getName());
    }
    
    private void playTurn() {
        Player current = players.get(currentPlayerIndex);
        
        if (current.shouldSkipTurn()) {
            System.out.println(current.getName() + " skips turn");
            current.setSkipTurn(false);
            nextPlayer();
            return;
        }
        
        int roll = diceService.roll();
        System.out.println(current.getName() + " rolled " + roll);
        
        if (!current.hasStarted() && !startStrategy.canStart(roll)) {
            System.out.println(current.getName() + " cannot start yet");
            nextPlayer();
            return;
        }
        
        if (!current.hasStarted()) {
            current.setStarted(true);
            System.out.println(current.getName() + " starts the game!");
        }
        
        if (roll == 6) {
            current.incrementSixes();
        } else {
            current.resetSixes();
        }
        
        if (!specialRule.apply(current, current.getConsecutiveSixes(), roll)) {
            nextPlayer();
            return;
        }
        
        int oldPos = current.getPosition();
        int newPos = winStrategy.getNewPosition(oldPos, roll, board.getSize());
        current.setPosition(newPos);
        
        if (winStrategy.hasWon(oldPos, roll, board.getSize())) {
            winner = current;
            gameOver = true;
            return;
        }
        
        newPos = board.applyEntity(newPos);
        current.setPosition(newPos);
        
        collisionStrategy.handle(current, players, newPos);
        
        System.out.println(current.getName() + " moved from " + oldPos + " to " + newPos);
        
        if (!turnContinuationStrategy.shouldContinueTurn(roll)) {
            nextPlayer();
        }
    }
    
    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    public Player getWinner() { return winner != null ? winner.copy() : null; }
    public boolean isGameOver() { return gameOver; }
    public List<Player> getPlayers() { return deepCopyPlayers(players); }
    public Board getBoard() { return board.copy(); }
}
