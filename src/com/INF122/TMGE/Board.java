package com.INF122.TMGE;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    //instance variables
    //private int[][] boardGrid;
    public Tile[][] boardGrid;
    public int gridWidth;
    public int gridHeight;
    public int tileSize;

    //private List<Shape> prototypeShapes = new ArrayList<Shape>();
    private List<Shape> currentShapes = new ArrayList<Shape>();


    public Board(int gridHeight, int gridWidth, int tileSize){
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        this.tileSize = tileSize;
        boardGrid = new Tile[this.gridWidth][this.gridHeight];
    }

    /*
         e.g. for tetris add the 7 different tetrominos
              for dr mario add the 9 different pills
     */
    /*public void setPrototypeShapes(List<Shape> prototypeShapes){
        this.prototypeShapes = prototypeShapes;
    }*/

    //TODO finish later
    /*
        return to group to caller
        then add group to scene constructor and set window's scene to the scene
        then window.show() to display
     */
    public Group renderBoard(Group group){
        for(Shape shape : this.currentShapes){
            shape.renderShape(group);
        }
        return group;
    }

    public Tile getTile(int columnIndex, int rowIndex){
        return this.boardGrid[columnIndex][rowIndex];
    }

    public void placeTile(Tile tile){
        this.boardGrid[tile.columnIndex][tile.rowIndex] = tile;
    }

    public void removeTile(Tile tile){
        this.boardGrid[tile.columnIndex][tile.rowIndex] = null;
    }

    public boolean isTileOffGrid(Tile tile){
       if(tile.columnIndex < 0 ||
               tile.columnIndex >= this.gridWidth ||
               tile.rowIndex < 0 ||
               tile.rowIndex >= this.gridHeight){
           return true;
       } else {
           return false;
       }
    }


} //end of Board class
