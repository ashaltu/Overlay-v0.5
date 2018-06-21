package com.overlay.states;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.overlay.boosts.RandomItemSpawnerManager;
import com.overlay.map.BackgroundLoader;
import com.overlay.objects.Entity;
import com.overlay.objects.Obstacle;
import com.overlay.objects.ObstacleManager;
import com.overlay.objects.Player;

public class PlayState extends GameState {
	Player player, player2;
	ObstacleManager om;
	GameStateManager gsm;
	RandomItemSpawnerManager rism;
	BackgroundLoader bl;
	SpriteSheet ps;
	SpriteSheet p2s;
	int lives= 6;
	public PlayState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
	}

	@Override
	public void init() {
		bl = new BackgroundLoader("white", 400, 400);
		om = new ObstacleManager();
		ps = new SpriteSheet("blueguy");
		p2s = new SpriteSheet("redguy");
		player = new Player(lives,20, 360, 16, 16, ps, Entity.RIGHT,"blueheart","bullet",1,false,0);
		player2 = new Player(lives,320, 20, 16, 16, p2s, Entity.LEFT,"redheart","bullet",1,true,1);
		rism = new RandomItemSpawnerManager();
		om.addObstacle(new Obstacle(60,20,5,75,new SpriteSheet("white"),Entity.LEFT,20,300));
		om.addObstacle(new Obstacle(325,350,5,75,new SpriteSheet("white"),Entity.LEFT,20,300));
	}

	@Override
	public void update() {
		player.update();
		player2.update();
		om.update();
		checkIntersection();
	}
	public void checkIntersection() {
		player.checkHitOtherPlayer(gsm, player2,0);
		player2.checkHitOtherPlayer(gsm, player,1);
			
		player.checkCollideWithOtherBullet(player2);
		
		om.checkHitBullet(player);
		om.checkHitBullet(player2);
		
		rism.checkBoost(player);
		rism.checkBoost(player2);
	}	
	
	
	public void draw(Graphics2D g) {
		bl.draw(g);
		player.draw(g);
		player2.draw(g);
		om.draw(g);
		rism.draw(g);
		
		g.drawString("x: "+player.getX()+"y:"+player.getY(),4,370);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		player.keyPressed(k);
		player2.keyPressed(k);	
	}

	@Override
	public void keyReleased(KeyEvent k) {
		player.keyReleased(k);
		player2.keyReleased(k);
	}
	public Player getPlayer1(){return player;}
	public Player getPlayer2(){return player2;}
}