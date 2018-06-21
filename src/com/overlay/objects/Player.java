package com.overlay.objects;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import com.overlay.states.GameStateManager;
import com.overlay.states.SpriteSheet;

public class Player extends Entity{
	Gun gun;
	int lives;
	int originalw,originalh;
	boolean uppressed,downpressed,leftpressed,rightpressed;
	Image heart,bullet;
	double speed;
	double originalspeed;
	boolean arrows;
	int playernum;
	Timer speedtimer = new Timer(15000, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			speed=originalspeed;
			speedTimerStop();
	}});
	Timer bulletsizetimer = new Timer(15000, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			gun.setBulletSizeWidth(gun.getOriginalBulletSizeWidth());
			gun.setBulletSizeHeight(gun.getOriginalBulletSizeHeight());
			bulletSizeTimerStop();
	}});
	Timer playersizetimer = new Timer(15000, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setW(originalw);
			setH(originalh);
			playerSizeTimerStop();
	}});
	public Player (int lives,int x, int y,int w,int h,SpriteSheet spritesheet, int direction,String heartname, String bulletname, double speed,boolean arrows, int playernum){
		super(x,y,w,h,spritesheet,direction);
		this.arrows=arrows;
		this.playernum=playernum;
		gun = new Gun(this,getDirection(),getBulletDirection());
		this.lives = lives;
		setHeart(heartname);
		setBullet(bulletname);
		this.speed=speed;
		originalspeed = speed;
		originalw = w;
		originalh = h;
	}
	public Image getBulletDirection(){
		Image toreturn = null;
		if(playernum == 0){
			toreturn = bulletright;
		}
		if(playernum == 1){
			toreturn = bulletright;
		}
		return toreturn;
	}
	//timer starters
	public void speedTimerStart(){speedtimer.start();}
	public void bulletSizeTimerStart(){bulletsizetimer.start();}
	public void playerSizeTimerStart(){playersizetimer.start();}
	//timer stoppers
	public void speedTimerStop(){speedtimer.stop();}
	public void bulletSizeTimerStop(){bulletsizetimer.stop();}
	public void playerSizeTimerStop(){playersizetimer.stop();}
	
	//Boost methods
	public void setSpeed(double speed){this.speed+=speed;}
	public void addLife(){lives++;}
	
	public void checkMovement(){
		stopMoving();
		if(uppressed){
			up();
		}
		if(downpressed){
			down();
		}
		if(leftpressed){
			left();
		}
		if(rightpressed){
			right();
		}
	}
	public void update(){
		checkMovement();
		checkBounds();
		getGun().update();
		x+=xd;
		y+=yd;
		entitybox.x=(int)x;
		entitybox.y=(int)y;
		entitybox.width=w;
		entitybox.height=h;
	}
	public void draw(Graphics2D g){
		g.drawImage(getCurrentImage(),(int)x, (int)y, w, h,null);
		if(getGun().getBulletsLeft()==0 && playernum==0){
			g.drawString("RELOAD", 0, 28);
		}
		if(getGun().getBulletsLeft()==0 && playernum==1){
			g.drawString("RELOAD", 340, 28);
		}
		
		for(int i = 0;i<getLives();i++){
//			g.drawImage(heart,(getX()+16*i)-16,getY()-16,null);
			if(playernum==0){
				g.drawImage(heart,0+(16*i),0,null);
			}
			if(playernum==1){
				g.drawImage(heart,377-(16*i),0,null);
			}
		}
		
		for(int j = 0;j<getGun().getBulletsLeft();j++){
			if(playernum==0){
				g.drawImage(bullet,0+(7*j),16,3,11,null);
			}
			if(playernum==1){
				g.drawImage(bullet,391-(7*j),16,3,11,null);
			}
		}
		getGun().draw(g);
	}
	public void checkHitOtherPlayer(GameStateManager gsm, Player player,int winner){
		for(int i = 0; i < getGun().size();i++){
			if (gun.firedBullets().get(i).getBulletBox().intersects(player.getBox())) {
				player.removeLife();
				gun.firedBullets().remove(i);
				if (player.getLives() <= 0) {
					gsm.setWinner(winner);
					gsm.setState(GameStateManager.ENDSTATE);
				}
			}
		}
	}
	public void checkCollideWithOtherBullet(Player player){
		for(int i = 0; i< getGun().firedBullets().size();i++){
	loop:	for(int j = 0; j<player.getGun().size();j++){
				if(getGun().firedBullets().get(i).getBulletBox().intersects(player.getGun().firedBullets().get(j).getBulletBox())){
					if(getGun().firedBullets().get(i).getBulletSizeHeight()>player.getGun().firedBullets().get(j).getBulletSizeHeight()){
						player.getGun().firedBullets().remove(j);
						gun.firedBullets().get(i).setBulletSizeWidth(gun.getOriginalBulletSizeWidth());
						gun.firedBullets().get(i).setBulletSizeHeight(gun.getOriginalBulletSizeHeight());
						break loop;
					}
					if(getGun().firedBullets().get(i).getBulletSizeHeight()<player.getGun().firedBullets().get(j).getBulletSizeHeight()){
						gun.firedBullets().remove(i);
						player.getGun().firedBullets().get(i).setBulletSizeWidth(player.getGun().getOriginalBulletSizeWidth());
						player.getGun().firedBullets().get(i).setBulletSizeHeight(player.getGun().getOriginalBulletSizeHeight());
						break loop;
					}
					if(getGun().firedBullets().get(i).getBulletSizeHeight()==player.getGun().firedBullets().get(j).getBulletSizeHeight()){
						gun.firedBullets().remove(i);
						player.getGun().firedBullets().remove(j);
						break loop;	
					}
				}
			}
		}
	}
	public void checkCollideWithOtherBullet(Computer computer){
		for(int i = 0; i< getGun().firedBullets().size();i++){
	loop:	for(int j = 0; j<computer.getGun().size();j++){
				if(getGun().firedBullets().get(i).getBulletBox().intersects(computer.getGun().firedBullets().get(j).getBulletBox())){
					if(getGun().firedBullets().get(i).getBulletSizeHeight()>computer.getGun().firedBullets().get(j).getBulletSizeHeight()){
						computer.getGun().firedBullets().remove(j);
						gun.firedBullets().get(i).setBulletSizeWidth(gun.getOriginalBulletSizeWidth());
						gun.firedBullets().get(i).setBulletSizeHeight(gun.getOriginalBulletSizeHeight());
						break loop;
					}
					if(getGun().firedBullets().get(i).getBulletSizeHeight()<computer.getGun().firedBullets().get(j).getBulletSizeHeight()){
						gun.firedBullets().remove(i);
						computer.getGun().firedBullets().get(i).setBulletSizeWidth(computer.getGun().getOriginalBulletSizeWidth());
						computer.getGun().firedBullets().get(i).setBulletSizeHeight(computer.getGun().getOriginalBulletSizeHeight());
						break loop;
					}
					if(getGun().firedBullets().get(i).getBulletSizeHeight()==computer.getGun().firedBullets().get(j).getBulletSizeHeight()){
						gun.firedBullets().remove(i);
						computer.getGun().firedBullets().remove(j);
						break loop;	
					}
				}
			}
		}
	}
	public void checkHitComputer(GameStateManager gsm, Computer computer){
		for(int i = 0; i < getGun().size();i++){
			if (gun.firedBullets().get(i).getBulletBox().intersects(computer.getBox())) {
				computer.removeLife();
				gun.firedBullets().remove(i);
				if (computer.getLives() <= 0) {
					gsm.setWinner(0);
					gsm.setState(GameStateManager.ENDSTATE);
				}
			}
		}
	}
	
	public void setHeart(String heartname){
		try{heart = ImageIO.read(this.getClass().getResourceAsStream("/res/"+heartname+".png"));}catch(Exception e){e.printStackTrace();}
	}
	public void setBullet(String bulletname){
		try{bullet = ImageIO.read(this.getClass().getResourceAsStream("/res/"+bulletname+".png"));}catch(Exception e){e.printStackTrace();}
	}
	public Image getHeart(){
		return heart;
	}
	public void reload(){gun.reload();}
	public Gun getGun(){return gun;}
	public int bulletsLeft(){return getGun().getBulletsLeft();}
	public void removeLife(){lives--;}
	public int getLives(){return lives;}
	
	public void setUp(boolean b){
		uppressed = b;
	}
	public void setDown(boolean b){
		downpressed = b;
	}
	public void setLeft(boolean b){
		leftpressed = b;
	}
	public void setRight(boolean b){
		rightpressed = b;
	}

	public void up(){setYD(-speed);setCurrentImage(spritesheet.getUp());}
	public void down(){setYD(speed);setCurrentImage(spritesheet.getDown());}
	public void left(){setXD(-speed);setCurrentImage(spritesheet.getLeft());}
	public void right(){setXD(speed);setCurrentImage(spritesheet.getRight());}	
	
	public void setCurrentImage(Image i){playercurrentimage=i;}
	public Image getCurrentImage(){return playercurrentimage;}
	
	public void keyPressed(KeyEvent k){
		int key = k.getKeyCode();
		if(arrows){
			switch(key){
			case UP_KEY:
				setUp(true);
				break;
			case DOWN_KEY:  
				setDown(true);
				break;
			case LEFT_KEY:
				setLeft(true);
				break;
			case RIGHT_KEY:
				setRight(true);
				break;
			}
			if (key == K_KEY) {
				getGun().shoot();
				}
			if (key == L_KEY) {
				reload();
			}
		}else{
			switch(key){
			case W_KEY:
				setUp(true);
				break;
			case S_KEY:  
				setDown(true);
				break;
			case A_KEY:
				setLeft(true);
				break;
			case D_KEY:
				setRight(true);
				break;
			}
			if (key == SPACE_KEY) {
				getGun().shoot();
				}
			if (key == R_KEY) {
				reload();
			}
		}
	}
	
	public void keyReleased(KeyEvent k){
		int key = k.getKeyCode();
		if(arrows){
			switch(key){
			case UP_KEY:
				setUp(false);
				break;
			case DOWN_KEY:  
				setDown(false);
				break;
			case LEFT_KEY:
				setLeft(false);
				break;
			case RIGHT_KEY:
				setRight(false);
				break;
			}
		}else{
			switch(key){
			case W_KEY:
				setUp(false);
				break;
			case S_KEY:  
				setDown(false);
				break;
			case A_KEY:
				setLeft(false);
				break;
			case D_KEY:
				setRight(false);
				break;
			}
		}
	}
}
