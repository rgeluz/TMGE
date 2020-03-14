package com.INF122.TMGE.tetris;

import com.INF122.TMGE.*;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Tetris {
    int tetrisLineCount;
    boolean includeTetrisBorder;
    Controller c;
    Group g;
    Pane p;
    Board b;

    TextField playerNameField;
    TextField playerScoreField;
    TextField playerLineCountField;

    // TODO - refactor Controller so duplicate arguments are removed and instead passed in by this class
    // TODO - refactor tileSize that is now based on Board.tileSize
    private Shape currentActiveShape;
    private double time;
    final GameEnum GAME_TO_TEST = GameEnum.TETRIS;


    Tetris(Board bo,
           TextField playerNameField,
           TextField playerScoreField,
           TextField playerLineCountField) {
        this.tetrisLineCount = 0;
        this.includeTetrisBorder = true;
        this.b = bo;
        this.playerNameField = playerNameField;
        this.playerScoreField = playerScoreField;
        this.playerLineCountField = playerLineCountField;
        c = new Controller(GAME_TO_TEST,
                        bo,
                        playerNameField,
                        playerScoreField,
                        playerLineCountField);
        if(this.includeTetrisBorder){
            g = c.create();
            //p = c.create();
        }
    }
    //TODO take private methods from Controller and refactor them into Tetris

    private void addTetrisBorder(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockGrey.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //create left and right wall
        for(int colIndex=0; colIndex<this.b.gridWidth; colIndex++){
            if(colIndex==0 || colIndex==(this.b.gridWidth-1)){  //leftmost and rightmost columns
                for(int rowIndex=0; rowIndex<this.b.gridHeight; rowIndex++){
                    //create new tile
                    Tile newBoarderTile = new Tile(b.tileSize, false, null,false,
                            true, image, colIndex, rowIndex,
                            0, Direction.DOWN);
                    this.b.boardGrid[colIndex][rowIndex]=newBoarderTile;
                }
            }
        }

        //create floor
        int floorRowIndex=this.b.gridHeight-1;
        for(int colIndex=0; colIndex<this.b.gridWidth; colIndex++){
            Tile newBoarderTile = new Tile(b.tileSize, false, null,false,
                    true, image, colIndex, floorRowIndex,
                    0,Direction.DOWN);
            this.b.boardGrid[colIndex][floorRowIndex]=newBoarderTile;
        }
        render();
    }

    public void render(){
        //this.g.getChildren().clear();
        this.p.getChildren().clear();
        for(int colIndex=0; colIndex<this.b.gridWidth; colIndex++){
            for(int rowIndex=0; rowIndex<this.b.gridHeight; rowIndex++){
                if(this.b.boardGrid[colIndex][rowIndex]!=null){
                    //System.out.println(this.board.boardGrid[colIndex][rowIndex].rectangle.);
                    //this.g.getChildren().add(this.b.boardGrid[colIndex][rowIndex].rectangle);
                    this.p.getChildren().add(this.b.boardGrid[colIndex][rowIndex].rectangle);
                }
            }
        }
    }

    public void checkForFullRows(){
        //TODO this is only for tetris, need to move outside
        //check for any full rows
        List<List<Tile>> listOfFullRows = getFullRows(this.includeTetrisBorder);
        if(listOfFullRows.size()>0){
            removeFullRowTiles(listOfFullRows);
            shiftDownTiles(this.includeTetrisBorder);
            //render();
        }
    }

    public List<List<Tile>> getFullRows(boolean isBorder){
        List<List<Tile>> listOfFullRows = new ArrayList<List<Tile>>();


        int floorRowIndex;
        if(isBorder){
            floorRowIndex = this.b.gridHeight-2;
        }else {
            floorRowIndex = this.b.gridHeight-1;
        }
        for(int rowIndex=0; rowIndex<=floorRowIndex; rowIndex++){
            int tileCount=0;
            List<Tile> tilesInRow = new ArrayList<Tile>();
            for(int colIndex=0; colIndex<this.b.gridWidth; colIndex++){
                if(this.b.boardGrid[colIndex][rowIndex]!=null){
                    tileCount++;
                }
                tilesInRow.add(this.b.boardGrid[colIndex][rowIndex]);
            }
            /**/
            if(tileCount>=this.b.gridWidth){
                this.tetrisLineCount++;
                listOfFullRows.add(tilesInRow);
                System.out.println("line count: " + this.tetrisLineCount);
                System.out.println("FULL ROW");

                //update player line count:
                this.playerLineCountField.setText(Integer.toString(this.tetrisLineCount) );
                //return true;
            }
        }
        //return false;
        return listOfFullRows;
    }

    private void removeFullRowTiles(List<List<Tile>> listOfFullRows){
        for(List<Tile> tilesInRow : listOfFullRows){
            for(Tile tile : tilesInRow){
                //Remove old tile from board
                this.b.boardGrid[tile.columnIndex][tile.rowIndex]=null;

                //set old tiles to null
                tile = null;
            }
        }
    }

    private void shiftDownTiles(boolean isBorder){
        List<Tile> listOfTilesToUpdate = new ArrayList<Tile>();



        for(int rowIndex=0; rowIndex<this.b.gridHeight; rowIndex++){
            for(int colIndex=0; colIndex<this.b.gridWidth; colIndex++){
                if(this.b.boardGrid[colIndex][rowIndex]!=null){ //tile exist at coordinates
                    Tile tile = this.b.boardGrid[colIndex][rowIndex];
                    //calculate new center tile coordinates
                    //Direction direction = Direction.DOWN;
                    //int newColIndex = tile.columnIndex + direction.colIndex;
                    //int newRowIndex = tile.rowIndex + direction.rowIndex;
                    int newRowIndex = tile.rowIndex + 1;

                    Tile newTile = new Tile(tile.tileSize, tile.setColor, tile.tileColor,tile.setTileBorder,
                            tile.setImage, tile.tileImage,
                            tile.columnIndex, newRowIndex, tile.position, tile.directions);
                    listOfTilesToUpdate.add(newTile);
                }
            }
        }

        //Clear Board Grid. Set every element in 2D array to null.
        for(int rowIndex=0; rowIndex<this.b.gridHeight; rowIndex++){
            for(int colIndex=0; colIndex<this.b.gridWidth; colIndex++){
                this.b.boardGrid[colIndex][rowIndex]=null;
            }
        }

        //add back tetris border
        if(this.includeTetrisBorder) {
            addTetrisBorder();
        }

        //Insert new tiles
        for(Tile newTile : listOfTilesToUpdate){
            this.b.boardGrid[newTile.columnIndex][newTile.rowIndex]=newTile;
        }
    }

    public void generateShape() {

        //TODO for tetris game - copy added to Tetris
        Shape newShape = TetrisShapeFactory.getRandomShape(this.b);

        //set newly created shape as the currently active shape
        this.currentActiveShape = newShape;

        //check if spawn area is occupied
        boolean isOccupied =false;
        for (Tile newTile : this.currentActiveShape.tiles) {
            if(this.b.boardGrid[newTile.columnIndex][newTile.rowIndex]!=null){
                isOccupied = true;
            }
        }

        //TODO
        //check if shape reaches top
        if(!isOccupied){
            //add tiles to board
            for (Tile newTile : this.currentActiveShape.tiles) {
                this.b.boardGrid[newTile.columnIndex][newTile.rowIndex] = newTile;
            }
        } else {
            //TODO later add Game Over JavaFx message
            System.out.println("GAME OVER");

            //TODO Finishlater
            //Text gameoverText = new Text(10,20,"GAME OVER");
            //gameoverText.setFill(Color.RED);
            //gameoverText.setX( 100/*( ((board.gridWidth-1)*tileSize)/2)*/ );
            //gameoverText.setY( 100/*( ((board.gridHeight-1)*tileSize)/2)*/ );
            //gameoverText.setStyle("-fx-font: 70 arial;");
            //this.group.getChildren().add(gameoverText);

            Text t = new Text();
            t.setX(20.0f);
            t.setY(65.0f);
            t.setText("Perspective");
            t.setFill(Color.YELLOW);
            t.setFont(Font.font(null, FontWeight.BOLD, 36));
            //this.g.getChildren().add(t);
            this.p.getChildren().add(t);

            System.exit(0);
        }

    }


}
