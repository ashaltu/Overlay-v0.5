package com.overlay.main;
import java.awt.Graphics2D;

import com.overlay.map.TileMap;

public class Printer {
	TileMap tm;
	String alphabet = "ABCDEFGHIJKLMN"
					+ "OPQRSTUVWXYZ, "
					+ "1234567890.?:!";
	String[][] letters =  new String[3][14];
	public Printer(String filename){
		tm = new TileMap(filename, 16, 3, 14, 16, 16);
		init();
	}
	
	public void init(){
		for(int row = 0;row<3;row++){
			for(int col = 0;col<14;col++){
				if(row==0){
					letters[row][col]=alphabet.substring(col,col+1);
				}
				else if(row==1){
					letters[row][col]=alphabet.substring(col+14,col+15);
				}else{
					letters[row][col]=alphabet.substring(col+28,col+29);
				}
			//	System.out.println(letters[row][col]);
			}
		}
	}
	public void printOnScreen(Graphics2D g,String text,int x,int y){
		text = text.toUpperCase();
		for(int placer = 0;placer<text.length();placer++){
rowloop:	for(int row = 0;row<3;row++){
				for(int col = 0;col<14;col++){
					if(text.substring(placer,placer+1).equals(letters[row][col])){
						g.drawImage(tm.getTiles(row, col), x+placer*16, y, null);
						break rowloop;
					}
				}
			}		
		}
	}	
}
