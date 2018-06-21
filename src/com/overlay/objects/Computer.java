package com.overlay.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import com.overlay.states.GameStateManager;
import com.overlay.states.SpriteSheet;

public class Computer extends Entity implements ActionListener {
	Player player;
	Gun gun;
	int lives;
	double bps;
	int originalw,originalh;
	double speed;
	double originalspeed;
	Image heart,bullet;
	Timer timer;
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
	Timer computersizetimer = new Timer(15000, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setW(originalw);
			setH(originalh);
			computerSizeTimerStop();
	}});
	
	public Computer(int lives,Player player,int x, int y, int w, int h,SpriteSheet spritesheet, int direction,String heartname,String bulletname,double speed,double bps) {
		super(x, y, w, h,spritesheet, direction);
		this.player=player;
		gun = new Gun(this,getDirection(),bulletleft);
		this.lives = lives;
		setHeart(heartname);
		setBullet(bulletname);
		this.speed=speed;
		originalspeed=speed;
		originalw=w;
		originalh=h;
		this.bps=bps;
		timer = new Timer((int) (1000/bps), this);
		timer.start();
	}
	public void update(){
		computerAI();
		checkBounds();
		gun.update();
		x+=xd;
		y+=yd;
		entitybox.x=(int)x;
		entitybox.y=(int)y;
		entitybox.width=w;
		entitybox.height=h;
	}
	public void actionPerformed(ActionEvent arg0) {
		if(getGun().getBulletsLeft()==0){
			getGun().reload();
		}
		if(player.getY()-getY()>=0 && player.getY()-getY()<=10 || getY()-player.getY()>=0 && getY()-player.getY()<=10){
			getGun().shoot();
		}
	}
	public void computerAI() {
		if(player.getY()>getY()){
			down();
		}
		if(player.getY()<getY()){
			up();
		}
	}
	public void checkHitPlayer(GameStateManager gsm, Player player){
		for(int i = 0; i < getGun().firedBullets().size();i++){	
			if (gun.firedBullets().get(i).getBulletBox().intersects(player.getBox())) {
				player.removeLife();
				gun.firedBullets().remove(i);
				if (player.getLives() <= 0) {
					gsm.setWinner(2);
					gsm.setState(GameStateManager.ENDSTATE);
				}
			}
		}
	}
	public void draw(Graphics2D g){
		g.drawImage(getCurrentImage(),(int)x, (int)y, w, h,null);
		if(getGun().getBulletsLeft()==0){
			g.drawString("RELOAD", 340, 28);
		}
		for(int i = 0;i<getLives();i++){
			g.drawImage(heart,377-(16*i),0,null);
		}
		for(int i = 0;i<getGun().getBulletsLeft();i++){
			g.drawImage(bullet,377-(7*i),16,3,11,null);
		}
		getGun().draw(g);
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
	
	//timer methods
	public void speedTimerStart(){speedtimer.start();}
	public void bulletSizeTimerStart(){bulletsizetimer.start();}
	public void computerSizeTimerStart(){computersizetimer.start();}
	//timer stoppers
	public void speedTimerStop(){speedtimer.stop();}
	public void bulletSizeTimerStop(){bulletsizetimer.stop();}
	public void computerSizeTimerStop(){computersizetimer.stop();}
	
	//Boost methods
	public void setSpeed(double speed){this.speed+=speed;}
	public void addLife(){lives++;}
	
	public void up(){setYD(-speed);setCurrentImage(spritesheet.getUp());}
	public void down(){setYD(speed);setCurrentImage(spritesheet.getDown());}
	public void left(){setXD(-speed);setCurrentImage(spritesheet.getLeft());}
	public void right(){setXD(speed);setCurrentImage(spritesheet.getRight());}	
}
