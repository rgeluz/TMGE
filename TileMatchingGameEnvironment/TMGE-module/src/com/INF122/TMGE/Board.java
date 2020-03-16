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
        if(!isTileOffGrid(columnIndex, rowIndex)){
            return this.boardGrid[columnIndex][rowIndex];
        } else {
            throw new IndexOutOfBoundsException("either column index:" + columnIndex +
                    " or row index:" + rowIndex + " is off the board grid.");
        }
    }

    public void placeTile(Tile tile){
        if(!isTileOffGrid(tile.columnIndex, tile.rowIndex)){
            this.boardGrid[tile.columnIndex][tile.rowIndex] = tile;
        } else {
            throw new IndexOutOfBoundsException("either column index:" + tile.columnIndex +
                    " or row index:" + tile.rowIndex + " is off the board grid.");
        }
    }
    
    public void placeNull(int col, int row) {
    	this.boardGrid[col][row] = null;
    }

    public void removeTile(Tile tile){
        if(!isTileOffGrid(tile.columnIndex, tile.rowIndex)){
            this.boardGrid[tile.columnIndex][tile.rowIndex] = null;
        }else {
            throw new IndexOutOfBoundsException("either column index:" + tile.columnIndex +
                    " or row index:" + tile.rowIndex + " is off the board grid.");
        }
    }

    public boolean isTileOffGrid(int columnIndex, int rowIndex){
       if(columnIndex < 0 ||
               columnIndex >= this.gridWidth ||
               rowIndex < 0 ||
               rowIndex >= this.gridHeight){
           return true;
       } else {
           return false;
       }
    }




} //end of Board class
