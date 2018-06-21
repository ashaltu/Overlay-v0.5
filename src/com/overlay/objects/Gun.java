package com.overlay.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public class Gun {
	ArrayList<Bullet> firedbullets = new ArrayList<Bullet>();
	int bulletsleft = 5;
	int bulletsizewidth = 22;
	int bulletsizeheight = 6;
	int originalbulletsizewidth = bulletsizewidth;
	int originalbulletsizeheight = bulletsizeheight;
	int originalbulletsleft = bulletsleft;
	Entity entity;
	Image bulletimage;
	int direction;

	public Gun(Entity entity, int direction,Image bulletimage) {
		this.entity = entity;
		this.direction = direction;
		this.bulletimage=bulletimage;
	}

	public void checkBulletLocation() {
		ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		for(Bullet bullet: firedbullets){
			if(bullet.getX()>400){
				bulletsToRemove.add(bullet);
			}
			if(bullet.getX()<0){
				bulletsToRemove.add(bullet);
			}
		}
		firedbullets.removeAll(bulletsToRemove);
	}

	public void shoot() {
		if (bulletsleft > 0) {
			Bullet bullet = new Bullet(entity.getX(), entity.getY(),bulletimage,getBulletSizeWidth(),getBulletSizeHeight(), direction);
			firedbullets.add(bullet);
			bulletsleft--;
			if(entity.getDirection()<0){
				entity.setCurrentImage(entity.getSpriteSheet().getLeft());
			}
			if(entity.getDirection()>0){
				entity.setCurrentImage(entity.getSpriteSheet().getRight());
			}
		}
	}

	public void moveBullets() {
		for (Bullet bullet : firedbullets) {
			bullet.move();
		}
	}

	public void draw(Graphics2D g) {
		if(firedbullets!=null){
		for (Bullet bullet : new ArrayList<>(firedbullets)) {
			bullet.draw(g);
		}
		}
	}
	
	public void update(){
		moveBullets();
		checkBulletLocation();
	}

	public void reload() {
		if(bulletsleft<originalbulletsleft){
		bulletsleft = originalbulletsleft;
		}
	}

	public ArrayList<Bullet> firedBullets() {
		return firedbullets;
	}

	public int getBulletsLeft() {
		return bulletsleft;
	}
	public int getBulletSizeWidth(){
		return bulletsizewidth;
	}
	public int getBulletSizeHeight(){
		return bulletsizeheight;
	}
	
	public void setBulletsLeft(int v){bulletsleft+=v;}
	public void addBulletSize(int v){bulletsizewidth += v;	bulletsizeheight+=v;}
	public void setBulletSizeWidth(int v){bulletsizewidth = v;}
	public void setBulletSizeHeight(int v){bulletsizeheight = v;}
	
	public int size() {
		return firedbullets.size();
	}
	public int getOriginalBulletSizeWidth(){return originalbulletsizewidth;}
	public int getOriginalBulletSizeHeight(){return originalbulletsizeheight;}

}
