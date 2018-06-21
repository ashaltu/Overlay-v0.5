package com.overlay.map;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundLoader {
	Image background;
	String backgroundname;
	int x, y, w, h ,xd=0, yd=0;
	public BackgroundLoader(String backgroundname, int w, int h) {
		this.backgroundname=backgroundname;
		this.x=0;
		this.y=0;
		this.w = w;
		this.h = h;
		try{setBackgroundImage();}catch(IOException e){e.printStackTrace();}
	}
	public BackgroundLoader(String backgroundname, int x, int y, int w, int h){
		this.backgroundname=backgroundname;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		try{setBackgroundImage();}catch(IOException e){e.printStackTrace();}
	}

	public void draw(Graphics2D g) {/*checkBounds();*/ move();	g.drawImage(background, x, y, w, h, null);}
	public void move(){x+=xd;	y+=yd;}
	public void checkBounds(){if(x<0 || x>w/2){setXD(0);}}
	public void setXD(int value){xd=value;}
	public void setYD(int value){yd=value;}
	public void setXValue(int value){x=value;}
	public void setYValue(int value){y=value;}
	public void setW(int value){w=value;}
	public void setH(int value){h=value;}
	public void setBackgroundImage() throws IOException{background=ImageIO.read(this.getClass().getResourceAsStream("/res/"+getBackgroundName()+".png"));}
	public void changeBackground(String backgroundname){this.backgroundname=backgroundname;}
	
	public Image getBackgroundImage(){return background;}
	public String getBackgroundName(){return backgroundname;}
	public int getX(){return x;}
	public int getY(){return y;}
	public int getW(){return w;}
	public int getH(){return h;}
}
