package com.INF122.TMGE.DRM;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.INF122.TMGE.*;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Pills 
{
	private int type;
	List<Tile> caps;
	private static boolean setColor = false;
	private static boolean setTileBorder = false;
	private static boolean setImg = true;
    public int centerPieceColumnIndex;
    public int centerPieceRowIndex;
	public static int rotateStatus = 0;
    
	Pills(List<Tile> c)
	{
		caps = c;
        this.centerPieceColumnIndex = caps.get(0).columnIndex;
        this.centerPieceRowIndex = caps.get(0).rowIndex;
	}
	
	void change_rotate()
	{
		if (rotateStatus >= 3)
			rotateStatus = 0;
		else
			rotateStatus++;
	}
	
	public static Pills getPill(Board b)
	{
		int w = b.gridWidth;
		int sz = b.tileSize;
		List<Tile> caps = new ArrayList<Tile>(); 

		for(int i = 0; i < 2; i++)
		{
			int pick = (int) (Math.random()*30);
			if(pick <= 10)
				caps.add(createBlue(b, i));
			else if(pick > 10 && pick <= 20)
				caps.add(createRed(b, i));
			
			else if(pick > 20)
				caps.add(createYellow(b, i));
		}
		return new Pills(caps);
	}

	private static Tile createBlue(Board b, int i)
	{
        Image image = null;
        try 
        {
            image = new Image(new FileInputStream("resources/BlockBlue.png"));
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
		Tile cap = new Tile(b.tileSize, setColor, Color.BLUE, setTileBorder, setImg, image, b.gridWidth/2, 0, i, Direction.DOWN);
		return cap;
	}
	
	private static Tile createRed(Board b, int i)
	{
        Image image = null;
        try 
        {
            image = new Image(new FileInputStream("resources/BlockRed.png"));
        } catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
		Tile cap = new Tile(b.tileSize, setColor, Color.RED, setTileBorder, setImg, image, b.gridWidth/2, 0, i, Direction.DOWN);
		return cap;
	}
	
	private static Tile createYellow(Board b, int i)
	{
        Image image = null;
        try 
        {
            image = new Image(new FileInputStream("resources/BlockYellow.png"));
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
		Tile cap = new Tile(b.tileSize, setColor, Color.YELLOW, setTileBorder, setImg, image, b.gridWidth/2, 0, i, Direction.DOWN);
		return cap;
	}
}
