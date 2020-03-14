package com.INF122.TMGE;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Tile implements Cloneable{

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
    public BufferedImage tileBImage; //TODO: phase tileImage into tileBImage



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
            this.setBorder();
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
            this.setBorder();
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
    public void setColor(Color color) {
        this.rectangle.setFill(color);

        setBlockImage(color);

    }

    public void setBlockImage(Color color) {
        // TODO: return tile image based on color and game (for now code in tetris)
        if (color == Color.BLUE)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockBlue.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
        else if (color == Color.CYAN)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockCyan.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
        else if (color == Color.GREEN)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockGreen.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
        else if (color == Color.GREY)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockGrey.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
        else if (color == Color.ORANGE)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockOrange.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
        else if (color == Color.PURPLE)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockPurple.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
        else if (color == Color.RED)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockRed.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
        else if (color == Color.YELLOW)
            try {
                tileBImage = ImageIO.read(new File("resources\\BlockYellow.png"));
            } catch(IOException ioEx) {
                System.out.println("This image cannot be found");
            }
    }

    public void setBorder() {
        this.rectangle.setStroke(Color.WHITE);
    }

    //TODO finish later - phase into BufferedImage
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

    //Provides a deepCopy of the tile
    public Tile deepCopyClone() throws CloneNotSupportedException{
        //assign the shallow copy to new reference variable
        Tile t = (Tile)super.clone();

        //t.tileImage = new Image("");
        t.directions = new ArrayList<Direction>();
        t.rectangle = new Rectangle();

        //create a new object for the titleImage, directions, rectangle
        //and assign it to shallow copy obtained,
        //to make it a deep copy
        return t;

    }



}
