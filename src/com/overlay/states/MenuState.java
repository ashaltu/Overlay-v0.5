package com.overlay.states;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.overlay.main.Panel;
import com.overlay.main.Printer;

public class MenuState extends GameState{
	Printer print;
	int x,y;
	Image arrow;
	GameStateManager gsm;
	Panel panel;
	public MenuState(GameStateManager gsm, Panel panel){
		super();
		this.gsm=gsm;
		this.panel=panel;
	}
	
	@Override
	public void init(){
		print = new Printer("saabirfont");
		x = 135;
		y = 115;
		try {
			arrow = ImageIO.read(this.getClass().getResourceAsStream("/res/arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(){
		//nothing to update... yet
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 400);
		g.setColor(Color.RED);
		g.fillRect(40, 30, 300, 300);
		g.setColor(Color.BLACK);
		g.fillRect(50, 40, 280, 280);
		g.setColor(Color.WHITE);
		g.drawImage(arrow, x, y, null);
		print.printOnScreen(g, "dumb game", 120, 60);
		print.printOnScreen(g, "play", 155, 115);
		print.printOnScreen(g, "u vs comp", 155, 155);
		print.printOnScreen(g, "options", 155, 195);
		print.printOnScreen(g, "help", 155, 235);
		print.printOnScreen(g, "quit", 155, 275);
		print.printOnScreen(g, "Created by", 120, 300);
		print.printOnScreen(g, "Abdul Shaltu", 120, 330);
		print.printOnScreen(g, "v"+panel.getVersion(), 150, 350);
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			y -= 40;
			if (y < 115) {
				y = 275;
			}
		}
		if (key == KeyEvent.VK_DOWN) {
			y += 40;
			if (y > 275) {
				y = 115;
			}
		}
		if(key == KeyEvent.VK_ENTER){
			select();
		}
		if(key == KeyEvent.VK_SPACE);
	}
	public void select(){
		if (y==115) {
			gsm.setState(GameStateManager.PLAYSTATE);
		}
		if (y==155) {
			gsm.setState(GameStateManager.COMPUTERVSPLAYERSTATE);
		}
		if (y==195) {
			gsm.setState(GameStateManager.OPTIONSSTATE);
		}
		if (y==235) {
			gsm.setState(GameStateManager.HELPSTATE);
		}
		if (y==275){
			panel.stop();
			System.exit(0);
		}
	}
	@Override
	public void keyReleased(KeyEvent k) {
		//nothing to release
	}
}
