package taegyumk_CSCI201_Assignment5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class OceanJLabel extends JLabel {
	public static final long serialVersionUID = 1;

	private ImageIcon icon1;
	private ImageIcon icon2;
	private ImageIcon ship;
	private BattleShip bs;

	private boolean firstOne = true;
	private boolean secondOne = true;
	private boolean startBlow = false;
	private boolean fire = false;
	private boolean water = false;
	
	
	Graphics g;
	private ImageIcon explosion1;
	private ImageIcon explosion2;
	private ImageIcon explosion3;
	private ImageIcon explosion4;
	private ImageIcon explosion5;
	
	private ImageIcon splash1;
	private ImageIcon splash2;
	private ImageIcon splash3;
	private ImageIcon splash4;
	private ImageIcon splash5;
	private ImageIcon splash6;
	private ImageIcon splash7;
	
	private ImageIcon[] explosion;
	private ImageIcon[] explosionVersion1;
	private ImageIcon[] splash;
	private int i = 0;
	private int j = 0;
	
	private boolean sameTime = false;
	
	private boolean imagePause = false;
	
	private shootingClass shooting;
	
	private static Thread changing;
	private boolean delay = false;
	private boolean changeQuestion = false;
	private boolean wholeShip = false;
	private boolean fireTrigger = false;
	private boolean something = false;
	
	public OceanJLabel(ImageIcon icon1, ImageIcon icon2, ImageIcon ship) { 
		
		this.icon1 = icon1;
		this.icon2 = icon2;
		this.ship = ship;
		this.bs = bs;
		this.setOpaque(true);
		splash = new ImageIcon[8];
		
		explosion1 = new ImageIcon("expl1.png");
		explosion2 = new ImageIcon("expl2.png");
		explosion3 = new ImageIcon("expl3.png");
		explosion4 = new ImageIcon("expl4.png");
		explosion5 = new ImageIcon("expl5.png");

		
		splash1 = new ImageIcon("splash1.png");
		splash2 = new ImageIcon("splash2.png");
		splash3 = new ImageIcon("splash3.png");
		splash4 = new ImageIcon("splash4.png");
		splash5 = new ImageIcon("splash5.png");
		splash6 = new ImageIcon("splash6.png");
		splash7 = new ImageIcon("splash7.png");
		
		splash[0] = splash1;
		splash[1] = splash2;
		splash[2] = splash3;
		splash[3] = splash4;
		splash[4] = splash5;
		splash[5] = splash6;
		splash[6] = splash7;
		
		explosionVersion1 = new ImageIcon[7];
		
		explosionVersion1[0] = explosion1;
		explosionVersion1[1] = explosion2;
		explosionVersion1[2] = explosion2;
		explosionVersion1[3] = explosion3;
		explosionVersion1[4] = explosion3;
		explosionVersion1[5] = explosion4;
		explosionVersion1[6] = explosion5;
		
		
		changing = new Thread(){
			public void run() {
				while(true){
					try {
						sleep(250);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					repaint();
				}
			}
		};
	changing.start();
	
	}
	public void delay(long stop) {
		Timer time = new Timer();
		TimerTask task = new TimerTask(){
			public void run() {
				delay = true;
			}
		};
		time.schedule(task, stop);
	}
	public void soundSplash(boolean sunkShip) {
		changeQuestion = true;
		if(!sunkShip) {
			shootingClass sound = new shootingClass("splash.wav", false);	
			sound.start();
		}
			
		imagePause = true;
	
		if(sunkShip){
			delay(4500);
		}
		else{
			delay(2000);
		}
		
	}
	public void soundExplosion() {
		changeQuestion = true;
		shootingClass sound = new shootingClass("explode.wav", false);	
		imagePause = true;
		sound.start();
		delay(2500);

	}
	public void soundSinking() {
		
		changeQuestion = true;
		shootingClass sound = new shootingClass("explode.wav", true);
		imagePause = true;
		sound.start();]
		delay(3000);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			if(firstOne == false){
				g.drawImage(icon2.getImage(), 0, 0, null);
				firstOne = true;
			}
			else {
				g.drawImage(icon1.getImage(), 0, 0, null);
				firstOne = false;
			}
			super.setOpaque(true);
			
			if(startBlow && fire && delay) {
				
				g.drawImage(explosionVersion1[i].getImage(), 10, 10, null);
				i++;
				if(i == 7) {
					startBlow = false;
					fire = false;
					i = 0;
					changeQuestion = false;
					delay = false;
				}
			}
			else if(startBlow && water && delay) {

				g.drawImage(splash[j].getImage(), -10, -10, null);
				j++;
				if(j == 7) {
					startBlow = false;
					water = false;
					j = 0;
					changeQuestion = false;
					delay = false;
					something = false;
				}
			}
			else if(sameTime&&delay) {
				if(i != 7) {
					g.drawImage(explosionVersion1[i].getImage(), 10, 10, null);
					i++;
				}
				else {
					g.drawImage(splash[j].getImage(), -10, -10, null);
					j++;
					if(j == 7) {
						j = 0;
						sameTime = false;
						changeQuestion = false;
						delay = false;
					}
				}
			}else if(wholeShip) {
				wholeShip = false;
			}
			else if(!changeQuestion || something) {
				g.drawImage(ship.getImage(), 10, 10, null);
			}
		}
	
	public void setShip(ImageIcon II) {
		ship = II;
	}
	public void setBlowShip(ImageIcon II) {
		ship = II;
		startBlow = true;
		fire = true;
	}
	public void missShip(ImageIcon II) {
		ship = II;
		startBlow = true;
		water = true;
	}
	public void sinkingShip(ImageIcon II) {
		ship = II;
		startBlow = true;
		water = true;
		something = true;
	}
	public void sameTime(ImageIcon II) {
		ship = II;
		sameTime = true;
	}

}
	 class shootingClass extends Thread {
		 
		private String sound;
		private boolean turnOn;
		
		public shootingClass(String sound, boolean turnOn) {
			this.sound = sound;
			this.turnOn = turnOn;
		}
		
		public void run() {
			SoundLibrary.playSound("cannon.wav");
			
			try {
				sleep(700);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SoundLibrary.playSound(sound);
			if(turnOn) {
				SoundLibrary.playSound("sinking.wav");
			}
			
		}
	}
	
	 
	 
