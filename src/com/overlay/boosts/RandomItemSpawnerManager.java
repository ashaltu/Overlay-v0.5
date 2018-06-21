package com.overlay.boosts;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import com.overlay.objects.Computer;
import com.overlay.objects.Player;

public class RandomItemSpawnerManager implements ActionListener{
	public final static int QUICKER = 0;
	public final static int MORE_BULLETS = 1;
	public final static int BIGGER_BULLETS = 2;
	public final static int BIGGER_PLAYER = 3;
	public final static int ADD_HEART = 4;
	ArrayList<Boost> rism = new ArrayList<Boost>();
	Timer timer = new Timer(5000,this);;
	Random rand = new Random();
	int numItemsOnScreen = 0;
	int maxItemsOnScreen = 3;
	int minspawnx = 100;
	int maxspawnx = 285;
	int minspawny = 100;
	int maxspawny = 285;
	public RandomItemSpawnerManager() {
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e){
		if(numItemsOnScreen<maxItemsOnScreen){
			int num = rand.nextInt(5);
			int randomx = rand.nextInt((maxspawnx-minspawnx)+1)+minspawnx;
			int randomy = rand.nextInt((maxspawny-minspawny)+1)+minspawny;
			switch(num){
			case QUICKER:
				rism.add(new QuickerBoost(randomx,randomy, 12, 12, "quickboost","quick"));
				numItemsOnScreen++;
				break;
			case MORE_BULLETS:	
				rism.add(new MoreBulletsBoost(randomx, randomy, 12, 12, "morebulletsboost","morebullets"));
				numItemsOnScreen++;
				break;
			case BIGGER_BULLETS:
				rism.add(new BiggerBulletsBoost(randomx, randomy, 12, 12, "biggerbulletsboost","biggerbullets"));
				numItemsOnScreen++;
				break;
			case BIGGER_PLAYER:
				rism.add(new BiggerPlayerBoost(randomx, randomy, 12, 12, "biggerplayerboost","biggerplayer"));
				numItemsOnScreen++;
				break;
			case ADD_HEART:
				rism.add(new AddHeartBoost(randomx, randomy, 8, 8, "addheartboost","addheart"));
				numItemsOnScreen++;
				break;
			}
		}
	}
	
	public void draw(Graphics2D g){
		for(Boost boost : new ArrayList<>(rism)){
			boost.draw(g);
		}
	}
	
	public void checkBoost(Player player){
		ArrayList<Boost> toRemove = new ArrayList<Boost>();
		for(Boost boost : rism){
			if(boost.intersectsPlayer(player)){
				boost.boost(player);
				if(boost.getBoostName().equals("quick")){player.speedTimerStart();}
				if(boost.getBoostName().equals("biggerbullets")){player.bulletSizeTimerStart();}
				if(boost.getBoostName().equals("biggerplayer")){player.playerSizeTimerStart();}
				toRemove.add(boost);
				numItemsOnScreen--;
			}
		}
		rism.removeAll(toRemove);
	}
	
	public void checkBoost(Computer computer){
		ArrayList<Boost> toRemove = new ArrayList<Boost>();
		for(Boost boost : new ArrayList<>(rism)){
			if(boost.intersectsComputer(computer)){
				boost.boost(computer);
				if(boost.getBoostName().equals("quick")){computer.speedTimerStart();}
				if(boost.getBoostName().equals("biggerbullets")){computer.bulletSizeTimerStart();}
				if(boost.getBoostName().equals("biggerplayer")){computer.computerSizeTimerStart();}
				toRemove.add(boost);
				numItemsOnScreen--;
			}
		}
		rism.removeAll(toRemove);
	}	
}
