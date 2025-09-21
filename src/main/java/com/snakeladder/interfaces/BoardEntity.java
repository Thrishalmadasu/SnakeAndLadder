package com.snakeladder.interfaces;

public interface BoardEntity {
    int getStart();
    int getEnd();
    String getType();
    int apply(int position);
    BoardEntity copy();
}
