package com.INF122.DrMario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.INF122.TMGE.*;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Pills 
{
	List<Capsule> caps;
	private static boolean setColor = false;
	private static boolean setTileBorder = false;
	private static boolean setImg = true;
    public int centerPieceColumnIndex;
    public int centerPieceRowIndex;
	public static int rotateStatus = 0;
    
	Pills(List<Capsule> c)
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
		List<Capsule> caps = new ArrayList<Capsule>(); 

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

	private static Capsule createBlue(Board b, int i)
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
		Capsule cap = new Capsule(b.tileSize, setColor, Color.BLUE, setTileBorder, setImg, image, b.gridWidth/2, 0, i, Direction.DOWN, 1);
		return cap;
	}
	
	private static Capsule createRed(Board b, int i)
	{
        Image image = null;
        try 
        {
            image = new Image(new FileInputStream("resources/BlockRed.png"));
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        Capsule cap = new Capsule(b.tileSize, setColor, Color.RED, setTileBorder, setImg, image, b.gridWidth/2, 0, i, Direction.DOWN, 2);
		return cap;
	}
	
	private static Capsule createYellow(Board b, int i)
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
        Capsule cap = new Capsule(b.tileSize, setColor, Color.YELLOW, setTileBorder, setImg, image, b.gridWidth/2, 0, i, Direction.DOWN, 3);
		return cap;
	}
}

