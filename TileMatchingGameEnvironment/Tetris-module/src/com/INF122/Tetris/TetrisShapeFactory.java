package com.INF122.Tetris;

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

public class TetrisShapeFactory {

    static int spawnColIndex;
    static int spawnRowIndex;
    static int tileSize;
    static boolean setColor = false;
    public static boolean setTileBorder = false; //toggle to add boarder to tile
    static boolean setImage = true; //if image is set to true, color should be set to false



    /**
     * Get a random shape from shape factory.
     * @param board
     * @return
     */
    public static Shape getRandomShape(Board board){
        TetrisShapeEnum randomShapeEnum = TetrisShapeEnum.getRandomShapeEnum();
        return getShape(randomShapeEnum, board);
    }

    /**
     * Given board, and shape enum, return new shape
     * @param type
     * @param board
     * @return
     */
   public static Shape getShape(TetrisShapeEnum type, Board board){
        spawnColIndex = (board.gridWidth-1)/2; //half of board width
        spawnRowIndex = 0;
        tileSize = board.tileSize;


        switch(type){
            case I_TETROMINO:
                return create_I_Tetromino();
            case O_TETROMINO:
                return create_O_Tetromino();
            case T_TETROMINO:
                return create_T_Tetromino();
            case J_TETROMINO:
                return create_J_Tetromino();
            case L_TETROMINO:
                return create_L_Tetromino();
            case S_TETROMINO:
                return create_S_Tetromino();
            case Z_TETROMINO:
                return create_Z_Tetromino();
        }
        return null;
    }

    private static Shape create_I_Tetromino(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockCyan.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.CYAN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.CYAN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex,1 , Direction.RIGHT);
        Tile tile3 = new Tile(tileSize, setColor, Color.CYAN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.LEFT);
        Tile tile4 = new Tile(tileSize, setColor, Color.CYAN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.RIGHT, Direction.RIGHT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        return new Shape(tiles);
    }

    private static Shape create_O_Tetromino(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockYellow.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex,1 , Direction.RIGHT);
        Tile tile3 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.RIGHT, Direction.DOWN);
        Tile tile4 = new Tile(tileSize, setColor, Color.YELLOW, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.DOWN);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        return new Shape(tiles);
    }

    private static Shape create_T_Tetromino(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockPurple.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.VIOLET, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.VIOLET, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex,1 , Direction.RIGHT);
        Tile tile3 = new Tile(tileSize, setColor, Color.VIOLET, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.LEFT);
        Tile tile4 = new Tile(tileSize, setColor, Color.VIOLET, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.DOWN);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        return new Shape(tiles);
    }

    private static Shape create_J_Tetromino(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockBlue.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);
        Tile tile3 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.RIGHT);
        Tile tile4 = new Tile(tileSize, setColor, Color.BLUE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.RIGHT,Direction.DOWN);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        return new Shape(tiles);
    }

    private static Shape create_L_Tetromino(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockOrange.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.ORANGE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.ORANGE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);
        Tile tile3 = new Tile(tileSize, setColor, Color.ORANGE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.RIGHT);
        Tile tile4 = new Tile(tileSize, setColor, Color.ORANGE, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.LEFT, Direction.DOWN);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        return new Shape(tiles);
    }

    private static Shape create_S_Tetromino(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockGreen.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.SPRINGGREEN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.SPRINGGREEN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex,1 , Direction.RIGHT);
        Tile tile3 = new Tile(tileSize, setColor, Color.SPRINGGREEN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.DOWN);
        Tile tile4 = new Tile(tileSize, setColor, Color.SPRINGGREEN, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.DOWN, Direction.LEFT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        return new Shape(tiles);
    }

    //TODO
    private static Shape create_Z_Tetromino(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockRed.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tile tile1 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex,1 , Direction.LEFT);
        Tile tile3 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.DOWN);
        Tile tile4 = new Tile(tileSize, setColor, Color.RED, setTileBorder, setImage, image, spawnColIndex,spawnRowIndex, 1 , Direction.DOWN, Direction.RIGHT);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        return new Shape(tiles);
    }

}
