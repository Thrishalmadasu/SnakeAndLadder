package com.snakeladder.models;

import com.snakeladder.interfaces.BoardEntity;

public class Ladder implements BoardEntity {
    private final int bottom, top;
    
    public Ladder(int bottom, int top) {
        if (bottom >= top) throw new IllegalArgumentException("Invalid ladder positions");
        this.bottom = bottom;
        this.top = top;
    }
    
    public int getStart() { return bottom; }
    public int getEnd() { return top; }
    public String getType() { return "Ladder"; }
    
    public int apply(int position) {
        return position == bottom ? top : position;
    }
    
    public int getBottom() { return bottom; }
    public int getTop() { return top; }
    
    public BoardEntity copy() {
        return new Ladder(this.bottom, this.top);
    }
    
    public String toString() {
        return "Ladder[" + bottom + "->" + top + "]";
    }
}
