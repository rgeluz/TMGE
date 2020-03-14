package com.INF122.TMGE;

import com.INF122.TMGE.tetris.TetrisShapeFactory;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    Board board;
    Group group;
    //int tileSize;
    private Shape currentActiveShape;
    private double time;

    //TODO move this to tetris - added to tetris
    int tetrisLineCount;
    boolean includeTetrisBorder;

    /**
     * Constructor
     * @param board
     */
    public Controller(Board board) {
        this.board = board;
        // tileSize has been outsourced to board, which is passed in to Tetris first
       // this.tileSize = board.tileSize;
    }

    /**
     *
     * @return
     */
    public Group create(){

        System.out.println("");

        //JavaFX group
        Group group = new Group();
        this.group = group;

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
                    checkForFullRows();
                    render();
                    time = 0;
                }
            }
        };
        timer.start();

        return group;
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
                    //this.board.boardGrid[colIndex][rowIndex]=newBoarderTile;
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
            //this.board.boardGrid[colIndex][floorRowIndex]=newBoarderTile;
            this.board.placeTile(newBoarderTile);
        }
        render();
    }

    /**
     *
     */
    public void render(){
        this.group.getChildren().clear();
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
            for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++){
                //if(this.board.boardGrid[colIndex][rowIndex]!=null){
                    //System.out.println(this.board.boardGrid[colIndex][rowIndex].rectangle.);
                    //this.group.getChildren().add(this.board.boardGrid[colIndex][rowIndex].rectangle);
                //}
                Tile tile = this.board.getTile(colIndex,rowIndex);
                if(tile!=null){
                    this.group.getChildren().add(tile.rectangle);
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
                generateShape();
            }
            checkForFullRows();
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
    

    //TODO for tetris
    public void checkForFullRows(){
        //TODO this is only for tetris, need to move outside
        //check for any full rows
        List<List<Tile>> listOfFullRows = getFullRows(this.includeTetrisBorder);
        if(listOfFullRows.size()>0){
            removeFullRowTiles(listOfFullRows);
            render();
            shiftDownTiles(this.includeTetrisBorder);
        }
    }

    //TODO for tetris - copy added to Tetris
    public List<List<Tile>> getFullRows(boolean isBorder){
        List<List<Tile>> listOfFullRows = new ArrayList<List<Tile>>();


        int floorRowIndex;
        if(isBorder){
            floorRowIndex = this.board.gridHeight-2;
        }else {
            floorRowIndex = this.board.gridHeight-1;
        }
        for(int rowIndex=0; rowIndex<=floorRowIndex; rowIndex++){
            int tileCount=0;
            List<Tile> tilesInRow = new ArrayList<Tile>();
            for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
                if(this.board.boardGrid[colIndex][rowIndex]!=null){
                    tileCount++;
                }
                tilesInRow.add(this.board.boardGrid[colIndex][rowIndex]);
            }
            /**/
            if(tileCount>=this.board.gridWidth){
                this.tetrisLineCount++;
                listOfFullRows.add(tilesInRow);
                System.out.println("line count: " + this.tetrisLineCount);
                System.out.println("FULL ROW");
                //return true;
            }
        }
        //return false;
        return listOfFullRows;
    }

    //TODO for tetris - copy added to Tetris
    private void removeFullRowTiles(List<List<Tile>> listOfFullRows){
        for(List<Tile> tilesInRow : listOfFullRows){
            for(Tile tile : tilesInRow){
                //Remove old tile from board
                this.board.removeTile(tile);

                //set old tiles to null
                tile = null;
            }
        }
    }

    //TODO for tetris - copy added to Tetris
    private void shiftDownTiles(boolean isBorder){
        List<Tile> listOfTilesToUpdate = new ArrayList<Tile>();



        for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++){
            for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
                if(this.board.boardGrid[colIndex][rowIndex]!=null){ //tile exist at coordinates
                    Tile tile = this.board.boardGrid[colIndex][rowIndex];
                    //calculate new center tile coordinates
                    //Direction direction = Direction.DOWN;
                    //int newColIndex = tile.columnIndex + direction.colIndex;
                    //int newRowIndex = tile.rowIndex + direction.rowIndex;
                    int newRowIndex = tile.rowIndex + 1;
                    tile.rowIndex = newRowIndex;
                    listOfTilesToUpdate.add(tile);
                }
            }
        }

        //Clear Board Grid. Set every element in 2D array to null.
        for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++){
            for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++){
                this.board.boardGrid[colIndex][rowIndex]=null;
            }
        }

        //add back tetris border
        if(this.includeTetrisBorder) {
            addTetrisBorder();
        }

        //Insert new tiles
        for(Tile newTile : listOfTilesToUpdate){
            this.board.placeTile(newTile);
        }
    }



    /**
     * Spawns new shape from top of grid
     */
    public void generateShape() {

        //TODO for tetris game - copy added to Tetris
        Shape newShape = TetrisShapeFactory.getRandomShape(this.board);

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

            Text t = new Text();
            t.setX(20.0f);
            t.setY(65.0f);
            t.setText("Perspective");
            t.setFill(Color.YELLOW);
            t.setFont(Font.font(null, FontWeight.BOLD, 36));
            this.group.getChildren().add(t);

            System.exit(0);
        }

    }

}