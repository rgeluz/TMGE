package com.INF122.DrMario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.INF122.TMGE.*;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DRMController  {
	Board board;
    Group group;
    int tileSize;
    private Pills currentActiveShape;
    private double time;

    TextField playerNameField;
    TextField playerScoreField;
    TextField playerLineCountField;


    //TODO move this to tetris - added to tetris
    int LineCount;
    boolean includeTetrisBorder;

    /**
     * Constructor
     * @param board
     */
    /*public DRMController(Board board)
    {
        this.board = board;
        // tileSize has been outsourced to board, which is passed in to Tetris first
       // this.tileSize = board.tileSize;
    }*/
    public DRMController(Board board,
                         TextField playerNameField,
                         TextField playerScoreField,
                         TextField playerLineCountField){
        this.board = board;
        this.playerNameField = playerNameField;
        this.playerScoreField = playerScoreField;
        this.playerLineCountField = playerLineCountField;
        this.playerNameField.setText("Player");
        this.playerScoreField.setText("0");
        this.playerLineCountField.setText("0");
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
        this.group = group;

        this.LineCount = 0;
        addBorder();

        //first spawn
        Levels lvl = new Levels(this.board);
        lvl.getLvl(1);
        generateShape();
        render();

        AnimationTimer timer = new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                time += 0.017;

                if (time >= 0.5) 
                {
                    moveShape(Direction.DOWN);
//                  checkForFullRows();
                    render();
                    time = 0;
                }
            }
        };
        timer.start();

        return group;
    }
    
    
    private void addBorder(){
        Image image = null;
        try 
        {
            image = new Image(new FileInputStream("DrMario-module/resources/BlockGrey.png"));
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        //create left and right wall
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++)
        {
            if(colIndex==0 || colIndex==(this.board.gridWidth-1))
            {  //leftmost and rightmost columns
                for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++)
                {
                    //create new tile
                    /*Tile newBoarderTile = new Tile(board.tileSize, false, null, false,
                            true, image, colIndex, rowIndex,
                            0, 4, Direction.DOWN);*/
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
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++)
        {
            /*Tile newBoarderTile = new Tile(board.tileSize, false, null,false,
                    true, image, colIndex, floorRowIndex,
                    0, 4, Direction.DOWN);*/
            Tile newBoarderTile = new Tile(board.tileSize, false, null,false,
                    true, image, colIndex, floorRowIndex,
                    0,Direction.DOWN);
            //this.board.boardGrid[colIndex][floorRowIndex]=newBoarderTile;
            this.board.placeTile(newBoarderTile);
        }
        render();
    }
    
    public void render()
    {
        this.group.getChildren().clear();
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++)
        {
            for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++)
            {
                //if(this.board.boardGrid[colIndex][rowIndex]!=null){
                    //System.out.println(this.board.boardGrid[colIndex][rowIndex].rectangle.);
                    //this.group.getChildren().add(this.board.boardGrid[colIndex][rowIndex].rectangle);
                //}
                Tile tile = this.board.getTile(colIndex,rowIndex);
                if(tile!=null)
                    this.group.getChildren().add(tile.rectangle);
            }
        }
    }

    public boolean isColliding(List<Capsule> newTileList){
        boolean isCollidingWithSelf = false;
        for(Capsule newTile: newTileList){
        	Tile tile = this.board.getTile(newTile.columnIndex,newTile.rowIndex);
            if(tile!=null){
                for(Capsule activeTile: this.currentActiveShape.caps)
                {
                    isCollidingWithSelf = false;
                    if((activeTile.rowIndex==newTile.rowIndex) && (activeTile.columnIndex==newTile.columnIndex))
                    {
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
    public boolean isBottom(List<Capsule> newTileList)
    {
        for(Capsule newTile: newTileList)
        {
            if( newTile.rowIndex <0 || newTile.rowIndex >= board.gridHeight)
                return true;
        }
        return false;
    }

    /**
     *
     * @param newTileList
     * @return
     */
    public boolean isLeftOrRightWall(List<Capsule> newTileList)
    {
        for(Capsule newTile: newTileList)
        {
            if( newTile.columnIndex <0 || newTile.columnIndex >= board.gridWidth)
                return true;
        }
        return false;
    }
    
    private void updateBoard(List<Capsule> newTileList)
    {
        //Remove old shape
        for(Tile tile : this.currentActiveShape.caps)
        {
            //Remove old tile from board
            this.board.removeTile(tile);

            //set old tiles to null
            tile = null;
        }

        this.currentActiveShape = null;

        //Create a new shape
        Pills newPill = new Pills(newTileList);
        this.currentActiveShape = newPill;

        //Update board
        for(Tile newTile: this.currentActiveShape.caps)
        {
            //Update board with new tile
            this.board.placeTile(newTile);
        }
    }
    
    public void moveShape(Direction direction) 
    {
        //calculate new center tile coordinates
        int newColIndex = this.currentActiveShape.centerPieceColumnIndex + direction.colIndex;
        int newRowIndex = this.currentActiveShape.centerPieceRowIndex + direction.rowIndex;

        //Creating new shape
        List<Capsule> newTileList = new ArrayList<Capsule>();
//        int mov = 1;
        for(Capsule tile : this.currentActiveShape.caps)
        {
        	Capsule newTile = new Capsule(tile.tileSize, tile.setColor, tile.tileColor, tile.setTileBorder,
                                    tile.setImage, tile.tileImage,
                                    newColIndex, newRowIndex, tile.position, tile.directions, tile.type);
            newTileList.add(newTile);
        }

        //Update Board if not out of bounds and no collision
        if(direction==Direction.LEFT || direction==Direction.RIGHT)
        {
            if( !isLeftOrRightWall(newTileList) && !isColliding(newTileList))
                updateBoard(newTileList);
        } 
        else if(direction==Direction.DOWN)
        {
            //check for out of bounds and collision
            if( !isBottom(newTileList) && !isColliding(newTileList))
        		updateBoard(newTileList);

            else 
            { 
            	for(Tile t: newTileList)
        		{
        			List<Tile> match = checkFourCaps(t);
        			if(!match.isEmpty())
        			{
        				for(Tile c: match)
        					board.removeTile(c);
        			}
        		}
            	render();
            	
            	System.out.println("toRemove");
            	
            	List<Tile> move = this.isSingleCap();
            	if(!move.isEmpty())
            	{
            		for(Tile t: move)
            			this.moveCapDown((Capsule) t);
            	}
                generateShape();
         		
//            checkForFullRows();
            }
        }
    }
    
    public void rotateShape()
    {
        if(this.currentActiveShape.centerPieceRowIndex>0)
        { //don't allow rotation when shape is at the top of board
            List<Capsule> newTileList = new ArrayList<Capsule>();
            for(Capsule tile : this.currentActiveShape.caps)
            {
                List<Direction> newDirections = new ArrayList<>();
                for(Direction direction: tile.directions)
                {
                    Direction newDirection = direction.next();
                    newDirections.add(newDirection);
                }

                Capsule newTile = new Capsule(tile.tileSize, tile.setColor, tile.tileColor, tile.setTileBorder,
                        tile.setImage, tile.tileImage,
                        tile.centerPieceColumnIndex, tile.centerPieceRowIndex, tile.position , newDirections, tile.type);
                newTileList.add(newTile);

            }

            for(Tile tile : this.currentActiveShape.caps)
            {
                //Remove old tile from board
                //this.board.boardGrid[tile.columnIndex][tile.rowIndex]=null;
                this.board.removeTile(tile);

                //set old tiles to null
                tile = null;
            }

            this.currentActiveShape = null;

            //Create a new shape
            Pills newShape = new Pills(newTileList);
            this.currentActiveShape = newShape;

            //Update board
            for(Tile newTile: this.currentActiveShape.caps)
            {
                //Update board with new tile
                //this.board.boardGrid[newTile.columnIndex][newTile.rowIndex]=newTile;
                this.board.placeTile(newTile);
            }
        }
    }
    
    public void generateShape() 
    {
        //TODO for tetris game - copy added to Tetris
        Pills newShape = Pills.getPill(this.board);

        //set newly created shape as the currently active shape
        this.currentActiveShape = newShape;

        //check if spawn area is occupied
        boolean isOccupied =false;
        for (Tile newTile : this.currentActiveShape.caps) 
        {
            if(this.board.getTile(newTile.columnIndex,newTile.rowIndex)!=null)
                isOccupied = true;
        }

        //TODO
        //check if shape reaches top
        if(!isOccupied)
        {
            //add tiles to board
            for (Tile newTile : this.currentActiveShape.caps) 
                this.board.placeTile(newTile);
        } 
        else 
        {
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
            //t.setText("Perspective");
            //t.setFill(Color.YELLOW);
            //t.setFont(Font.font(null, FontWeight.BOLD, 36));
            //this.group.getChildren().add(t);

            //System.exit(0);
        }
    }
    
    private List<Tile> checkFourCaps(Tile t) 
    {
    	int col = t.columnIndex;
    	int row = t.rowIndex;
    	List<Tile> toClear = new ArrayList<Tile>();
    	
    	for( int r =0; r < this.board.gridHeight-1; r++)
    	{
    		if (r < this.board.gridHeight-4 && this.board.getTile(col, r) != null)
    		{    		
    			toClear.add(this.board.getTile(col, r));
    			for (int c = 1; c < 5; c++)
    			{
    				if(this.board.getTile(col, c+r)!= null && this.board.getTile(col, c+r).type != 4 && 
    				   this.board.getTile(col, c+r).type == this.board.getTile(col, r).type)
    					toClear.add(this.board.getTile(col, c+r));
    				
    				else
    				{
    					if (c > 3)
    						return toClear;
    					else
    					{
    						toClear.clear();
    						break;
    					}
    				}
    			}
	    	}
    	}
    	
    	for( int c = 1; c < this.board.gridWidth-2; c++)
    	{
    		if (c < this.board.gridWidth-4 && this.board.getTile(c, row) != null)
    		{    		
    			toClear.add(this.board.getTile(c, row));
    			for (int i = 1; i < 5; i++)
    			{
//    				System.out.println("c: " + c +" i: " + i + " row: " + row );
    				if(this.board.getTile(c+i, row)!= null && this.board.getTile(c+i, row).type != 4 &&
    				   this.board.getTile(c+i, row).type == this.board.getTile(c, row).type)
    					toClear.add(this.board.getTile(c+i, row));
    				else
    				{
    					if (i > 3)
    						return toClear;
    					else
    					{
    						toClear.clear();
    						break;
    					}
    				}
    			}
	    	}
    	}
    	
    	return toClear;
    }
    
    private List<Tile> isSingleCap()
    {
    	List<Tile> toMove = new ArrayList<Tile>();
        for(int colIndex=0; colIndex<this.board.gridWidth; colIndex++)
        {
            for(int rowIndex=0; rowIndex<this.board.gridHeight; rowIndex++)
            {
            	Tile toCheck = this.board.getTile(colIndex, rowIndex);
            	if( toCheck != null && toCheck.isCapsule() &&
            			toCheck.columnIndex >= 1 && toCheck.columnIndex <= board.gridWidth -2)
            	{
                    if(toCheck.rowIndex >= 0 && toCheck.rowIndex <= board.gridHeight -2)
                    {
                    	if(this.board.getTile(toCheck.columnIndex-1, toCheck.rowIndex  ) == null &&  //left
                    	   this.board.getTile(toCheck.columnIndex+1, toCheck.rowIndex  ) == null &&  //right
                    	   this.board.getTile(toCheck.columnIndex,   toCheck.rowIndex+1) == null)    //down
                    		toMove.add(toCheck);
                    }
            	}
            }
        }
        return toMove;
    }
    
    private void moveCapDown(Capsule tile)
    {
        int newColIndex = tile.columnIndex + Direction.DOWN.colIndex;
        int newRowIndex = tile.rowIndex + Direction.DOWN.rowIndex;

        //Creating new shape
    	Capsule newTile = new Capsule(tile.tileSize, tile.setColor, tile.tileColor, tile.setTileBorder,
                                tile.setImage, tile.tileImage,
                                newColIndex, newRowIndex, tile.position, tile.directions, tile.type);
    	board.removeTile(tile);
    	board.placeTile(newTile);
    	render();
    	
        //Update Board if not out of bounds and no collision
        //check for out of bounds and collision
        while(newTile.rowIndex != board.gridHeight-2 || board.getTile(newTile.columnIndex, newTile.rowIndex+1) == null)
        {
        	System.out.println("Moving");
            int newRow = newTile.rowIndex + 1;

            //Creating new shape
        	Capsule oldTile = newTile;
            newTile = new Capsule(tile.tileSize, tile.setColor, tile.tileColor, tile.setTileBorder,
                                    tile.setImage, tile.tileImage,
                                    newTile.columnIndex, newRow, tile.position, tile.directions, tile.type);
        	board.removeTile(oldTile);
        	board.placeTile(newTile);
        	render();
        }
        tile = null;
    }
}
