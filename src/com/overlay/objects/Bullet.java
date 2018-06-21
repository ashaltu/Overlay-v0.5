package com.overlay.objects;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Bullet {
	Gun gun;
	Player player;
	int bulletsizewidth;
	int bulletsizeheight;
	Image image;
	Rectangle bulletbox;
	int x,y;
	int direction;
	public Bullet(int x, int y, Image image,int bulletsizewidth,int bulletsizeheight,int direction){
		this.x=x;
		this.y=y;
		this.image=image;
		this.bulletsizewidth=bulletsizewidth;
		this.bulletsizeheight=bulletsizeheight;
		bulletbox = new Rectangle(bulletsizewidth,bulletsizeheight);
		bulletbox.x=x;
		bulletbox.y=y;
		this.direction=direction;
	}
	public void draw(Graphics2D g){
		g.drawImage(image,x, y, getBulletSizeWidth(), getBulletSizeHeight(),null);
	}
	public void move(){
		x+=direction;
		bulletbox.x=x;		
	}
	public int getX(){return x;}
	public int getY(){return y;}
	public Rectangle getBulletBox(){return bulletbox;}
	public int getBulletSizeWidth(){return bulletsizewidth;}
	public int getBulletSizeHeight(){return bulletsizeheight;}
	public void setBulletSizeWidth(int i){bulletsizewidth = i;}
	public void setBulletSizeHeight(int i){bulletsizeheight = i;}
}
