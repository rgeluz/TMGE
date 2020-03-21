package com.INF122.DrMario;

import com.INF122.TMGE.Board;
import com.INF122.TMGE.Direction;
import com.INF122.TMGE.Shape;
import com.INF122.TMGE.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DrMarioShapeFactory {


    static int spawnColIndex;
    static int spawnRowIndex;
    static int tileSize;
    static boolean setColor = false;
    public static boolean setTileBorder = false;
    static boolean setImage = true; //if image is set to true, color should be set to false


    /**
     * Get a random shape from shape factory.
     * @param board
     * @return
     */
    public static Shape getRandomShape(Board board){
        DrMarioShapeEnum randomShapeEnum = DrMarioShapeEnum.getRandomShapeEnum();
        return getShape(randomShapeEnum, board);
    }

    /**
     * Given board, and shape enum, return new shape
     * @param type
     * @param board
     * @return
     */
    public static Shape getShape(DrMarioShapeEnum type, Board board){
        spawnColIndex = (board.gridWidth-1)/2; //half of board width
        spawnRowIndex = 0;
        tileSize = board.tileSize;


        switch(type){
            case RED_RED_PILL:
                return create_Red_Red_Pill();
            case RED_BLUE_PILL:
                return create_Red_Blue_Pill();
            case RED_YELLOW_PILL:
                return create_Red_Yellow_Pill();
            case BLUE_RED_PILL:
                return create_Blue_Red_Pill();
            case BLUE_BLUE_PILL:
                return create_Blue_Blue_Pill();
            case BLUE_YELLOW_PILL:
                return create_Blue_Yellow_Pill();
            case YELLOW_RED_PILL:
                return create_Yellow_Red_Pill();
            case YELLOW_BLUE_PILL:
                return create_Yellow_Blue_Pill();
            case YELLOW_YELLOW_PILL:
                return create_Yellow_Yellow_Pill();
        }
        return null;
    }


    private static Shape create_Red_Red_Pill(){
        Image imageRed = null;
        try {
            //imageRed = new Image(new FileInputStream("DrMario-module/resources/BlockRed.png"));
        	imageRed = new Image(new FileInputStream("resources/BlockRed.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, imageRed, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, imageRed, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Red_Blue_Pill(){
        Image imageRed = null;
        Image imageBlue = null;
        try {
            imageRed = new Image(new FileInputStream("resources/BlockRed.png"));
            imageBlue = new Image(new FileInputStream("resources/BlockBlue.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, imageRed, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, imageBlue, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Red_Yellow_Pill(){
        Image imageRed = null;
        Image imageYellow = null;
        try {
            imageRed = new Image(new FileInputStream("resources/BlockRed.png"));
            imageYellow = new Image(new FileInputStream("resources/BlockYellow.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, imageRed, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, imageYellow, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Blue_Red_Pill(){
        Image imageBlue = null;
        Image imageRed = null;
        try {
            imageBlue = new Image(new FileInputStream("resources/BlockBlue.png"));
            imageRed = new Image(new FileInputStream("resources/BlockRed.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, imageBlue, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, imageRed, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Blue_Blue_Pill(){
        Image imageBlue = null;
        try {
            imageBlue = new Image(new FileInputStream("resources/BlockBlue.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, imageBlue, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, imageBlue, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Blue_Yellow_Pill(){
        Image imageBlue = null;
        Image imageYellow = null;
        try {
            imageBlue = new Image(new FileInputStream("resources/BlockBlue.png"));
            imageYellow = new Image(new FileInputStream("resources/BlockYellow.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, imageBlue, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, imageYellow, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Yellow_Red_Pill(){
        Image imageYellow = null;
        Image imageRed = null;
        try {
            imageYellow = new Image(new FileInputStream("resources/BlockYellow.png"));
            imageRed = new Image(new FileInputStream("resources/BlockRed.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, imageYellow, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, imageRed, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Yellow_Blue_Pill(){
        Image imageYellow = null;
        Image imageBlue = null;
        try {
            imageYellow = new Image(new FileInputStream("resources/BlockYellow.png"));
            imageBlue  = new Image(new FileInputStream("resources/BlockBlue.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, imageYellow, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, imageBlue, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }
    private static Shape create_Yellow_Yellow_Pill(){
        Image imageYellow = null;
        try {
            imageYellow = new Image(new FileInputStream("resources/BlockYellow.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, imageYellow, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, imageYellow, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        return new Shape(tiles);
    }


}
