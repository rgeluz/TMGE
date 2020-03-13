package com.INF122.TMGE;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;

public class Tile {

    //instance variables
    public int tileSize;
    public boolean setColor;
    public Color tileColor;
    public boolean setTileBorder;
    public boolean setImage;
    public Image tileImage;
    public Rectangle rectangle;
    public int columnIndex;
    public int rowIndex;
    public int position; //position from center tile
    public int centerPieceColumnIndex;
    public int centerPieceRowIndex;
    public List<Direction> directions;



    public Tile(int tileSize, boolean setColor, Color color, boolean setTileBorder, boolean setImage, Image image, int cenColIndex, int cenRowIndex, int position, List<Direction> directions){
        this.tileSize = tileSize;
        this.setColor = setColor;
        this.tileColor = color;
        this.setTileBorder =setTileBorder;
        this.setImage = setImage;
        this.tileImage = image;
        this.position = position;
        this.centerPieceColumnIndex = cenColIndex;
        this.centerPieceRowIndex = cenRowIndex;
        this.directions = directions;

        this.rectangle = new Rectangle();
        this.rectangle.setHeight(this.tileSize);
        this.rectangle.setWidth(this.tileSize);
        if(this.setColor){
            this.setColor(this.tileColor);
        }
        if(this.setTileBorder){
            this.setBoarder();
        }
        if(this.setImage){
            this.setImage(this.tileImage);
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

    public Tile(int tileSize, boolean setColor, Color color, boolean setTileBorder, boolean setImage, Image image, int cenColIndex, int cenRowIndex, int position, Direction... directions){
        this.tileSize = tileSize;
        this.setColor = setColor;
        this.tileColor = color;
        this.setTileBorder =setTileBorder;
        this.setImage = setImage;
        this.tileImage = image;
        this.position = position;
        this.centerPieceColumnIndex = cenColIndex;
        this.centerPieceRowIndex = cenRowIndex;
        this.directions = Arrays.asList(directions);

        this.rectangle = new Rectangle();
        this.rectangle.setHeight(this.tileSize);
        this.rectangle.setWidth(this.tileSize);
        if(this.setColor){
            this.setColor(this.tileColor);
        }
        if(this.setTileBorder){
            this.setBoarder();
        }
        if(this.setImage){
            this.setImage(this.tileImage);
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
    public void setImage(Image image){
        ImagePattern imagePattern = new ImagePattern(image);
        this.rectangle.setFill(imagePattern);
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
