package com.snakeladder.models;

import com.snakeladder.interfaces.BoardEntity;

public class Snake implements BoardEntity {
    private final int head, tail;
    
    public Snake(int head, int tail) {
        if (head <= tail) throw new IllegalArgumentException("Invalid snake positions");
        this.head = head;
        this.tail = tail;
    }
    
    public int getStart() { return head; }
    public int getEnd() { return tail; }
    public String getType() { return "Snake"; }
    
    public int apply(int position) {
        return position == head ? tail : position;
    }
    
    public int getHead() { return head; }
    public int getTail() { return tail; }
    
    public BoardEntity copy() {
        return new Snake(this.head, this.tail);
    }
    
    public String toString() {
        return "Snake[" + head + "->" + tail + "]";
    }
}
