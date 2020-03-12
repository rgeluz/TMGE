package com.INF122.TMGE;

public enum Direction {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);


    int colIndex, rowIndex;

    Direction(int colIndex, int rowIndex) {
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
    }


}
