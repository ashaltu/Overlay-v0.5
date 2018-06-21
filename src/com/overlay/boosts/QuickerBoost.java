package com.overlay.boosts;

import java.awt.Graphics2D;

import com.overlay.objects.Computer;
import com.overlay.objects.Player;

public class QuickerBoost extends Boost {

	public QuickerBoost(double x, double y, int w, int h, String filename, String boostname) {
		super(x, y, w, h, filename, boostname);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(boostimage,(int)x, (int)y, w, h,null);
	}

	@Override
	public void boost(Player player) {
		player.setSpeed(.7);
	}

	@Override
	public void boost(Computer computer) {
		computer.setSpeed(.5);
	}

}
