package com.overlay.states;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.overlay.main.Printer;

public class OptionsState extends GameState {
	GameStateManager gsm;
	Printer printer;
	public static final double BASIC = 0.9;
	public static final double NORMAL = 1.5;
	public static final double MASTER = 2;
	public static final double BASICBPS = 2;
	public static final double NORMALBPS = 4;
	public static final double MASTERBPS = 8;
	public OptionsState(GameStateManager gsm) {
		super();
		this.gsm=gsm;
	}

	@Override
	public void init() {
		printer = new Printer("newfont");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 400);
		
		printer.printOnScreen(g, "Player Speed:", 90, 50);

		printer.printOnScreen(g, "Press B For basic", 60, 100);
		printer.printOnScreen(g, "press n For normal", 60, 130);
		printer.printOnScreen(g, "press m For master", 60, 160);
		
		if(gsm.getDifficulty()==OptionsState.BASIC){
			printer.printOnScreen(g, "Difficulty:Basic", 60, 210);
		}
		if(gsm.getDifficulty()==OptionsState.NORMAL){
			printer.printOnScreen(g, "Difficulty:Normal", 60, 210);
		}
		if(gsm.getDifficulty()==OptionsState.MASTER){
			printer.printOnScreen(g, "Difficulty:Master", 60, 210);
		}	
		
		
		printer.printOnScreen(g, "To go back", 60, 280);
		printer.printOnScreen(g, "  press ESC", 120, 310);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE){
			gsm.setState(GameStateManager.MENUSTATE);
		}
		if(key == KeyEvent.VK_B){
			gsm.setDifficulty(OptionsState.BASIC);
			gsm.setBPS(OptionsState.BASICBPS);
		}
		if(key == KeyEvent.VK_N){
			gsm.setDifficulty(OptionsState.NORMAL);
			gsm.setBPS(OptionsState.NORMALBPS);
		}
		if(key == KeyEvent.VK_M){
			gsm.setDifficulty(OptionsState.MASTER);
			gsm.setBPS(OptionsState.MASTERBPS);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub

	}
}
