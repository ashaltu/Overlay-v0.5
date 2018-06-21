package com.overlay.states;

import java.awt.Image;

import com.overlay.map.TileMap;

public class SpriteSheet {
	TileMap tm;

	public SpriteSheet(String filename) {
		tm = new TileMap(filename, 16, 2, 2, 16, 16);
		
	}
	public Image getUp(){return tm.getTiles(1, 1);}
	public Image getDown(){return tm.getTiles(0, 0);}
	public Image getLeft(){return tm.getTiles(0, 1);}
	public Image getRight(){return tm.getTiles(1, 0);}
}
