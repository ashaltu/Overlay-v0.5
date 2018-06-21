package com.overlay.states;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.overlay.main.Printer;

public class HelpState extends GameState {
	GameStateManager gsm;
	Printer printer;
	public HelpState(GameStateManager gsm) {
		super();
		this.gsm = gsm;
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
		
		printer.printOnScreen(g, "Instructions", 100, 30);
		printer.printOnScreen(g, "Shoot the other player ", 20, 60);
		printer.printOnScreen(g, "using the space button", 20, 90);
		printer.printOnScreen(g, " and K button", 80, 120);
		printer.printOnScreen(g, "To go back,press", 60, 240);
		printer.printOnScreen(g, "B or ESC", 120, 270);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_B){
			gsm.setState(GameStateManager.MENUSTATE);
		}
		if(key == KeyEvent.VK_ESCAPE){
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

}
