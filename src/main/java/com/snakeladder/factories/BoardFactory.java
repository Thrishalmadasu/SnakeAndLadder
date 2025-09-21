package com.snakeladder.factories;

import com.snakeladder.models.*;
import java.util.Random;

public class BoardFactory {
    private static final Random random = new Random();
    
    private enum Difficulty { EASY, MEDIUM, HARD }
    
    private static int getRow(int position, int boardSize) {
        int boardWidth = (int) Math.sqrt(boardSize);
        return (position - 1) / boardWidth + 1;
    }
    
    private static boolean isValidLadder(int bottom, int top, int boardSize) {
        return bottom < top && getRow(bottom, boardSize) < getRow(top, boardSize);
    }
    
    private static boolean isValidSnake(int head, int tail, int boardSize) {
        return head > tail && getRow(head, boardSize) > getRow(tail, boardSize);
    }
    
    public static Board createEasyBoard() {
        return createBoard(Difficulty.EASY);
    }
    
    public static Board createMediumBoard() {
        return createBoard(Difficulty.MEDIUM);
    }
    
    public static Board createHardBoard() {
        return createBoard(Difficulty.HARD);
    }
    
    private static Board createBoard(Difficulty difficulty) {
        switch (difficulty) {
            case EASY: return createEasyBoardInternal();
            case MEDIUM: return createMediumBoardInternal();
            case HARD: return createHardBoardInternal();
            default: return createEasyBoardInternal();
        }
    }
    
    private static Board createEasyBoardInternal() {
        Board board = new Board(100);
        
        board.addEntity(new Ladder(1, 38));   // Row 1 -> Row 4
        board.addEntity(new Ladder(4, 25));   // Row 1 -> Row 3
        board.addEntity(new Ladder(9, 31));   // Row 1 -> Row 4
        board.addEntity(new Ladder(21, 42));  // Row 3 -> Row 5
        board.addEntity(new Ladder(28, 84));  // Row 3 -> Row 9
        
        board.addEntity(new Snake(16, 6));    // Row 2 -> Row 1
        board.addEntity(new Snake(47, 26));   // Row 5 -> Row 3
        board.addEntity(new Snake(49, 11));   // Row 5 -> Row 2
        board.addEntity(new Snake(87, 24));   // Row 9 -> Row 3
        
        return board;
    }
    
    private static Board createMediumBoardInternal() {
        Board board = new Board(100);
        
        board.addEntity(new Ladder(2, 15));   // Row 1 -> Row 2
        board.addEntity(new Ladder(5, 27));   // Row 1 -> Row 3
        board.addEntity(new Ladder(15, 26));  // Row 2 -> Row 3
        board.addEntity(new Ladder(18, 29));  // Row 2 -> Row 3
        board.addEntity(new Ladder(21, 42));  // Row 3 -> Row 5
        board.addEntity(new Ladder(36, 55));  // Row 4 -> Row 6
        board.addEntity(new Ladder(51, 67));  // Row 6 -> Row 7
        board.addEntity(new Ladder(71, 91));  // Row 8 -> Row 10
        
        board.addEntity(new Snake(17, 4));    // Row 2 -> Row 1
        board.addEntity(new Snake(20, 9));    // Row 2 -> Row 1
        board.addEntity(new Snake(34, 16));   // Row 4 -> Row 2
        board.addEntity(new Snake(47, 26));   // Row 5 -> Row 3
        board.addEntity(new Snake(56, 33));   // Row 6 -> Row 4
        board.addEntity(new Snake(64, 41));   // Row 7 -> Row 5
        board.addEntity(new Snake(87, 36));   // Row 9 -> Row 4
        board.addEntity(new Snake(93, 73));   // Row 10 -> Row 8
        board.addEntity(new Snake(95, 65));   // Row 10 -> Row 7
        board.addEntity(new Snake(98, 79));   // Row 10 -> Row 8
        
        return board;
    }
    
    private static Board createHardBoardInternal() {
        Board board = new Board(144);
        
        int laddersAdded = 0;
        int attempts = 0;
        while (laddersAdded < 12 && attempts < 100) {
            int start = random.nextInt(130) + 5;
            int end = start + random.nextInt(40) + 15;
            if (end <= 144 && isValidLadder(start, end, 144)) {
                board.addEntity(new Ladder(start, end));
                laddersAdded++;
            }
            attempts++;
        }
        
        int snakesAdded = 0;
        attempts = 0;
        while (snakesAdded < 15 && attempts < 100) {
            int head = random.nextInt(120) + 25;
            int tail = head - random.nextInt(20) - 10;
            if (tail > 0 && isValidSnake(head, tail, 144)) {
                board.addEntity(new Snake(head, tail));
                snakesAdded++;
            }
            attempts++;
        }
        
        return board;
    }
}
