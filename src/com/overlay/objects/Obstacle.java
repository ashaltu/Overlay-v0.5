package com.overlay.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.overlay.states.SpriteSheet;

public class Obstacle extends Entity {
	int ytopdest,ybottomdest;
	boolean hittop;
	double speed = 1.2;
	public Obstacle(int x, int y, int w, int h, SpriteSheet spritesheet, int direction,int ytopdest,int ybottomdest) {
		super(x, y, w, h, spritesheet, direction);
		this.ytopdest=ytopdest;
		this.ybottomdest=ybottomdest;
		if(y==ytopdest){hittop = true;}else{hittop = false;}
	}
	public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, w, h);
	}
	public void update(){
		move();
		computerAI();
	}
	
	public void checkHitBullet(Player player){
		for(int i = 0;i<player.getGun().firedBullets().size();i++){
			if(player.getGun().firedBullets().get(i).getBulletBox().intersects(getBox())){
				player.getGun().firedBullets().remove(i);
			}
		}
	}
	public void checkHitBullet(Computer computer){
		for(int i = 0;i<computer.getGun().firedBullets().size();i++){
			if(computer.getGun().firedBullets().get(i).getBulletBox().intersects(getBox())){
				computer.getGun().firedBullets().remove(i);
			}
		}
	}
	public void computerAI(){
		if(getY()==ytopdest){
			hittop = true;
		}
		if(!hittop){
			setYD(-speed);
		}
		if(hittop){
			setYD(speed);
		}
		if(getY()==ybottomdest){
			hittop=false;
		}
		
	}
	public void move(){
		x+=xd;
		y+=yd;
		entitybox.x=(int) x;
		entitybox.y=(int) y;
	}
}