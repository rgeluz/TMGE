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

    public int top;
    public int bottom;

    public Shape(List<Tile> tiles){
        this.tiles = tiles;
        this.centerPieceColumnIndex = tiles.get(0).columnIndex;
        this.centerPieceRowIndex = tiles.get(0).rowIndex;

        this.bottom = this.tiles.get(0).rowIndex;
        this.top = this.tiles.get(0).rowIndex;
        for (int i = 1; i < this.tiles.size(); i ++)
        {
            if (this.tiles.get(i).rowIndex > bottom)
            {
                this.bottom = this.tiles.get(i).rowIndex;
            }
            if (this.tiles.get(i).rowIndex < top)
            {
                this.top = this.tiles.get(i).rowIndex;
            }
        }


    }

    public void renderShape(Group group){
        for(Tile tile: tiles){
            tile.renderTile(group);
        }
    }

}
