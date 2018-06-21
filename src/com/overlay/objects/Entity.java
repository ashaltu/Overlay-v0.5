package com.overlay.objects;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

import com.overlay.states.SpriteSheet;

public abstract class Entity {
	double x,y,xd=0,yd=0;
	int w,h;
	public static final int LEFT = -10;
	public static final int RIGHT = 10;
	public static final int UP_KEY = KeyEvent.VK_UP;
	public static final int DOWN_KEY = KeyEvent.VK_DOWN;
	public static final int LEFT_KEY = KeyEvent.VK_LEFT;
	public static final int RIGHT_KEY = KeyEvent.VK_RIGHT;
	public static final int W_KEY = KeyEvent.VK_W;
	public static final int S_KEY = KeyEvent.VK_S;
	public static final int A_KEY = KeyEvent.VK_A;
	public static final int D_KEY = KeyEvent.VK_D;
	public static final int SPACE_KEY = KeyEvent.VK_SPACE;
	public static final int R_KEY = KeyEvent.VK_R;
	public static final int K_KEY = KeyEvent.VK_K;
	public static final int L_KEY = KeyEvent.VK_L;
	private int direction;
	Image playercurrentimage;
	Image bulletleft;
	Image bulletright;
	Rectangle entitybox;
	SpriteSheet spritesheet;
	public Entity(int x,int y,int w,int h,SpriteSheet spritesheet,int direction){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.spritesheet=spritesheet;
		entitybox = new Rectangle(w,h);
		entitybox.x=x;
		entitybox.y=y;
		this.direction=direction;
		try{this.bulletleft = ImageIO.read(this.getClass().getResourceAsStream("/res/bulletleft.png"));}catch(Exception e){e.printStackTrace();}
		try{this.bulletright = ImageIO.read(this.getClass().getResourceAsStream("/res/bulletright.png"));}catch(Exception e){e.printStackTrace();}
		playercurrentimage = spritesheet.getDown();
	}
	public abstract void draw(Graphics2D g);
	public abstract void update();
	
	public Rectangle getBox(){return entitybox;}
	public boolean intersects(Rectangle box){
		return entitybox.intersects(box);
	}
	
	public void stopMoving(){setXD(0);setYD(0);}
	public void checkBounds(){
		if(getX()<0){setX(0);}
		if(getX()>384){setX(384);}
		if(getY()<0){setY(0);}
		if(getY()>362){setY(362);}
	}
	
	public void setW(int v){w=v;}
	public void setH(int v){h=v;}
	public void setX(double v){x=v;}
	public void setY(double v){y=v;}
	public void setXD(double v){xd=v;}
	public void setYD(double v){yd=v;}
	
	public int getX(){return (int)x;}
	public int getY(){return (int)y;}
	public int getW(){return w;}
	public int getH(){return h;}
	
	public SpriteSheet getSpriteSheet(){return spritesheet;}
	public int getDirection(){return direction;}
	
	public void setCurrentImage(Image i){playercurrentimage=i;}
	public Image getCurrentImage(){return playercurrentimage;}
}
