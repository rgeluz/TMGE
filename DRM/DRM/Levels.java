package com.INF122.TMGE.DRM;

import java.util.ArrayList;
import java.util.List;
import com.INF122.TMGE.*;

public class Levels 
{
	private static Board board;
	public List<Virus> blueVirusList = new ArrayList<Virus>();
	public List<Virus> redVirusList = new ArrayList<Virus>();
	public List<Virus> yellowVirusList = new ArrayList<Virus>();
	public List<Virus> capsuleList = new ArrayList<Virus>();
	Levels(Board b)
	{
		Levels.board = b;
	}
	
	public void getLvl(int lvl)
	{
		switch(lvl)
		{
			case 1:
				lvl1();
				for (Tile t: blueVirusList)
					board.placeTile(t);
				for (Tile t: redVirusList)
					board.placeTile(t);
				for (Tile t: yellowVirusList)
					board.placeTile(t);
				
				break;
			case 2:
				
				break;
			case 3:
				break;
		}
	}
	
	private void lvl1()
	{
		blueVirusList.add(Virus.getVirus(board, 1, board.gridWidth/3, board.gridHeight-10));
		redVirusList.add(Virus.getVirus(board, 2, board.gridWidth/2, board.gridHeight-5));
		yellowVirusList.add(Virus.getVirus(board, 3, board.gridWidth/2, board.gridHeight/2));
	}
	
	private void lvl2()
	{
		
	}
	
	private void lvl3()
	{
		
	}
}
