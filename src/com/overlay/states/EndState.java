package com.overlay.states;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.overlay.main.Printer;

public class EndState extends GameState{
	GameStateManager gsm;
	Printer printer;
	public EndState(GameStateManager gsm){
		this.gsm=gsm;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		printer = new Printer("newfont");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 400, 400);
		if(gsm.getWinner()==0){
		printer.printOnScreen(g, "Blue Player wins", 40, 150);
		}
		if(gsm.getWinner()==1){
			printer.printOnScreen(g, "Red Player wins", 40, 150);
		}
		if(gsm.getWinner()==2){
			printer.printOnScreen(g, "Computer wins", 40, 150);
		}
		
		printer.printOnScreen(g, "PRESS M TO RETURN", 40, 200);
		printer.printOnScreen(g, "TO THE MAIN MENU",40,230);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_M){
			gsm.setState(GameStateManager.MENUSTATE);
		}
		if(key == KeyEvent.VK_R){
			//gsm.setState(GameStateManager.PLAYSTATE);
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

}
