package com.INF122.TMGE;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;

public class Tile {

    //instance variables
    public int tileSize;
    public Color tileColor;
    public boolean setBoarder;
    public Rectangle rectangle;
    public int columnIndex;
    public int rowIndex;
    public int position; //position from center tile
    public int centerPieceColumnIndex;
    public int centerPieceRowIndex;
    public List<Direction> directions;



    public Tile(int tileSize, Color color, boolean setBoarder, int cenColIndex, int cenRowIndex, int position, List<Direction> directions){
        this.tileSize = tileSize;
        this.tileColor = color;
        this.setBoarder=setBoarder;
        this.position = position;
        this.centerPieceColumnIndex = cenColIndex;
        this.centerPieceRowIndex = cenRowIndex;
        this.directions = directions;

        this.rectangle = new Rectangle();
        this.rectangle.setHeight(this.tileSize);
        this.rectangle.setWidth(this.tileSize);
        this.setColor(color);
        if(setBoarder){
            this.setBoarder();
        }


        int dx = 0, dy = 0;

        for (Direction d : directions) {
            dx += position * d.colIndex;
            dy += position * d.rowIndex;
        }

        this.columnIndex = this.centerPieceColumnIndex + dx;
        this.rowIndex = this.centerPieceRowIndex + dy;

        this.setCoordinates(this.columnIndex,this.rowIndex);

    }

    public Tile(int tileSize, Color color, boolean setBoarder, int cenColIndex, int cenRowIndex, int position, Direction... directions){
        this.tileSize = tileSize;
        this.tileColor = color;
        this.setBoarder=setBoarder;
        this.position = position;
        this.centerPieceColumnIndex = cenColIndex;
        this.centerPieceRowIndex = cenRowIndex;
        this.directions = Arrays.asList(directions);

        this.rectangle = new Rectangle();
        this.rectangle.setHeight(this.tileSize);
        this.rectangle.setWidth(this.tileSize);
        this.setColor(color);
        if(setBoarder){
            this.setBoarder();
        }

        int dx = 0, dy = 0;

        for (Direction d : directions) {
            dx += position * d.colIndex;
            dy += position * d.rowIndex;
        }

        this.columnIndex = this.centerPieceColumnIndex + dx;
        this.rowIndex = this.centerPieceRowIndex + dy;

        this.setCoordinates(this.columnIndex,this.rowIndex);

    }


    //TODO for now just set color, replace with image later
    public void setColor(Color color){
        this.rectangle.setFill(color);
    }

    public void setBoarder() { this.rectangle.setStroke(Color.WHITE); }

    //TODO finish later
    /*
        maybe look into using image view
     */
    public void setImage(){

    }

    public void setCoordinates(int columnIndex, int rowIndex){
        this.rectangle.setX(columnIndex*this.tileSize);
        this.rectangle.setY(rowIndex*this.tileSize);
    }

    //TODO
    /*
        JavaFx
     */
    public void renderTile(Group group){
        //add tile to group
        group.getChildren().add(this.rectangle);
    }




}
