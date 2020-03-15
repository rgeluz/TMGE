package com.INF122.TMGE.DRM;

import java.util.List;

import com.INF122.TMGE.Direction;
import com.INF122.TMGE.Tile;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Capsule extends Tile
{
	int type;
	public Capsule(int tileSize, boolean setColor, Color color, boolean setTileBorder, boolean setImage, Image image,
			int cenColIndex, int cenRowIndex, int position, Direction directions, int type) 
	{
		super(tileSize, setColor, color, setTileBorder, setImage, image, cenColIndex, cenRowIndex, position, type, directions);	
	}
	
	public Capsule(int tileSize, boolean setColor, Color color, boolean setTileBorder, boolean setImage, Image image,
			int cenColIndex, int cenRowIndex, int position, List<Direction> directions, int type) 
	{
		super(tileSize, setColor, color, setTileBorder, setImage, image, cenColIndex, cenRowIndex, position, directions, type);
	}
}
