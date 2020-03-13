package com.INF122.TMGE.tetris;

import java.util.Random;

public enum TetrisShapeEnum {
    I_TETROMINO,
    O_TETROMINO,
    T_TETROMINO,
    J_TETROMINO,
    L_TETROMINO,
    S_TETROMINO,
    Z_TETROMINO;

    /**
     * Pick a random value of the TetrisShapeEnum
     * @return
     */
    public static TetrisShapeEnum getRandomShapeEnum(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
