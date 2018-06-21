package com.overlay.states;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.overlay.main.Panel;

public class GameStateManager {
	GameState[] gs;
	Panel panel;
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	public static final int COMPUTERVSPLAYERSTATE = 2;
	public static final int OPTIONSSTATE = 3;
	public static final int HELPSTATE = 4;
	public static final int ENDSTATE = 5;
	int currentState;
	int winner;
	double difficulty;
	double bps;
	public GameStateManager(Panel panel){
		this.panel=panel;
		gs = new GameState[6];
		addGameStates();
		setDifficulty(OptionsState.BASIC);
		setBPS(OptionsState.BASICBPS);
	}
	public void update(){
		gs[currentState].update();
	}
	public void draw(Graphics2D g){
		gs[currentState].draw(g);
	}
	public void keyPressed(KeyEvent k){
		gs[currentState].keyPressed(k);
	}
	public void keyReleased(KeyEvent k){
		gs[currentState].keyReleased(k);
	}
	public void addGameStates(){
		gs[MENUSTATE]= new MenuState(this,panel);
		gs[PLAYSTATE] = new PlayState(this);
		gs[COMPUTERVSPLAYERSTATE] = new ComputerVSPlayerState(this);
		gs[OPTIONSSTATE] = new OptionsState(this);
		gs[HELPSTATE] = new HelpState(this);
		gs[ENDSTATE] = new EndState(this);
	}
	public void setState(int state){
		gs[state].init();
		currentState = state;
	}
	public void setWinner(int v){
		winner = v;
	}
	public int getWinner(){
		return winner;
	}
	public Panel getInstance(){
		return panel;
	}
	public double getDifficulty(){
		return difficulty;
	}
	public double getBPS(){
		return bps;
	}
	public void setDifficulty(double difficulty){
		this.difficulty = difficulty;
	}
	public void setBPS(double bps){
		this.bps=bps;
	}
}