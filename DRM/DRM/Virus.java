package com.INF122.TMGE.DRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.INF122.TMGE.*;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Virus extends Tile
{	
	public Virus(int tileSize, boolean setColor, Color color, boolean setTileBorder, boolean setImage, Image image,
			int cenColIndex, int cenRowIndex, int position, Direction directions, int t) 
	{
		super(tileSize, setColor, color, setTileBorder, setImage, image, cenColIndex, cenRowIndex, position, t, directions);
	}
	
	public static Virus getVirus(Board b, int t, int col, int row)
	{
		Image image = null;
		Virus v = null;
		switch (t)
		{
			case 1:
		        try 
		        {   
		        	image = new Image(new FileInputStream("resources/VirusBlue.png"));
		        	v = new Virus(b.tileSize, false, Color.BLUE, false, true, image, col, row, 0, Direction.DOWN, 1);
		        }
		        catch (FileNotFoundException e) 
		        {  	e.printStackTrace();}
				break;
			case 2:
		        try 
		        {   
		        	image = new Image(new FileInputStream("resources/VirusRed.png"));
		        	v = new Virus(b.tileSize, false, Color.RED, false, true, image, col, row, 0, Direction.DOWN, 2);
		        }
		        catch (FileNotFoundException e) 
		        {  	e.printStackTrace();}
				break;
			case 3:
		        try 
		        {   
		        	image = new Image(new FileInputStream("resources/VirusYellow.png"));
		        	v = new Virus(b.tileSize, false, Color.YELLOW, false, true, image, col, row, 0, Direction.DOWN, 3);
		        }
		        catch (FileNotFoundException e) 
		        {  	e.printStackTrace();}
				break;
		}	
		return v;
	}
	
	@Override
	public boolean isCapsule()
	{
		return false;
	}
}
