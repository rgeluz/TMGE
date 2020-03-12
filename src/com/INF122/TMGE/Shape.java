package com.INF122.TMGE;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Shape {


    //instance variables
    private Color defaultColor;
    private Color shapeColor;
    public int centerPieceColumnIndex;
    public int centerPieceRowIndex;
    public List<Tile> tiles;  //four tiles for tetris, two tiles for drmario

    public Shape(List<Tile> tiles){
        this.tiles = tiles;
        this.centerPieceColumnIndex = tiles.get(0).columnIndex;
        this.centerPieceRowIndex = tiles.get(0).rowIndex;
    }

    //TODO
    public void renderShape(Group group){
        for(Tile tile: tiles){
            tile.renderTile(group);
        }
    }




    //TODO
    public void rotateShape(){
        for(Tile tile: tiles){
            //TODO
        }
    }

    //TODO
    /*
        used to copy shape from shape protoype
     */
    /*public static Shape generateShape(){





        return shape;
    }*/

}
