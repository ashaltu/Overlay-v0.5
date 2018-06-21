package com.overlay.main;
import javax.swing.JFrame;
//Version 0.5
//By Abdul Shaltu
public class GameLauncher {
	public static final double VERSION = 0.5; //////////////////////<<<CURRENT GAME VERSION
	public static void main(String[] args) {
		Panel panel = new Panel(GameLauncher.VERSION);
		panel.setSize(Panel.WIDTH, Panel.HEIGHT);
		JFrame jf = new JFrame();
		jf.setTitle("Overlay");
		jf.setSize(Panel.WIDTH, Panel.HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setContentPane(panel);
		jf.setVisible(true);
		panel.setVisible(true);
		
	}
}
