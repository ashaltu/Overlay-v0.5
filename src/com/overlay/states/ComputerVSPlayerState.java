package com.overlay.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.overlay.boosts.RandomItemSpawnerManager;
import com.overlay.map.BackgroundLoader;
import com.overlay.objects.Computer;
import com.overlay.objects.Entity;
import com.overlay.objects.Obstacle;
import com.overlay.objects.ObstacleManager;
import com.overlay.objects.Player;

public class ComputerVSPlayerState extends GameState {
	Player player;
	ObstacleManager om;
	Computer computer;
	RandomItemSpawnerManager rism;
	GameStateManager gsm;
	BackgroundLoader bl;
	SpriteSheet ps;
	SpriteSheet cs;
	int lives = 6;
	double difficulty;
	public ComputerVSPlayerState(GameStateManager gsm) {
		super();
		this.gsm=gsm;
	}

	@Override
	public void init() {
		difficulty = gsm.getDifficulty();
		bl = new BackgroundLoader("bg", 400, 400);
		om = new ObstacleManager();
		ps = new SpriteSheet("blueguy");
		cs = new SpriteSheet("redguy");
		player = new Player(lives,20, 20, 16, 16, ps, Entity.RIGHT, "blueheart","bullet",1,false,0);
		computer = new Computer(lives,player,360, 370, 16, 16,cs, Entity.LEFT,"redheart","bullet",gsm.getDifficulty(),gsm.getBPS());
		rism = new RandomItemSpawnerManager();
		
		om.addObstacle(new Obstacle(60,20,5,75,new SpriteSheet("white"),Entity.LEFT,20,300));
		om.addObstacle(new Obstacle(325,350,5,75,new SpriteSheet("white"),Entity.LEFT,20,300));
	}

	@Override
	public void update() {
		player.update();
		computer.update();
		om.update();
		checkIntersection();
	}

	@Override
	public void draw(Graphics2D g) {
		bl.draw(g);
		player.draw(g);
		computer.draw(g);
		om.draw(g);
		rism.draw(g);
	}
	public void checkIntersection(){
		player.checkHitComputer(gsm, computer);
		computer.checkHitPlayer(gsm, player);
		
		player.checkCollideWithOtherBullet(computer);
		
		om.checkHitBullet(player);
		om.checkHitBullet(computer);
		
		rism.checkBoost(player);
		rism.checkBoost(computer);
	}
	@Override
	public void keyPressed(KeyEvent k) {
		player.keyPressed(k);
	}

	@Override
	public void keyReleased(KeyEvent k) {
		player.keyReleased(k);
	}
}
