package com.overlay.boosts;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import com.overlay.objects.Computer;
import com.overlay.objects.Player;

public abstract class Boost {
	Rectangle box;
	Image boostimage;
	String filename;
	String boostname;
	double x, y;
	int w,h;

	public Boost(double x, double y, int w, int h, String filename,String boostname) {
		this.filename=filename;
		this.boostname=boostname;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		box = new Rectangle(w,h);
		box.x=(int) x;
		box.y=(int) y;
		try{boostimage = ImageIO.read(this.getClass().getResourceAsStream("/res/"+filename+".png"));}catch(Exception e){e.printStackTrace();}
	}

	public abstract void draw(Graphics2D g);
	public abstract void boost(Player player);
	public abstract void boost(Computer computer);
	
	public Rectangle getBox(){return box;}
	public String getBoostName(){return boostname;}
	public boolean intersectsPlayer(Player player){return box.intersects(player.getBox());}
	public boolean intersectsComputer(Computer computer){return box.intersects(computer.getBox());}
	
}
