# Snake and Ladder Game

A comprehensive implementation of the classic Snake and Ladder board game using Java with Strategy Pattern and Builder Pattern for flexible game configuration.

## Features

- Multiple difficulty Game Boards (Easy, Medium, Hard)
- Configurable game rules and strategies
- Support for both Human and Bot players
- Strategy-based design for easy extensibility
- Proper encapsulation and design patterns
- Realistic board layout with snakes and ladders spanning different rows
- Row validation to ensure logical game board structure

## Architecture Overview

This implementation uses several design patterns:

- **Strategy Pattern**: For game rules, winning conditions, collision handling, etc.
- **Builder Pattern**: For flexible game configuration
- **Factory Pattern**: For board creation with different difficulty levels
- **Singleton Pattern**: For dice implementation

## Class Diagram

```mermaid
classDiagram
    %% Core Game Classes
    class Game {
        -Board board
        -List~Player~ players
        -DiceService diceService
        -WinningStrategy winStrategy
        -GameStartStrategy startStrategy
        -SpecialRuleStrategy specialRule
        -CollisionStrategy collisionStrategy
        -TurnContinuationStrategy turnContinuationStrategy
        -int currentPlayerIndex
        -Player winner
        -boolean gameOver
        +builder() Builder
        +play() void
        +getWinner() Player
        +isGameOver() boolean
        +getPlayers() List~Player~
        +getBoard() Board
    }

    class GameBuilder {
        -Board board
        -List~Player~ players
        -DiceService diceService
        -WinningStrategy winStrategy
        -GameStartStrategy startStrategy
        -SpecialRuleStrategy specialRule
        -CollisionStrategy collisionStrategy
        -TurnContinuationStrategy turnContinuationStrategy
        +withBoard(Board) Builder
        +withPlayers(List~Player~) Builder
        +addPlayer(Player) Builder
        +withDiceService(DiceService) Builder
        +withWinningStrategy(WinningStrategy) Builder
        +withStartStrategy(GameStartStrategy) Builder
        +withSpecialRule(SpecialRuleStrategy) Builder
        +withCollisionStrategy(CollisionStrategy) Builder
        +withTurnContinuationStrategy(TurnContinuationStrategy) Builder
        +build() Game
        -validate() void
    }

    class GameDemo {
        +main(String[]) void
    }

    %% Board and Entities
    class Board {
        -int size
        -Map~Integer, BoardEntity~ entities
        +Board(int)
        +addEntity(BoardEntity) void
        +copy() Board
        +applyEntity(int) int
        +getSize() int
        +getEntities() Collection~BoardEntity~
        +hasEntity(int) boolean
        +toString() String
    }

    class BoardFactory {
        -Random random
        +createEasyBoard() Board
        +createMediumBoard() Board
        +createHardBoard() Board
        -createBoard(Difficulty) Board
        -createEasyBoardInternal() Board
        -createMediumBoardInternal() Board
        -createHardBoardInternal() Board
        -getRow(int, int) int
        -isValidLadder(int, int, int) boolean
        -isValidSnake(int, int, int) boolean
    }

    %% Board Entities
    interface BoardEntity {
        +getStart() int
        +getEnd() int
        +getType() String
        +apply(int) int
        +copy() BoardEntity
    }

    class Snake {
        -int head
        -int tail
        +Snake(int, int)
        +getStart() int
        +getEnd() int
        +getType() String
        +apply(int) int
        +getHead() int
        +getTail() int
        +copy() BoardEntity
        +toString() String
    }

    class Ladder {
        -int bottom
        -int top
        +Ladder(int, int)
        +getStart() int
        +getEnd() int
        +getType() String
        +apply(int) int
        +getBottom() int
        +getTop() int
        +copy() BoardEntity
        +toString() String
    }

    %% Players
    interface Player {
        +getName() String
        +getPosition() int
        +setPosition(int) void
        +hasStarted() boolean
        +setStarted(boolean) void
        +getConsecutiveSixes() int
        +setConsecutiveSixes(int) void
        +incrementSixes() void
        +resetSixes() void
        +shouldSkipTurn() boolean
        +setSkipTurn(boolean) void
        +reset() void
        +getType() String
        +copy() Player
    }

    abstract class BasePlayer {
        #String name
        #int position
        #boolean started
        #int consecutiveSixes
        #boolean skipTurn
        +BasePlayer(String)
        #BasePlayer(BasePlayer)
        +getName() String
        +getPosition() int
        +setPosition(int) void
        +hasStarted() boolean
        +setStarted(boolean) void
        +getConsecutiveSixes() int
        +setConsecutiveSixes(int) void
        +incrementSixes() void
        +resetSixes() void
        +shouldSkipTurn() boolean
        +setSkipTurn(boolean) void
        +reset() void
        +toString() String
    }

    class HumanPlayer {
        +HumanPlayer(String)
        +getType() String
        +copy() Player
    }

    class BotPlayer {
        +BotPlayer(String)
        +getType() String
        +copy() Player
    }

    %% Dice and Services
    enum Dice {
        INSTANCE
        -Random random
        +roll() int
        +rollMultiple(int) int
        +rollIndividual(int) int[]
    }

    class DiceService {
        -int diceCount
        -Dice dice
        +DiceService(int)
        +roll() int
        +rollIndividual() int[]
        +contains(int) boolean
        +getDiceCount() int
    }

    %% Strategy Interfaces
    interface WinningStrategy {
        +hasWon(int, int, int) boolean
        +getNewPosition(int, int, int) int
    }

    interface GameStartStrategy {
        +canStart(int) boolean
        +getRule() String
    }

    interface SpecialRuleStrategy {
        +apply(Player, int, int) boolean
        +getDescription() String
    }

    interface TurnContinuationStrategy {
        +shouldContinueTurn(int) boolean
        +getDescription() String
    }

    interface CollisionStrategy {
        +handle(Player, List~Player~, int) void
        +getRule() String
    }

    %% Winning Strategies
    class ExactWinStrategy {
        +hasWon(int, int, int) boolean
        +getNewPosition(int, int, int) int
    }

    class OvershootWinStrategy {
        +hasWon(int, int, int) boolean
        +getNewPosition(int, int, int) int
    }

    %% Game Start Strategies
    class SixToStartStrategy {
        +canStart(int) boolean
        +getRule() String
    }

    class NormalStartStrategy {
        +canStart(int) boolean
        +getRule() String
    }

    %% Special Rule Strategies
    class RestartOnThreeSixesStrategy {
        +apply(Player, int, int) boolean
        +getDescription() String
    }

    class SkipTurnOnThreeSixesStrategy {
        +apply(Player, int, int) boolean
        +getDescription() String
    }

    %% Turn Continuation Strategies
    class RollAgainOnSixStrategy {
        +shouldContinueTurn(int) boolean
        +getDescription() String
    }

    class NoExtraTurnsStrategy {
        +shouldContinueTurn(int) boolean
        +getDescription() String
    }

    %% Collision Strategies
    class KillCollisionStrategy {
        +handle(Player, List~Player~, int) void
        +getRule() String
    }

    class NoCollisionStrategy {
        +handle(Player, List~Player~, int) void
        +getRule() String
    }

    %% Relationships
    Game *-- GameBuilder : contains
    Game *-- Board : contains
    Game *-- Player : contains
    Game *-- DiceService : contains
    Game *-- WinningStrategy : contains
    Game *-- GameStartStrategy : contains
    Game *-- SpecialRuleStrategy : contains
    Game *-- TurnContinuationStrategy : contains
    Game *-- CollisionStrategy : contains

    BoardFactory --> Board : creates
    Board *-- BoardEntity : contains
    
    Snake ..|> BoardEntity : implements
    Ladder ..|> BoardEntity : implements
    
    BasePlayer ..|> Player : implements
    HumanPlayer --|> BasePlayer : extends
    BotPlayer --|> BasePlayer : extends
    
    DiceService *-- Dice : contains
    
    ExactWinStrategy ..|> WinningStrategy : implements
    OvershootWinStrategy ..|> WinningStrategy : implements
    
    SixToStartStrategy ..|> GameStartStrategy : implements
    NormalStartStrategy ..|> GameStartStrategy : implements
    
    RestartOnThreeSixesStrategy ..|> SpecialRuleStrategy : implements
    SkipTurnOnThreeSixesStrategy ..|> SpecialRuleStrategy : implements
    
    RollAgainOnSixStrategy ..|> TurnContinuationStrategy : implements
    NoExtraTurnsStrategy ..|> TurnContinuationStrategy : implements
    
    KillCollisionStrategy ..|> CollisionStrategy : implements
    NoCollisionStrategy ..|> CollisionStrategy : implements

    GameDemo --> Game : creates
    GameDemo --> BoardFactory : uses
```

## Usage Example

```java
// Create a game with medium difficulty board and custom rules
Board board = BoardFactory.createMediumBoard();
DiceService dice = new DiceService(1);

Game game = Game.builder()
    .withBoard(board)
    .addPlayer(new HumanPlayer("Alice"))
    .addPlayer(new BotPlayer("Bot"))
    .withDiceService(dice)
    .withWinningStrategy(new ExactWinStrategy())
    .withStartStrategy(new SixToStartStrategy())
    .withSpecialRule(new RestartOnThreeSixesStrategy())
    .withCollisionStrategy(new KillCollisionStrategy())
    .withTurnContinuationStrategy(new RollAgainOnSixStrategy())
    .build();

game.play();
```

## Board Difficulty Levels

### Easy Board
- 100 squares (10x10 grid)
- 5 ladders, 4 snakes
- All snakes and ladders span multiple rows for realistic gameplay
- Balanced for beginners

### Medium Board
- 100 squares (10x10 grid)
- 8 ladders, 10 snakes
- All entities validated to span different rows
- More challenging gameplay with strategic placement

### Hard Board
- 144 squares (12x12 grid)
- 12 randomly placed ladders
- 15 randomly placed snakes
- All randomly generated entities validated to span multiple rows
- Most challenging experience with larger board and more obstacles

### Row Validation System
- **Ladders**: Always go from a lower row to a higher row
- **Snakes**: Always go from a higher row to a lower row
- **Visual Logic**: Ensures game board makes visual and logical sense
- **Automatic Validation**: Invalid placements are automatically rejected during board generation

## Strategy Implementations

### Winning Strategies
- **ExactWinStrategy**: Must land exactly on the final square
- **OvershootWinStrategy**: Can overshoot to win

### Game Start Strategies
- **SixToStartStrategy**: Must roll a 6 to begin
- **NormalStartStrategy**: Can start with any roll

### Special Rule Strategies
- **RestartOnThreeSixesStrategy**: Player restarts from position 0 after 3 consecutive sixes
- **SkipTurnOnThreeSixesStrategy**: Player skips next turn after 3 consecutive sixes

### Turn Continuation Strategies
- **RollAgainOnSixStrategy**: Player gets another turn when rolling a 6
- **NoExtraTurnsStrategy**: No extra turns regardless of roll

### Collision Strategies
- **KillCollisionStrategy**: Landing on another player sends them back to start
- **NoCollisionStrategy**: Players can occupy the same position

## Design Patterns Used

1. **Strategy Pattern**: Allows different game rules to be swapped at runtime
2. **Builder Pattern**: Provides flexible game configuration
3. **Factory Pattern**: Encapsulates board creation logic with proper encapsulation
4. **Singleton Pattern**: Ensures single dice instance
5. **Template Method Pattern**: BasePlayer provides common player behavior

## BoardFactory Encapsulation

The `BoardFactory` class follows proper encapsulation principles:

- **Private Enum**: `Difficulty` enum is private and not accessible from outside
- **Public Methods**: Three public static methods for creating different difficulty boards:
  - `createEasyBoard()` - Creates easy difficulty board
  - `createMediumBoard()` - Creates medium difficulty board  
  - `createHardBoard()` - Creates hard difficulty board
- **Row Validation**: Internal helper methods ensure logical board layout:
  - `getRow(int position, int boardSize)` - Calculates row number for any position
  - `isValidLadder(int bottom, int top, int boardSize)` - Validates ladder spans different rows
  - `isValidSnake(int head, int tail, int boardSize)` - Validates snake spans different rows
- **No Direct Access**: Clients cannot access `BoardFactory.Difficulty.MEDIUM` anymore
- **Clean Interface**: Simple method calls like `BoardFactory.createMediumBoard()`

## Running the Game

Execute the `GameDemo` class to see the game in action with different configurations:

```bash
javac -d bin src/main/java/com/snakeladder/*.java src/main/java/com/snakeladder/**/*.java
java -cp bin com.snakeladder.GameDemo
```

## Extensibility

The design allows for easy extension:
- Add new player types by extending `BasePlayer`
- Create new strategies by implementing the respective interfaces
- Add new board entities by implementing `BoardEntity`
- Modify game rules without changing core game logic
