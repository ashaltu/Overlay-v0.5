package com.overlay.states;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public abstract class GameState {
	public GameState(){
	}
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(KeyEvent k);
	public abstract void keyReleased(KeyEvent k);
}
