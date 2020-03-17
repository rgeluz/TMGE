package com.INF122.Tetris;

import com.INF122.TMGE.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TetrisController extends Controller {
    Board board;
    Group tileGroup;

    int tileSize;
    private Shape currentActiveShape;
    private double time;
    int rowToBeRemovedIndex, score, lines;


    TextField playerNameField;
    TextField playerScoreField;
    TextField playerLineCountField;

    //For Testing Purposes
    final GameEnum GAME_TO_TEST;




    //TODO move this to tetris - added to tetris
    int tetrisLineCount;
    boolean includeTetrisBorder;

    /**
     * Constructor
     * @param board
     */
    public TetrisController(GameEnum GAME_TO_TEST,
                      Board board,
                      TextField playerNameField,
                      TextField playerScoreField,
                      TextField playerLineCountField) {
        super(GAME_TO_TEST, board, playerNameField, playerScoreField, playerLineCountField);
        this.GAME_TO_TEST = GAME_TO_TEST;
        this.board = board;
        this.playerNameField = playerNameField;
        this.playerScoreField = playerScoreField;
        this.playerLineCountField = playerLineCountField;
        this.playerNameField.setText("Player");
        this.playerScoreField.setText("0");
        this.playerLineCountField.setText("0");
        // tileSize has been outsourced to board, which is passed in to Tetris first
        this.tileSize = board.tileSize;
    }

    /**
     *
     * @return
     */
    public Group create(){

        System.out.println("");

        //JavaFX group
        Group group = new Group();
        this.tileGroup = group;

        //TODO for tetris - added to Tetris constructor
        this.tetrisLineCount = 0;
        this.includeTetrisBorder = true;
        if(this.includeTetrisBorder){
            addTetrisBorder();
        }


        //first spawn
        generateShape();
        render();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.017;

                if (time >= 0.5) {
                    moveShape(Direction.DOWN);
                    render();
                    time = 0;
                }
            }
        };
        timer.start();

        return this.tileGroup;
    }

    //TODO used for tetris border - copy added to Tetris
    private void addTetrisBorder(){
        Image image = null;
        try {
            image = new Image(new FileInputStream("resources/BlockGrey.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //create left and right wall
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
            if(colIndex==0 || colIndex==(this.board.gridWidth-1)){  //leftmost and rightmost columns
                for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++){
                    //create new tile
                    Tile newBoarderTile = new Tile(board.tileSize, false, null,false,
                            true, image, colIndex, rowIndex,
                            0,Direction.DOWN);
                    this.board.placeTile(newBoarderTile);
                }
            }
        }

        //create floor
        int floorRowIndex=this.board.gridHeight-1;
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
            Tile newBoarderTile = new Tile(board.tileSize, false, null,false,
                    true, image, colIndex, floorRowIndex,
                    0,Direction.DOWN);
            this.board.placeTile(newBoarderTile);
        }
    }

    /**
     *
     */
    public void render(){
        this.tileGroup.getChildren().clear();
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
            for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++){
                Tile tile = this.board.getTile(colIndex,rowIndex);
                if(tile!=null){
                    this.tileGroup.getChildren().add(tile.rectangle);
                }
            }
        }
    }

    /**
     *
     */

    public void rotateShape() {
        if(this.currentActiveShape.centerPieceRowIndex>0){ //don't allow rotation when shape is at the top of board
            List<Tile> newTileList = getNewOrientation(this.currentActiveShape.tiles);

            if (!(isColliding(newTileList))) {
                updateBoard(newTileList);
            }
        }
    }


    //gets new pieces for rotation
    public List<Tile> getNewOrientation(List<Tile> tiles){
        List<Tile> newTileList = new ArrayList<Tile>();
        for(Tile tile : tiles){
            List<Direction> newDirections = new ArrayList<>();
            for(Direction direction: tile.directions){
                Direction newDirection = direction.next();
                newDirections.add(newDirection);
            }

            Tile newTile = new Tile(tile.tileSize, tile.setColor, tile.tileColor, tile.setTileBorder,
                    tile.setImage, tile.tileImage,
                    tile.centerPieceColumnIndex, tile.centerPieceRowIndex, tile.position , newDirections);
            newTileList.add(newTile);
        }

        if(!isColliding(newTileList)) {
            //if the new tiles are not colliding with anything, return
            return newTileList;
        }
        else {
            //if there is a collision, try and offset left once or right once
            if(!isColliding(offsetTile(newTileList, Direction.LEFT))) {
                newTileList = offsetTile(newTileList, Direction.LEFT);
                return newTileList;
            } else if(!isColliding(offsetTile(newTileList, Direction.RIGHT))) {
                newTileList = offsetTile(newTileList, Direction.RIGHT);
                return newTileList;
            }
            else return getNewOrientation(newTileList);
        }
    }

    public List<Tile> offsetTile(List<Tile> tiles, Direction direction){
        //offsets the tile list in a direction
        int newColIndex = this.currentActiveShape.centerPieceColumnIndex + direction.colIndex;
        int newRowIndex = this.currentActiveShape.centerPieceRowIndex + direction.rowIndex;
        List<Tile> newTileList = new ArrayList<Tile>();
        for(Tile tile : tiles){
            Tile newTile = new Tile(tile.tileSize, tile.setColor, tile.tileColor, tile.setTileBorder,
                    tile.setImage, tile.tileImage,
                    newColIndex, newRowIndex, tile.position , tile.directions);
            newTileList.add(newTile);
        }
        return newTileList;
    }


    /**
     *
     * @param direction
     */
    public void moveShape(Direction direction) {

        //calculate new center tile coordinates
        int newColIndex = this.currentActiveShape.centerPieceColumnIndex + direction.colIndex;
        int newRowIndex = this.currentActiveShape.centerPieceRowIndex + direction.rowIndex;

        //Creating new shape
        List<Tile> newTileList = new ArrayList<Tile>();
        for(Tile tile : this.currentActiveShape.tiles){
            Tile newTile = new Tile(tile.tileSize, tile.setColor, tile.tileColor, tile.setTileBorder,
                    tile.setImage, tile.tileImage,
                    newColIndex, newRowIndex, tile.position , tile.directions);
            newTileList.add(newTile);
        }

        //Update Board if not out of bounds and no collision
        if(direction==Direction.LEFT || direction==Direction.RIGHT){
            if( !isLeftOrRightWall(newTileList) && !isColliding(newTileList) ){
                updateBoard(newTileList);
            }
        } else if(direction==Direction.DOWN){
            //check for out of bounds and collision
            if( !isBottom(newTileList) && !isColliding(newTileList) ){
                updateBoard(newTileList);
            } else {
                //spawn new shape
                checkAllRows();
                generateShape();
            }
            //checkForFullRows();
        }
    }


    /**
     *
     * @param newTileList
     */
    private void updateBoard(List<Tile> newTileList){
        //Remove old shape
        for(Tile tile : this.currentActiveShape.tiles){
            //Remove old tile from board
            this.board.removeTile(tile);

            //set old tiles to null
            tile = null;
        }

        this.currentActiveShape = null;

        //Create a new shape
        Shape newShape = new Shape(newTileList);
        this.currentActiveShape = newShape;

        //Update board
        for(Tile newTile: this.currentActiveShape.tiles){
            //Update board with new tile
            this.board.placeTile(newTile);
        }
    }

    /**
     *
     * @param newTileList
     * @return
     */
    public boolean isColliding(List<Tile> newTileList){
        boolean isCollidingWithSelf = false;
        for(Tile newTile: newTileList){
            Tile tile = this.board.getTile(newTile.columnIndex,newTile.rowIndex);
            if(tile!=null){
                for(Tile activeTile: this.currentActiveShape.tiles){
                    isCollidingWithSelf = false;
                    if((activeTile.rowIndex==newTile.rowIndex) && (activeTile.columnIndex==newTile.columnIndex)){
                        isCollidingWithSelf= true;
                        break;
                    }
                }
                //if its not colliding with itself
                //but there is a existing object,
                //then return isColliding=true
                if(!isCollidingWithSelf){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param newTileList
     * @return
     */
    public boolean isBottom(List<Tile> newTileList){
        for(Tile newTile: newTileList){
            if( newTile.rowIndex <0 || newTile.rowIndex >= board.gridHeight){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param newTileList
     * @return
     */
    public boolean isLeftOrRightWall(List<Tile> newTileList){
        for(Tile newTile: newTileList){
            if( newTile.columnIndex <=0 || newTile.columnIndex >= board.gridWidth-1){
                return true;
            }
        }
        return false;
    }

    public void calculateScore(int lines){
    	if (lines == 1) {
    		this.score += 40;
    	} else if (lines == 2) {
    		this.score += 100;
    	} else if (lines == 3) {
    		this.score += 300;
    	} else if (lines == 4) {
    		this.score += 1200;
    	}
    }
    
    public void checkAllRows(){
    	//first get the row indexes of filled rows
    	List<Integer> rowIndexes = getFilledRowIndexes(this.includeTetrisBorder);
    	
    	if (rowIndexes.size()>0) {
    		//if there are filled rows, first remove the filled rows
    		//then shift all other rows above it down
    		removeRowsFromIndex(rowIndexes, this.includeTetrisBorder);
    		int shiftAmount = rowIndexes.size();
    		int shiftIndex = rowIndexes.get(0);
    		this.lines += shiftAmount;
    		calculateScore(shiftAmount);
    		this.playerScoreField.setText(Integer.toString(this.score));
            this.playerLineCountField.setText(Integer.toString(this.lines));
			shiftRows(shiftIndex, shiftAmount, this.includeTetrisBorder);
    	}
    }
    
    
    public List<Integer> getFilledRowIndexes(boolean border){
    	
    	//initial border checking and calculations
    	int floorRowIndex, columnStartIndex, columnEndIndex, maxTileCount;
        if(border){
            floorRowIndex = this.board.gridHeight-1;
            columnStartIndex = 1;
            columnEndIndex = this.board.gridWidth-1;
            maxTileCount = columnEndIndex - columnStartIndex;
        }else {
            floorRowIndex = this.board.gridHeight;
            columnStartIndex = 0;
            columnEndIndex = this.board.gridWidth;
            maxTileCount = columnEndIndex - columnStartIndex;
        }
        
        //scans through every row, counts the number of tiles in each row, 
        //if the number of tiles == how many tiles should be in a row, then 
        // that row's index is added onto a list
        List <Integer> filledRows = new ArrayList<Integer>();
        for (int rowIndex = floorRowIndex-1; rowIndex >= 0; rowIndex--) {
        	int tileCount = 0;
        	for (int colIndex = columnStartIndex; colIndex<columnEndIndex; colIndex++) {
        		if (this.board.getTile(colIndex,rowIndex)!=null) {
        			tileCount++;
        		}
        	}
        	if (tileCount == maxTileCount) {
        		filledRows.add(rowIndex);
        	}
        }
        return filledRows;
    }
    
    public void removeRowsFromIndex(List<Integer> filledRowIndexes, boolean border) {
    	
    	//initial calculation and checking
    	int columnStartIndex, columnEndIndex; 
    	Tile currentTile;
        if(border){
            columnStartIndex = 1;
            columnEndIndex = this.board.gridWidth-1;
        }else {
            columnStartIndex = 0;
            columnEndIndex = this.board.gridWidth;
        }
        
        //iterates through the filled rows, takes the tiles there and removes them 
    	for (Integer rowIndex: filledRowIndexes) {
            for(int colIndex = columnStartIndex; colIndex<columnEndIndex; colIndex++){
            	currentTile = this.board.getTile(colIndex, rowIndex);
            	this.board.removeTile(currentTile);
            	currentTile = null;
            }
		}
    }
    
    public void shiftRows(int startIndex, int size, boolean border){
    	
    	//initial calculation and checking
    	int columnStartIndex, columnEndIndex; 
    	Tile currentTile;
    	if(border){
            columnStartIndex = 1;
            columnEndIndex = this.board.gridWidth-1;
        }else {
            columnStartIndex = 0;
            columnEndIndex = this.board.gridWidth;
        }
    	int holder = startIndex;
    	// iterates through the rows the rows above the lowest sweeped row
    	// then shifts all rows above it down
    	for (int rowIndex = startIndex-1; rowIndex >= 0; rowIndex--) {
    		int newRowIndex = holder;
    		//iterates through each column and shifts it down to the next index
    		if (rowNotNull(rowIndex, columnStartIndex, columnEndIndex)){
				for(int colIndex = columnStartIndex; colIndex<columnEndIndex; colIndex++){
					currentTile = this.board.getTile(colIndex, rowIndex);
					if (currentTile != null) {
						currentTile.changeIndex(colIndex, newRowIndex);
						this.board.placeTile(currentTile);
						this.board.placeNull(colIndex, rowIndex);
					}
					else {
						this.board.placeNull(colIndex, rowIndex);
						this.board.placeNull(colIndex, newRowIndex);
					}
					currentTile = null;
				}
				holder--;
            }
    	}
    }
    
    public boolean rowNotNull(int index, int colStart, int colEnd) {
    	for(int colIndex = colStart; colIndex<colEnd; colIndex++){
			Tile currentTile = this.board.getTile(colIndex, index);
			if (currentTile != null) {
				return true;
			}
    	}
		return false;
    }
    



    /**
     * Spawns new shape from top of grid
     */
    public void generateShape() {

        //TODO for tetris game - copy added to Tetris
        Shape newShape = null;
        newShape = TetrisShapeFactory.getRandomShape(this.board);


        //set newly created shape as the currently active shape
        this.currentActiveShape = newShape;

        //check if spawn area is occupied
        boolean isOccupied =false;
        for (Tile newTile : this.currentActiveShape.tiles) {
            if(this.board.getTile(newTile.columnIndex,newTile.rowIndex)!=null){
                isOccupied = true;
            }
        }

        //TODO
        //check if shape reaches top
        if(!isOccupied){
            //add tiles to board
            for (Tile newTile : this.currentActiveShape.tiles) {
                this.board.placeTile(newTile);
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

            //Text t = new Text();
            //t.setX(20.0f);
            //t.setY(65.0f);
            //t.setX(100);
            //t.setY(200);
            //t.setText("Perspective");
            //t.setFill(Color.YELLOW);
            //t.setFont(Font.font(null, FontWeight.BOLD, 36));
            //this.group.getChildren().add(t);
            //this.pane.getChildren().add(t);

            //System.exit(0);
        }

    }

}
