package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	
	public Menu(Game game, Handler handler, HUD hud){
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu){
			//play button
			if(mouseOver(mx, my, 210, 150, 200, 64)){
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemy();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				
			}
			
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				Game.gameState = STATE.Help;
				
				
			}
			
			//quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				System.exit(1);
			}
		}
		
			//back button for help
			if(Game.gameState == STATE.Help){
				if(mouseOver(mx, my, 210, 350, 200, 64)){
					Game.gameState = STATE.Menu;
					return;
				}
			}
			//try again button for game over
			if(Game.gameState == STATE.End){
				if(mouseOver(mx, my, 210, 350, 200, 64)){
					Game.gameState = STATE.Game;
					hud.setLevel(1);
					hud.setScore(0);
					handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
					handler.clearEnemy();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
		}				
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if (mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Neat-o", 240, 70);
			
			
			g.setFont(fnt2);
			g.drawRect(220, 150, 200, 64);
			g.drawString("Play", 288, 190);
			
			g.drawRect(220, 250, 200, 64);
			g.drawString("Help", 288, 290);
			
			g.drawRect(220, 350, 200, 64);
			g.drawString("Quit", 288, 390);
			
		}else if(Game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 270, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move around and dodge enemies", 80 ,200);
			
			g.setFont(fnt2);
			g.drawRect(220, 350, 200, 64);
			g.drawString("Back", 285, 390);
		}else if(Game.gameState == STATE.End){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			Font fnt4 = new Font("arial", 1, 10);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 180 ,200);
			
			g.setFont(fnt2);
			g.drawRect(220, 350, 200, 64);
			g.drawString("Try Again", 250, 390);
			
			g.setFont(fnt4);
			g.drawRect(220, 350, 200, 64);
			g.drawString("loser", 600, 435);
		}
	
	}

}
