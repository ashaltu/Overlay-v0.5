package com.overlay.map;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class TileMap {
	BackgroundLoader image;
	String imagename;
	BufferedImage subimage;
	Image[][] tiles;
	int tilesize;
	int row,column;
	int w,h;
	public TileMap(String imagename, int tilesize, int row, int column,int w,int h) {
		this.w=w;
		this.h=h;
		this.row=row;
		this.column=column;
		this.tilesize = tilesize;
		this.imagename = imagename;
		this.image = new BackgroundLoader(this.imagename,w,h);
		subimage=(BufferedImage) this.image.getBackgroundImage();
		tiles = new Image[row][column];
		setTileArray();
	}
	
	public Image getTiles(int row,int column){return tiles [row][column];}
	public String getImageName(){return imagename;}
	public void setTileArray(){
		for(int row = 0;row<this.row;row++){
			for(int column = 0;column<this.column;column++){
				tiles[row][column] = subimage.getSubimage(tilesize*column, tilesize*row, w, h);
			}
		}
	}
	public void changeImage(String name){
		image.changeBackground(name);
	}
	public BackgroundLoader getBackgroundLoader(){return image;}
	
}
