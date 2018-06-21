package com.overlay.objects;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ObstacleManager {
	ArrayList<Obstacle> obstacles;
	public ObstacleManager() {
		obstacles = new ArrayList<Obstacle>();
	}
	
	public void addObstacle(Obstacle obstacle){
		obstacles.add(obstacle);
	}
	public void update(){
		for(Obstacle object : obstacles){
			object.update();
		}
	}
	public void checkHitBullet(Player player){
		for(Obstacle object : obstacles){
			object.checkHitBullet(player);
		}
	}
	public void checkHitBullet(Computer computer){
		for(Obstacle object : obstacles){
			object.checkHitBullet(computer);
		}
	}
	public void draw(Graphics2D g){
		for(Obstacle object : obstacles){
			object.draw(g);
		}
	}

}
