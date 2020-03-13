package com.INF122.TMGE.tetris;

import com.INF122.TMGE.Board;
import com.INF122.TMGE.Controller;
import javafx.scene.Group;

public class Tetris {
    int tetrisLineCount;
    boolean includeTetrisBorder;
    Controller c;
    Group g;
    Board b;

    Tetris(Board bo) {
        this.tetrisLineCount = 0;
        this.includeTetrisBorder = true;
        this.b = bo;
        c = new Controller(bo);
        if(this.includeTetrisBorder){
            g = c.create();
        }
    }
    //TODO take private methods from Controller and refactor them into Tetris


}
