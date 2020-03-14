package com.INF122.TMGE.drmario;

import java.util.Random;

public enum DrMarioShapeEnum {
    RED_RED_PILL,
    RED_BLUE_PILL,
    RED_YELLOW_PILL,
    BLUE_RED_PILL,
    BLUE_BLUE_PILL,
    BLUE_YELLOW_PILL,
    YELLOW_RED_PILL,
    YELLOW_BLUE_PILL,
    YELLOW_YELLOW_PILL;


    public static DrMarioShapeEnum getRandomShapeEnum(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}


