package com.INF122.TMGE;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Controller {

    Board board;
    private List<Shape> prototypeShapes = new ArrayList<Shape>();
    private Shape currentActiveShape;
    private double time;

    public Controller(Board board) {
        this.board = board;
    }

    /*public void addProtoypeShape(Tile... tiles){
      Shape shape = new Shape(tiles);
      prototypeShapes.add(shape);
    }*/

    public Group create(){

        System.out.println();

        //Pane pane = new Pane();
        //root.setPrefSize(GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE);
        //parent.setPrefSize(this.board.gridWidth * this.board.tileSize,
                              //this.board.gridHeight * this.board.tileSize
                            //);
        Group group = new Group();

        //Canvas canvas = new Canvas(GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE);
        //g = canvas.getGraphicsContext2D();

        //root.getChildren().addAll(canvas);

        //spawn
        generateShape();
        render(group);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.017;

                if (time >= 0.5) {
                    //moveShape(Direction.DOWN);
                    moveShape(Direction.RIGHT);
                    //moveShape(Direction.LEFT);
                    render(group);
                    time = 0;
                }
            }
        };
        timer.start();


        return group;
    }

    public void render(Group group){
        group.getChildren().clear();
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
            for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++){
                if(this.board.boardGrid[colIndex][rowIndex]!=null){
                    //System.out.println(this.board.boardGrid[colIndex][rowIndex].rectangle.);
                    group.getChildren().add(this.board.boardGrid[colIndex][rowIndex].rectangle);

                }
            }

        }
    }



    public void addPrototypeShape() {

    }


    public void moveShape(Direction direction) {

        //check for out bounds

        //calculate new center tile coordinates
        int newColIndex = this.currentActiveShape.centerPieceColumnIndex += direction.colIndex;
        int newRowIndex = this.currentActiveShape.centerPieceRowIndex += direction.rowIndex;



          //Creating new shape
        List<Tile> newTileList = new ArrayList<Tile>();
        for(Tile tile : this.currentActiveShape.tiles){
            Tile newTile = new Tile(25, Color.BLUE, newColIndex, newRowIndex, tile.position , tile.directions);
            newTileList.add(newTile);

            //Remove old tile from board
            this.board.boardGrid[tile.columnIndex][tile.rowIndex]=null;


            //set old tiles to null
            tile = null;
        }

        this.currentActiveShape = null;

        //Create a new shape
        Shape newShape = new Shape(newTileList);
        this.currentActiveShape = newShape;

        for(Tile newTile: this.currentActiveShape.tiles){
            //Update board with new tile
            this.board.boardGrid[newTile.columnIndex][newTile.rowIndex]=newTile;
        }

    }


    /*
       Spawns new shape from top of grid
    */
    public void generateShape() {

        Tile tile1 = new Tile(25, Color.BLUE, (this.board.gridWidth-1)/2, 0, 0 , Direction.DOWN); //Center Tile
        Tile tile2 = new Tile(25, Color.BLUE, (this.board.gridWidth-1)/2, 0,1 , Direction.RIGHT);
        Tile tile3 = new Tile(25, Color.BLUE,(this.board.gridWidth-1)/2, 0, 1 , Direction.LEFT);
        Tile tile4 = new Tile(25, Color.BLUE,(this.board.gridWidth-1)/2, 0, 1 , Direction.DOWN);

        List<Tile> tiles = new ArrayList<Tile>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);


        //int randomIndex = new Random().nextInt(this.prototypeShapes.size());
        //Shape shape = this.prototypeShapes.get(randomIndex).generateShape();
        //Shape shape = Shape.generateShape();

        Shape shape = new Shape(tiles);

        //start shape at top
        //shape.move(this.gridWidth / 2); //TODO later

        //set newly created shape as the currently active shape
        this.currentActiveShape = shape;

        //add tiles to board
        for (Tile tile : shape.tiles) {
            this.board.boardGrid[tile.columnIndex][tile.rowIndex] = tile;
        }

        //TODO
        //check if shape reaches top
        //if(){
        //TODO later add Game Over message
        //}


    }
}