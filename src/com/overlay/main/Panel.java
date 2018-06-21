package com.overlay.main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.overlay.states.GameStateManager;

@SuppressWarnings("serial")
public class Panel extends JPanel implements Runnable, KeyListener {
	GameStateManager gsm;
	BufferedImage image;
	Thread thread;
	boolean running = false;
	Graphics2D g;
	int fps = 60;
	long tt = 1000/fps;
	double version;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	public Panel(double version) {
		super();
		this.version=version;
		setLayout(null);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	public double getVersion(){return version;}
	@Override
	public void run() {
		start();
		
		long start;
		long elapsed;
		long wait;
		while (running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			wait = tt - elapsed / 1000000;
			if(wait<0) wait=5; 
			try{Thread.sleep(wait);}catch(Exception e){	e.printStackTrace();}
		}
	}
	private void update() {
		gsm.update();
	}

	private void draw() {
		gsm.draw(g);
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image,0,0,WIDTH,HEIGHT,null);
		g2.dispose();
	}
	public void addNotify(){
		super.addNotify();
		if(thread==null){
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	public void start(){
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D) image.getGraphics();
		running = true;
		
		gsm = new GameStateManager(this);
		gsm.setState(GameStateManager.MENUSTATE);
	}
	
	@SuppressWarnings("deprecation")
	public void stop(){
		running=false;
		thread.stop();
	}
	
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {

	}
}
