package com.snakeladder.models;

import com.snakeladder.interfaces.BoardEntity;
import java.util.*;

public class Board {
    private final int size;
    private final Map<Integer, BoardEntity> entities;
    
    public Board(int size) {
        this.size = size;
        this.entities = new HashMap<>();
    }
    
    
    public void addEntity(BoardEntity entity) {
        entities.put(entity.getStart(), entity);
    }
    
    public Board copy() {
        Board copy = new Board(this.size);
        for (BoardEntity entity : this.entities.values()) {
            copy.addEntity(entity.copy());
        }
        return copy;
    }
    
    public int applyEntity(int position) {
        BoardEntity entity = entities.get(position);
        return entity != null ? entity.apply(position) : position;
    }
    
    public int getSize() { return size; }
    public Collection<BoardEntity> getEntities() { return new ArrayList<>(entities.values()); }
    public boolean hasEntity(int position) { return entities.containsKey(position); }
    
    public String toString() {
        return "Board[" + size + "] with " + entities.size() + " entities";
    }
}
