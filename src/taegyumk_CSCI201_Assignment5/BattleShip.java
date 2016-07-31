package taegyumk_CSCI201_Assignment5;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Vector;
import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;

public class BattleShip extends JFrame{
	
	public static final long serialVersionUID = 1;
//coordinate player 
	private JPanel Field;
	private static JLabel[][] coordinate;
	private static char[][] coordi;
//score
	private JLabel[][] highscore;
	private JPanel scoreField;
	private JPanel outScoreField;
//computer coordinate
	private JPanel FieldC;
	private static JLabel[][] coordinateC;
	private static char[][] coordiC;
	private static boolean[][] coordinateSaver;
	protected static int counter;
	//protected static boolean[][] checked;
	
//bottom sign
	private JButton selectFile;
	private JButton gameStart;
	private static JLabel logLabel;
	private JLabel chosenFile;
	
//game .battle file
	private static String battleFile;
//game starter
	private static boolean starter;
	private static String filePath;
	private static String chosenShip;
	private static SelectShip ss;
	private static int destroyCounter = 2;
	
	private static Vector<String> shipList;
//game 
	private static int Countaircraft = 5;
	private static int Countbattlship = 4;
	private static int Countcruiser = 3;
	private static int Countdestroyer = 2;
	private static int Countdestroyer1 = 2;
	
	private static int CountaircraftC = 5;
	private static int CountbattlshipC = 4;
	private static int CountcruiserC = 3;
	private static int CountdestroyerC = 2;
	private static int CountdestroyerC1 = 2;
	//TODO
	private static boolean[][] playerSaver;
	private static boolean[][] computerSaver;
//regarding menu bar
	//image
	private static ImageIcon A;
	private static ImageIcon B;
	private static ImageIcon C;
	private static ImageIcon D;
	private static ImageIcon question;
	private static ImageIcon miss;
	private static ImageIcon hit;
//destroyers boolean
	private static boolean destroyerD = false;
	private static String str;
	private static boolean filechooserCheck = false;
	
	
	
//For HW 4
	private static JLabel timeRemain;
	private static Timer time;
	private static TimerTask task;
	private static int second = 15;
	private static int turnCount = 0;
	
	private static JTextArea gameLog;
	private static String gameRecord;
	private static int roundCounter = 1;
	private static boolean playerFinish = false;
	private static boolean computerFinish = false;
	private static Image img;
	private static Image backgroundImage;
	private static Image backgroundImage1;
	
	private static JLabel[][] oceanCoordinate;
	private static ImageIcon ocean1;
	private static ImageIcon ocean2;
	private static OceanJLabel oceanBack;
	private static boolean movingOcean = true;
	private static JLabel[][] OJ;
	private static JLabel[][] OJ2;
	private static int computerWait = 0;
	
	private static SoundLibrary makeSound;
	private static Thread soundThread;
	private static boolean trueShoot = false;
	
	private static Timer shooting;
	private static TimerTask shooting1;
	private static Thread haha;
	private static boolean countStop = false;
	private static boolean connection_true = false;
	
	//hw 5
	private static IpMenu im;
	private static String ip;
	private static BattleShip BS;
	private static JPanel panel5;
	private static JCheckBox chatFilter;
	private static JCheckBox eventFilter;
	
	private static JPanel panel6;
	private static JLabel chatPlayer;
	private static JTextField chattingText;
	private static JButton chatSend;
	private static String chattingRecord;
	private static String bothRecord;
	private static String nothingRecord;
	
	private static boolean isHost = false;
	private static boolean isClient = false;

	private static Vector<String> holdingData;
	private static Client client;
	private static JLabel playerLabel;
	private static JLabel computerLabel;
	private static int timeCounter = 30;
	private boolean gameConnection = false;
	
	private String IpAddress;
	private int port;
	private ServerSocket sso;
	private Socket s;
	private ObjectOutputStream OOS;
	private ObjectInputStream OIS;
	private Vector<ServerThread> ctVector;
	ServerThread ct;
	private static String currentPlayer = "currentPlayer";
	private static String otherPlayer = "otherPlayer";
	private boolean placeD = false;
	private String pReady = "A";
	private String oReady = "A";
	
	public BattleShip() {
		
		super("Battleship");
		
		ctVector = new Vector<ServerThread>();

		setLayout(new GridLayout());
		setSize(900, 600);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		Field = new JPanel(new GridLayout(11,11));
		//setBackground(Color.RED);
		//menu bar
		JMenuBar jmb = new JMenuBar();
		JMenu info = new JMenu("Info");
		JMenuItem howTo = new JMenuItem("How To");
		JMenuItem about = new JMenuItem("About");
		
		info.add(howTo);
		info.add(about);
		jmb.add(info);
		setJMenuBar(jmb);
		
		howTo.setMnemonic('A');
		howTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		about.setMnemonic('S');
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		//for about
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				About ab = new About();
				ab.setVisible(true);
			}
		});
		howTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				howto hw = new howto(str);
				hw.setVisible(true);
				hw.getArea().setLineWrap(true);
			}
		});
		//waring
		
		coordinate = new JLabel[11][11];
		coordinate[0][0] = new JLabel("A");
		coordinate[1][0] = new JLabel("B");
		coordinate[2][0] = new JLabel("C");
		coordinate[3][0] = new JLabel("D");
		coordinate[4][0] = new JLabel("E");
		coordinate[5][0] = new JLabel("F");
		coordinate[6][0] = new JLabel("G");
		coordinate[7][0] = new JLabel("H");
		coordinate[8][0] = new JLabel("I");
		coordinate[9][0] = new JLabel("J");
		coordinate[10][0] = new JLabel("");

		OJ = new JLabel[11][11];
		
		//TODO
		for(int i = 0; i < 11; i++) {
			Field.add(coordinate[i][0]);
			for(int j = 0; j < 10; j++) {
				if(i == 10) {
					coordinate[10][j] = new JLabel("   "+String.valueOf(j+1));
					Field.add(coordinate[10][j]);
				}
				else {
					OJ[i][j] = new OceanJLabel(ocean1,ocean2,question);
					OJ[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
					Field.add(OJ[i][j]);
				}
			}
		}
		
		Field.setBorder(BorderFactory.createLineBorder(Color.black));

		FieldC = new JPanel(new GridLayout(11,11));
		
		coordinateC = new JLabel[11][11];
		coordinateC[0][0] = new JLabel("A");
		coordinateC[1][0] = new JLabel("B");
		coordinateC[2][0] = new JLabel("C");
		coordinateC[3][0] = new JLabel("D");
		coordinateC[4][0] = new JLabel("E");
		coordinateC[5][0] = new JLabel("F");
		coordinateC[6][0] = new JLabel("G");
		coordinateC[7][0] = new JLabel("H");
		coordinateC[8][0] = new JLabel("I");
		coordinateC[9][0] = new JLabel("J");
		coordinateC[10][0] = new JLabel("");
		
		OJ2 = new JLabel[11][11];
		
		for(int i = 0; i < 11; i++) {
			FieldC.add(coordinateC[i][0]);
			for(int j = 0; j < 10; j++) {
				if(i == 10) {
					coordinateC[10][j] = new JLabel("   "+String.valueOf(j+1));
					FieldC.add(coordinateC[10][j]);
				}
				else {
					OJ2[i][j] = new OceanJLabel(ocean1,ocean2,question);
					FieldC.add(OJ2[i][j]);	
					OJ2[i][j].setBorder(BorderFactory.createLineBorder(Color.red));
				}
			}
		}
		FieldC.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		//end of coordinate
		scoreField = new JPanel(new GridLayout(11,11));
		outScoreField = new JPanel();
		highscore = new JLabel[11][1];
		highscore[1][0] = new JLabel("Highscores : ");
		scoreField.add(highscore[1][0]);
		for(int i = 1; i < 11; i++) {
			highscore[i][0] = new JLabel(i+":");
			scoreField.add(highscore[i][0]);
		}
		
		outScoreField.add(scoreField);

		//add layouts to main layout
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		//first line
		playerLabel = new JLabel(currentPlayer);
		computerLabel = new JLabel(otherPlayer);
		timeRemain = new JLabel("Time - 0:"+second);
			
		//TODO
		JPanel playerPanel = new JPanel();
		JPanel computerPanel = new JPanel();
		JPanel timePanel = new JPanel();
		playerPanel.add(playerLabel);
		computerPanel.add(computerLabel);
		timePanel.add(timeRemain);
		
		//second line
		panel2.setLayout(new GridLayout(1,3));
		panel2.add(playerPanel);
		panel2.add(timePanel);
		panel2.add(computerPanel);
		//third line
		selectFile = new JButton("Select File...");
		gameStart = new JButton("START");
		gameStart.setEnabled(false);
		logLabel = new JLabel("log:You are in edit mode, click to place your ship");
		chosenFile = new JLabel("File:");
		
		//when user clicks file button
		selectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setCurrentDirectory(new File("scr/"));
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("battle FILES", "battle", "battle");      
		        fileChooser.setFileFilter(filter);
		  
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();
		          battleFile = selectedFile.getName();
				  StringTokenizer st = new StringTokenizer(battleFile);
				  String temp0 = st.nextToken(".");
		          chosenFile.setText("File: "+temp0);
		          filechooserCheck = true;
		          if(shipList.size() == 1)  {
		        	  gameStart.setEnabled(true);  
		          }
		          
		          filePath = fileChooser.getSelectedFile().getAbsolutePath();
		        }
			}
		});
		
		//when user click player JLabel
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				String represent = getAlphabet(i,j);
	        	int x = i+1;
	        	int y = j+1;
	        	if(!starter) {
					OJ[i][j].addMouseListener(new MouseAdapter() {  
					    public void mouseClicked(MouseEvent e)  
					    {  
					    	if(coordi[x-1][y-1] == 'X')
					    	{//place component's ship
					    		openUpSelection(represent, x, y);
					    		if(shipList.size() == 1) {
					    			gameStart.setEnabled(true);
					    		}
					    		else {
					    			gameStart.setEnabled(false);
					    		}
					    	}
					    	else {
					    		if(!starter) {
					    			reset(x, y);
					    		}
					    	}
					    }  
					}); 
	        	}
			}	
		}

		//user click Jlabel while playing game
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				String represent = getAlphabet(i,j);
				int x = i+1;
				int y = j+1;
						OJ2[i][j].addMouseListener(new MouseAdapter() { 
					    public void mouseClicked(MouseEvent e)   {
					    	if(starter && !playerFinish) {
					    		if(playerSaver[x-1][y-1] == false) {
					    	    	playGameUser(x,y);
					    	    	String ix = String.valueOf(x);
					    	    	String iy = String.valueOf(y);
					    	    	if(isClient) {
					    	    		try {
											OOS.writeObject("other:"+ix+":"+iy);
											OOS.flush();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
					    	    	}else if(isHost) {
					    	    		ct.sendMessage("other:"+ix+":"+iy);
					    	    	}
					    		}
				
					    	}
					    }  
					}); 
			}	
		}
		JPanel rightSection = new JPanel();
		JPanel leftSection = new JPanel();
	
		rightSection.add(chosenFile);
		rightSection.add(gameStart);
		
		leftSection.add(logLabel);
		
		panel3.add(leftSection);
		panel3.add(rightSection);
		
		panel3.setLayout(new GridLayout(1,1));
		panel1.setLayout(new GridLayout(1,1));
		panel1.add(Field);
		panel1.add(FieldC);
        
		//timeStart();
		// user clicks start button
		gameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				rightSection.setVisible(false);
				leftSection.setVisible(false);
				starter = true;
				logLabel.setText("log:"+" "+"Player: N/A"+" "+"Computer: N/A");
				gameRecord = "Round "+ roundCounter;
				gameLog.setText(gameRecord);
				Document d1 = gameLog.getDocument();
				gameLog.select(d1.getLength(), d1.getLength());
				roundCounter++;
				for(int i = 0; i < 10; i++) {
					for(int j = 0; j <10; j++) {
						System.out.print(coordiC[i][j]);
					}
					System.out.println("");
				}
				
				if(!isHost && !isClient){
					//computerWithComputer(coordiC);
					timeStart();
				}
				pReady = "B";
				if(isClient) {
					
					try {
						OOS.writeObject("start:"+B);
						OOS.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if(isHost) {
					
					ct.sendMessage("start:"+B);
				}
			}
		});
		
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.NORTH);
		add(panel3, BorderLayout.SOUTH);
		
		//starting log!!!!!!
		JPanel panel4 = new JPanel();
		gameLog = new JTextArea();
		JScrollPane JP = new JScrollPane(gameLog);
		JPanel logHolder = new JPanel();
		JP.setPreferredSize( new Dimension( 100, 80));
		logHolder.add(JP);

		//check boxes
		panel5 = new JPanel();
		panel5.setLayout(new GridLayout(3,1));
		JLabel filter = new JLabel("Filter:");
		chatFilter = new JCheckBox("Chat");
		eventFilter = new JCheckBox("Events");
		panel5.add(filter);
		panel5.add(chatFilter);
		panel5.add(eventFilter);
		
		//chatting!!
		panel6 = new JPanel();
		chatPlayer = new JLabel("player1");
		chattingText = new JTextField();
		chattingText.setPreferredSize( new Dimension( 500, 30));
		chatSend = new JButton("Send");
		panel6.add(chatPlayer);
		panel6.add(chattingText);
		panel6.add(chatSend);
		
		bothRecord = "BOTH!!!!!!!!";
		gameRecord = "event start!";
		chattingRecord = "chatting start";
		nothingRecord = "nothing is selected";
		gameLog.setText("");
		
		
		chatSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//transfer chatting to client 
				String sendChat = chattingText.getText();
				//asdasdasd
				if(isHost) {
					String chatFromHost = "chat: "+ sendChat;
					ct.sendMessage(chatFromHost);
				}else if(isClient) {
					String chatFromHost = "chat: "+ sendChat;
					try {
						OOS.writeObject(chatFromHost);
						OOS.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				chattingRecord = chattingRecord + "\n" + currentPlayer+" : "+sendChat;
				bothRecord = bothRecord + "\n" + chattingRecord;
				
				System.out.println(sendChat);
				chattingText.setText("");
			}
		}); 
		panel4.setLayout(new BorderLayout());
		panel4.add(panel3, BorderLayout.NORTH);
		panel4.add(JP, BorderLayout.CENTER);
		panel4.add(panel5, BorderLayout.EAST);
		panel4.add(panel6, BorderLayout.SOUTH);
		//making buttons

		chatFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(eventFilter.isSelected() && chatFilter.isSelected()) {
					gameLog.setText(bothRecord);
				}else if (chatFilter.isSelected() && !eventFilter.isSelected()) {
					gameLog.setText(chattingRecord);
				}else if (!chatFilter.isSelected() && eventFilter.isSelected()) {
					gameLog.setText(gameRecord);
				}else if (!chatFilter.isSelected() && !eventFilter.isSelected()) {
					gameLog.setText(nothingRecord);
				}
			}
		});
		
		eventFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if(eventFilter.isSelected() && chatFilter.isSelected()) {
					gameLog.setText(bothRecord);
				}else if (chatFilter.isSelected() && !eventFilter.isSelected()) {
					gameLog.setText(chattingRecord);
				}else if (!chatFilter.isSelected() && eventFilter.isSelected()) {
					gameLog.setText(gameRecord);
				}else if (!chatFilter.isSelected() && !eventFilter.isSelected()) {
					gameLog.setText(nothingRecord);
				}

			}
		});
		
		Thread recordUpdate = new Thread() {
			public void run() {
				while(true) {
					if(eventFilter.isSelected() && !(chatFilter.isSelected())) {
						gameLog.setText(gameRecord);
					}else if(chatFilter.isSelected() && !(eventFilter.isSelected())) {
						gameLog.setText(chattingRecord);
					}else if(eventFilter.isSelected() && chatFilter.isSelected()) {
						gameLog.setText(bothRecord);
					}
					
					
				}
			}
		};
		recordUpdate.start();
		
		Thread gameStart = new Thread() {
			public void run() {
				while(true) {
					System.out.println("S");
					if(pReady.equals("B") && oReady.equals("B")) {	
						timeStart();
						break;
					}
					
				}
			}
		};
		gameStart.start();
		
		//gameRecord = "Waiting......";
		gameLog.setText(gameRecord);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.NORTH);
		add(panel4, BorderLayout.SOUTH);
	
	}
	public void computerWithComputer(char[][] computerPlay) {
			coordiC = computerPlay;
			computerCoordi(coordiC);
	}
	public void setMyName(String myName) {
		playerLabel.setText(myName);
		
	}
	public void setYourName(String yourName) {
		computerLabel.setText(yourName);
	}
	
	public void showToUser() {
		setVisible(true);
	}
	public void closeGUI() {
		setVisible(false);
	}
	public void reset(int x, int y) {
		if(coordi[x-1][y-1] == 'A') {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'A') {
						coordi[i][j] = 'X';
						((OceanJLabel) OJ[i][j]).setShip(question);
						//coordinate[i][j].setIcon(question);
					}
				}
			}
			shipAdd("Aircraft Carrier");
		}
		else if(coordi[x-1][y-1] == 'B')
		{
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'B') {
						coordi[i][j] = 'X';
						((OceanJLabel) OJ[i][j]).setShip(question);
					}
				}
			}
			shipAdd("Battleship");
		}
		else if(coordi[x-1][y-1] == 'C') {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'C') {
						coordi[i][j] = 'X';
						((OceanJLabel) OJ[i][j]).setShip(question);
					}
				}
			}
			shipAdd("Cruiser");
		}
		else if(coordi[x-1][y-1] == 'D') {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'D') {
						coordi[i][j] = 'X';
						((OceanJLabel) OJ[i][j]).setShip(question);
						//coordinate[i][j].setIcon(question);
					}
				}
			}
			if(destroyCounter==0) {
				shipAdd("Destroyer");
			}
			destroyCounter++;
			destroyerD = false;
		}
		else if(coordi[x-1][y-1] == 'E') {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'E') {
						coordi[i][j] = 'X';
						((OceanJLabel) OJ[i][j]).setShip(question);
						coordinate[i][j].setIcon(question);
					}
				}
			}
			if(destroyCounter==0) {
				shipAdd("Destroyer");
			}
			destroyCounter++;
		}
		if(shipList.size() == 1) {
			gameStart.setEnabled(true);
		}
		else {
			gameStart.setEnabled(false);
		}
	}

	//end of GUI
	public void openUpSelection(String represent, int x, int y) {
    	if(!starter)
    	{
    		ss = new SelectShip(represent, shipList);
        	ss.setVisible(true);
    		ss.getSelectionButton().addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ae) {
    				ss.setShip();
    				ss.setVisible(false);
    				//saving ship location in 2d char array and boolean!
					String ship = ss.getchosenShip();
					char direction = ss.getShipDirection();
					String sendingDirection = String.valueOf(direction);
					String xPlace = String.valueOf(x);
					String yPlace = String.valueOf(y);
					if(isHost) {
						ct.sendMessage("ship:"+ship+":"+sendingDirection+":"+xPlace+":"+yPlace);
					} else if(isClient) {
						try {
							OOS.writeObject("ship:"+ship+":"+sendingDirection+":"+xPlace+":"+yPlace);
							OOS.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					placeShip(ship, direction, x, y);
		    		if(shipList.size() == 1) {
		    			gameStart.setEnabled(true);
		    		}
		    		else {
		    			gameStart.setEnabled(false);
		    		}
    			}
    		});
    	}
	}
	public static void playGameUser(int x, int y) {
		
		String represent = getAlphabet(x-1,y-1); //holding coordinate
		if(coordiC[x-1][y-1] == 'X') {
			if(playerSaver[x-1][y-1] == false) {
				((OceanJLabel) OJ2[x-1][y-1]).missShip(miss);
				((OceanJLabel) OJ2[x-1][y-1]).soundSplash(false);
				playerSaver[x-1][y-1] = true;
				gameRecord = gameRecord +"\n"+ currentPlayer +" hit " + represent + " and missed " + "(0:" +second +")";
				bothRecord = bothRecord + "\n" + gameRecord;
				gameLog.setText(gameRecord);
				Document d = gameLog.getDocument();
				gameLog.select(d.getLength(), d.getLength());
			}
		}
		else if(coordiC[x-1][y-1] == 'A') {
			if(playerSaver[x-1][y-1] == false) {
				Countaircraft--;
				playerSaver[x-1][y-1] = true;
				
				if(Countaircraft == 0) {
					((OceanJLabel) OJ2[x-1][y-1]).sameTime(A);
					((OceanJLabel) OJ2[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordiC[i][j] == 'A') {
								if( !((x-1)==i && (y-1)==j) ) {
								((OceanJLabel) OJ2[i][j]).sinkingShip(A)
								((OceanJLabel) OJ2[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n"+ currentPlayer + " hit " + represent + " and hit aircraft " + "(0:" +second +")";
					gameRecord = gameRecord + "\n"+ currentPlayer + " sunk "+otherPlayer+"'s aircraft";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ2[x-1][y-1]).soundExplosion();
					((OceanJLabel) OJ2[x-1][y-1]).setBlowShip(A);
					
					gameRecord = gameRecord +"\n"+ currentPlayer + " hit " + represent + " and hit aircraft " + "(0:" +second +")";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
			}	
		}
		else if(coordiC[x-1][y-1] == 'B') {
			if(playerSaver[x-1][y-1] == false) {
				Countbattlship--;
				playerSaver[x-1][y-1] = true;
				if(Countbattlship == 0) {
					((OceanJLabel) OJ2[x-1][y-1]).sameTime(B);
					((OceanJLabel) OJ2[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordiC[i][j] == 'B') {
								if( !((x-1)==i && (y-1)==j) ) {
									((OceanJLabel) OJ2[i][j]).sinkingShip(B);
									((OceanJLabel) OJ2[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n"+ currentPlayer + " hit "  + represent + " and hit battleship " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ currentPlayer +" sunk "+ otherPlayer +"'s battleship";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ2[x-1][y-1]).setBlowShip(B);
					((OceanJLabel) OJ2[x-1][y-1]).soundExplosion();
					
					gameRecord = gameRecord +"\n"+ currentPlayer + " hit " + represent + " and hit battleship " + "(0:" +second +")";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
		}
		else if(coordiC[x-1][y-1] == 'C') {
			if(playerSaver[x-1][y-1] == false) {
				Countcruiser--;
				playerSaver[x-1][y-1] = true;
				if(Countcruiser == 0) {
					((OceanJLabel) OJ2[x-1][y-1]).sameTime(C);
					((OceanJLabel) OJ2[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordiC[i][j] == 'C') {
								if( !((x-1)==i && (y-1)==j) ) {
									((OceanJLabel) OJ2[i][j]).sinkingShip(C);
									((OceanJLabel) OJ2[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n"+ currentPlayer + " hit " + represent + " and cruiser " + "(0:" +second +")";
					gameRecord = gameRecord + "\n" +currentPlayer+" sunk "+otherPlayer+ "'s cruiser";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ2[x-1][y-1]).setBlowShip(C);
					((OceanJLabel) OJ2[x-1][y-1]).soundExplosion();
					
					gameRecord = gameRecord +"\n"+ currentPlayer + " hit " + represent + " and cruiser " + "(0:" +second +")";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
		}
		else if(coordiC[x-1][y-1] == 'D') {
			if(playerSaver[x-1][y-1] == false) {
				Countdestroyer--;
				playerSaver[x-1][y-1] = true;
				if(Countdestroyer == 0) {
					((OceanJLabel) OJ2[x-1][y-1]).sameTime(D);
					((OceanJLabel) OJ2[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordiC[i][j] == 'D') {
								if( !((x-1)==i && (y-1)==j) ) {
									((OceanJLabel) OJ2[i][j]).sinkingShip(D);
									((OceanJLabel) OJ2[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord + "\n"+ currentPlayer + " hit " + represent + " and hit destroyer " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ currentPlayer + " sunk "+ otherPlayer +"'s destroyer";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ2[x-1][y-1]).setBlowShip(D);
					((OceanJLabel) OJ2[x-1][y-1]).soundExplosion();
					
					gameRecord = gameRecord + "\n"+ currentPlayer + " hit "  + represent + " and hit destroyer " + "(0:" +second +")";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
		}
		else if(coordiC[x-1][y-1] == 'E') {
			if(playerSaver[x-1][y-1] == false) {
				Countdestroyer1--;
				playerSaver[x-1][y-1] = true;
				if(Countdestroyer1 == 0) {
					((OceanJLabel) OJ2[x-1][y-1]).sameTime(D);
					((OceanJLabel) OJ2[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordiC[i][j] == 'E') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ2[i][j]).sinkingShip(D);
									((OceanJLabel) OJ2[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + currentPlayer + " hit "  + represent + " and destroyer " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ currentPlayer + " sunk "+ otherPlayer+"'s + destroyer";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ2[x-1][y-1]).setBlowShip(D);
					((OceanJLabel) OJ2[x-1][y-1]).soundExplosion();
					gameRecord = gameRecord +"\n" + currentPlayer + " hit " + represent + " and destroyer " + "(0:" +second +")";
					bothRecord = bothRecord + "\n" + gameRecord;
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
		}
		else {
			System.out.println("Wrong type of ship")
		}
		if(Countaircraft==0&&Countbattlship==0&&Countcruiser==0&&Countdestroyer==0&&Countdestroyer1==0){	
			Thread th = new Thread() {
				public void run() {
					try {
						time.cancel();
						sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					GameOver go = new GameOver(currentPlayer+" Won!"+ "Play Again?");
					go.setVisible(true);
					go.getOK().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {

							A = new ImageIcon("A.png");
							B = new ImageIcon("B.png");
							C = new ImageIcon("C.png");
							D = new ImageIcon("D.png");
							miss = new ImageIcon("no.png");
							hit = new ImageIcon("fire.png");
							question  = new ImageIcon("question.png");
							starter = false;
							Scanner input = new Scanner(System.in);

							coordi = new char [10][10]; // it is char 2D array size of 10
							for(int i = 0; i < 10; i++) {
								for(int j = 0; j < 10; j++) {
									coordi[i][j] = 'X';
								}
							}
							shipList = new Vector<String>();
							shipList.add("Select Ship");
							shipList.add("Aircraft Carrier");
							shipList.add("Battleship");
							shipList.add("Cruiser");
							shipList.add("Destroyer");
							
							Countdestroyer = 2;
							Countdestroyer1 = 2;
							Countaircraft = 5;
							Countcruiser = 4;
							Countbattlship = 3;
							second = 15;
							countStop = false;
							playerSaver = new boolean[10][10];
							computerSaver = new boolean[10][10];
							BS.setVisible(false);
							
							BattleShip BS = new BattleShip();
							
							BS.setVisible(true);
						}
					});
					go.getNO().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {

							A = new ImageIcon("A.png");
							B = new ImageIcon("B.png");
							C = new ImageIcon("C.png");
							D = new ImageIcon("D.png");
							miss = new ImageIcon("no.png");
							hit = new ImageIcon("fire.png");
							question  = new ImageIcon("question.png");
							starter = false;
							Scanner input = new Scanner(System.in);

							coordi = new char [10][10]; // it is char 2D array size of 10
							for(int i = 0; i < 10; i++) {
								for(int j = 0; j < 10; j++) {
									coordi[i][j] = 'X';
								}
							}
							shipList = new Vector<String>();
							shipList.add("Select Ship");
							shipList.add("Aircraft Carrier");
							shipList.add("Battleship");
							shipList.add("Cruiser");
							shipList.add("Destroyer");
							
							Countdestroyer = 2;
							Countdestroyer1 = 2;
							Countaircraft = 5;
							Countcruiser = 4;
							Countbattlship = 3;
							second = 15;
							countStop = false;
							playerSaver = new boolean[10][10];
							computerSaver = new boolean[10][10];
							BS.setVisible(false);
							
							BattleShip BS = new BattleShip();
					
							BufferedReader in;
							try {
								URL toCheckIp = new URL("http://checkip.amazonaws.com");
								in = new BufferedReader(new InputStreamReader(toCheckIp.openStream()));
								ip = in.readLine();
							} catch (UnknownHostException  es) {
								ip = "Error";
							} catch (IOException e) {
								// TODO Auto-generated catch block	
								e.printStackTrace();
							}
							IpMenu im = new IpMenu(BS,ip);
						}
					});
				}
			};	
			th.start();	
		}
		playerFinish = true;
	}
	public static void computerPlay() {

		Random rg = new Random();
	    int x = 0;
	    int y = 0;
	    
	    String computerUpdate = " ";
	    turnCount++;
	    
	    while(true) {
		    x = rg.nextInt(10);
		    y = rg.nextInt(10);
		    if(computerSaver[x][y] == false) {
		    	computerUpdate = getAlphabet(x,y);
		    	 break;
		    }
	    }

		if(coordi[x][y] == 'X') {
			if(computerSaver[x][y] == false) {
				((OceanJLabel) OJ[x][y]).missShip(miss);
				((OceanJLabel) OJ[x][y]).soundSplash(false);
				computerSaver[x][y] = true;
				gameRecord = gameRecord +"\n" + "computer hit " + computerUpdate + " and missed " + "(0:" +second +")";
				gameLog.setText(gameRecord);
				Document d = gameLog.getDocument();
				gameLog.select(d.getLength(), d.getLength());
			}
		}
		else if(coordi[x][y] == 'A') {
			if(computerSaver[x][y] == false) {
				CountaircraftC--;
				computerSaver[x][y] = true;
				if(CountaircraftC == 0) {
					
					((OceanJLabel) OJ[x-1][y-1]).sameTime(hit);
					((OceanJLabel) OJ[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'A') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + "computer hit " + computerUpdate + " and hit aircraft " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ "computer sunk player's aircraft";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + "computer hit " + computerUpdate + " and hit aircraft " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
			
		}
		else if(coordi[x][y] == 'B') {
			if(computerSaver[x][y] == false) {
				CountbattlshipC--;
				computerSaver[x][y] = true;
				if(CountbattlshipC == 0) {
					((OceanJLabel) OJ[x-1][y-1]).sameTime(hit);
					((OceanJLabel) OJ[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'B') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + "computer hit " + computerUpdate + " and hit battleship " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ "computer sunk player's battleship";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + "computer hit " + computerUpdate + " and hit battleship " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
		}
		else if(coordi[x][y] == 'C') {
			if(computerSaver[x][y] == false) {
				CountcruiserC--;
				computerSaver[x][y] = true;
				if(CountcruiserC == 0) {
					((OceanJLabel) OJ[x-1][y-1]).sameTime(hit);
					((OceanJLabel) OJ[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'C') {
								if( !((x-1)==i && (y-1)==j) ) {
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + "computer hit " + computerUpdate + " and hit cruiser " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ "computer sunk player's cruiser";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + "computer hit " + computerUpdate + " and hit cruiser " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
		}
		else if(coordi[x][y] == 'D') {
			if(computerSaver[x][y] == false) {
				CountdestroyerC--;
				computerSaver[x][y] = true;
				if(CountdestroyerC == 0) {
					((OceanJLabel) OJ[x-1][y-1]).sameTime(hit);
					((OceanJLabel) OJ[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'D') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + "computer hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ "computer sunk player's destroyer";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + "computer hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
		}
		else if(coordi[x][y] == 'E') {
			if(computerSaver[x][y] == false) {
				CountdestroyerC1--;
				computerSaver[x][y] = true;
				if(CountdestroyerC1 == 0) {
					((OceanJLabel) OJ[x-1][y-1]).sameTime(hit);
					((OceanJLabel) OJ[x-1][y-1]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'D') {
								if( !((x-1)==i && (y-1)==j) ) {
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + "computer hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ "computer sunk player's destroyer";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else {
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + "computer hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}

		}
		
		if(CountaircraftC==0&&CountbattlshipC==0&&CountcruiserC==0&&CountdestroyerC==0&&CountdestroyerC1==0){
			
			Thread th = new Thread() {
				public void run() {
					try {
						time.cancel();
						sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					GameOver go = new GameOver("Computer Won!");
					go.setVisible(true);
					go.getOK().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {

							A = new ImageIcon("A.png");
							B = new ImageIcon("B.png");
							C = new ImageIcon("C.png");
							D = new ImageIcon("D.png");
							miss = new ImageIcon("no.png");
							hit = new ImageIcon("fire.png");
							question  = new ImageIcon("question.png");
							starter = false;
							Scanner input = new Scanner(System.in);

							coordi = new char [10][10]; // it is char 2D array size of 10
							for(int i = 0; i < 10; i++) {
								for(int j = 0; j < 10; j++) {
									coordi[i][j] = 'X';
								}
							}
							shipList = new Vector<String>();
							shipList.add("Select Ship");
							shipList.add("Aircraft Carrier");
							shipList.add("Battleship");
							shipList.add("Cruiser");
							shipList.add("Destroyer");
							
							Countdestroyer = 2;
							Countdestroyer1 = 2;
							Countaircraft = 5;
							Countcruiser = 4;
							Countbattlship = 3;
							second = 15;
							countStop = false;
							playerSaver = new boolean[10][10];
							computerSaver = new boolean[10][10];
							BattleShip bs = new BattleShip();
							bs.setVisible(true);
						}
					});
					go.getNO().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {

							A = new ImageIcon("A.png");
							B = new ImageIcon("B.png");
							C = new ImageIcon("C.png");
							D = new ImageIcon("D.png");
							miss = new ImageIcon("no.png");
							hit = new ImageIcon("fire.png");
							question  = new ImageIcon("question.png");
							//question.setImage("asd.sd");
							starter = false;
							Scanner input = new Scanner(System.in);

							coordi = new char [10][10]; // it is char 2D array size of 10
							for(int i = 0; i < 10; i++) {
								for(int j = 0; j < 10; j++) {
									coordi[i][j] = 'X';
								}
							}
							shipList = new Vector<String>();
							shipList.add("Select Ship");
							shipList.add("Aircraft Carrier");
							shipList.add("Battleship");
							shipList.add("Cruiser");
							shipList.add("Destroyer");
							
							Countdestroyer = 2;
							Countdestroyer1 = 2;
							Countaircraft = 5;
							Countcruiser = 4;
							Countbattlship = 3;
							second = 15;
							countStop = false;
							playerSaver = new boolean[10][10];
							computerSaver = new boolean[10][10];
							BS.setVisible(false);
							
							BattleShip BS = new BattleShip();
							
							BufferedReader in;
							try {
								URL toCheckIp = new URL("http://checkip.amazonaws.com");
								in = new BufferedReader(new InputStreamReader(toCheckIp.openStream()));
								ip = in.readLine();
							} catch (UnknownHostException  es) {
								ip = "Error";
							
							} catch (IOException e) {
								// TODO Auto-generated catch block	
								e.printStackTrace();
							}
							IpMenu im = new IpMenu(BS,ip);
						}
					});
				}
			};
			th.start();	
		}
		computerFinish = true;
	}
	public static void main(String [] args){
		//reading textfile
		makeSound = new SoundLibrary();
		A = new ImageIcon("A.png");
		B = new ImageIcon("B.png");
		C = new ImageIcon("C.png");
		D = new ImageIcon("D.png");
		miss = new ImageIcon("no.png");
		hit = new ImageIcon("fire.png");
		question  = new ImageIcon("question.png");
		ocean1  = new ImageIcon("water1.png");
		ocean2  = new ImageIcon("water2.png");
		starter = false;
		Scanner input = new Scanner(System.in);
		Vector<Player> playerList = new Vector<Player>();
		coordi = new char [10][10]; // it is char 2D array size of 10
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				coordi[i][j] = 'X';
			}
		}
		shipList = new Vector<String>();
		shipList.add("Select Ship");
		shipList.add("Aircraft Carrier");
		shipList.add("Battleship");
		shipList.add("Cruiser");
		shipList.add("Destroyer");
		
		playerSaver = new boolean[10][10];
		computerSaver = new boolean[10][10];
		//reading textfile for instruction
		try{
			FileReader fr = new FileReader("howto.txt");
			Scanner sc = new Scanner(fr);
			str = "Welcome to battle ship!" + "\n" + "\n";
			while(sc.hasNextLine()) {
				str = str + sc.nextLine()+ "\n";	
			}
			fr.close();
			sc.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("No file! check it again!");
			System.exit(0);
		} catch (IOException ioe) {
			System.out.println("Error occurs! try again.");
			System.exit(0);
		}
		
		BufferedReader in;
		try {
			System.out.println("asda");
			URL toCheckIp = new URL("http://checkip.amazonaws.com");
			in = new BufferedReader(new InputStreamReader(toCheckIp.openStream()));
			ip = in.readLine();
		} catch (UnknownHostException  es) {
			ip = "Error";
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BS = new BattleShip();
		im = new IpMenu(BS, ip);
		im.setVisible(true);
		
	}//end of main static
	

	public static void timeStart() {
		
		if(isHost || isClient) {
			computerCoordi(coordiC);	
		}
		System.out.println("asdaasd");
		Random rg = new Random();
		computerWait = 1 + rg.nextInt(13);
		
		time = new Timer();
		task = new TimerTask(){
			public void run() {

					if(!countStop) {
						if(second >= 0) {
							if(playerFinish == true && computerFinish == true) {
								second = 15;
								gameRecord = gameRecord + "\n" + "Round " + roundCounter;
								gameLog.setText(gameRecord);
								Document d1 = gameLog.getDocument();
								gameLog.select(d1.getLength(), d1.getLength());
								roundCounter++;
								playerFinish = false;
								computerFinish = false;
							}
							if(!isHost && !isClient) {
								if(second == computerWait && computerFinish == false)
								{
									computerPlay();
									Random rg = new Random();
									computerWait = 1 + rg.nextInt(13);
								}
							}
							timeRemain.setText("Time - 0:"+second);
							if(second == 3) {
								gameRecord = gameRecord + "\n" + second +" seconds left in the round";
								gameLog.setText(gameRecord);
								Document d1 = gameLog.getDocument();
								gameLog.select(d1.getLength(), d1.getLength());
							}
							second--;
						}
						else {
							second = 15;
							gameRecord = gameRecord + "\n" + "Round " + roundCounter;
							gameLog.setText(gameRecord);
							Document d1 = gameLog.getDocument();
							gameLog.select(d1.getLength(), d1.getLength());
							roundCounter++;
							playerFinish = false;
							computerFinish = false;
						
							Random rg = new Random();
							computerWait = rg.nextInt(15);
						}			
				}
			}
		};
		time.schedule(task, 1000,1000);
	}
	
	public static int changeToNumber(char code) {
		int value = -1;
		if(code == 'A' || code == 'a') {
			value = 1;
		}
		else if(code == 'B' || code == 'b') {
			value = 2;
		}
		else if(code == 'C' || code == 'c') {
			value = 3;
		}
		else if(code == 'D' || code == 'd') {
			value = 4;
		}
		else if(code == 'E' || code == 'e') {
			value = 5;
		}
		else if(code == 'F' || code == 'f') {
			value = 6;
		}
		else if(code == 'G' || code == 'g') {
			value = 7;
		}
		else if(code == 'H' || code == 'h') {
			value = 8;
		}
		else if(code == 'I' || code == 'i') {
			value = 9;
		}
		else if(code == 'J' || code == 'j') {
			value = 10;
		}

		return value;
	}
	public static Vector<Player> playerSort(Vector<Player> player) {
		Vector<Player> temp = new Vector<Player>();
		Vector<Integer> temp1 = new Vector<Integer>();
		temp1 = ranking(player);
		
		for(int i = 0; i < temp1.size(); i++)  {
			for(int j = 0; j<player.size(); j++)  {
				if(temp1.get(i) == player.get(j).getScore()) {
					temp.add(player.get(j));
					player.remove(j);
					break;
				}
				else {
					continue;
				}
			}
		}
		return temp;
	}
	public static Vector<Integer> ranking(Vector<Player> player) {
		Vector<Integer> temp = new Vector<Integer>();
		for(int i = 0; i < player.size(); i++) {
			temp.add(player.get(i).getScore());
		}
		Collections.sort(temp);

		return temp;
		
	}

	public static Vector<Integer> fileChecking(char[][] coordi, char checking) {
		
		boolean[][] checked = new boolean[10][10];
		boolean first;
		boolean second = false;
		int forChecking = 0;
		Vector<Integer> storage = new Vector<Integer>();
		try {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					counter = 0;
					if(!checked[i][j]) {
						if(coordi[i][j] == checking) {
							
							checked[i][j] = true;
							counter++;
							first = true;
							BFS(first, second, checked, coordi, checking, i, j);
							second = true;
							forChecking = counter;
							storage.add(forChecking);
						}
					}
				}
			}		
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return storage;
		}
		return storage;
	}

	public static void BFS(boolean firstOne, boolean secondOne, boolean[][] checked, char[][] coordi, char checking, int i, int j) {
		int d = 0;
		//going to right
		if(firstOne) { 
			//needed to make sure that previous one is from the left.
				if( j!=9 &&coordi[i][j+1] == checking) {
					if(checked[i][j]) {
						if(!checked[i][j+1]) {
							if(checking != 'D' || counter != 2) {
								counter++;
								checked[i][j+1] = true;
								firstOne = false;
								BFS(firstOne, secondOne, checked, coordi, checking, i, j+1);
							}
							if(checking == 'D' && counter == 2 && secondOne) {
								coordi[i][j+1] = 'E'; 
								coordi[i][j] = 'E'; 
							}
	
						}
					}
				}
				else if(i!=9 && coordi[i+1][j] == checking) {
					if(checked[i][j]) {
						if(!checked[i+1][j]) {
							if(checking != 'D' || counter != 2) {
								counter++;
								checked[i+1][j] = true;
								firstOne = false;
								BFS(firstOne, secondOne, checked, coordi, checking, i+1, j);	
							}
							if(checking == 'D' && counter == 2 && secondOne) {
								coordi[i+1][j] = 'E'; 
								coordi[i][j] = 'E'; 
							}
						}	
					}		
				}
			}
			else {
				if(j!=9 && coordi[i][j+1] == checking) {//needed to make sure that previous one is from the left.
					if(checked[i][j-1]) {
						if(!checked[i][j+1]) {
							if(checking != 'D' || counter != 2) {
								counter++;
								checked[i][j+1] = true;
								firstOne = false;
								BFS(firstOne, secondOne, checked, coordi, checking, i, j+1);	
							}
							if(checking == 'D' && counter == 2 && secondOne) {
								coordi[i][j+1] = 'E'; 
								coordi[i][j] = 'E'; 
							}
						}
					}
				}
				else if(i!=9&&coordi[i+1][j] == checking) {
					if(checked[i-1][j]) {
						if(!checked[i+1][j]) {
							if(checking != 'D' || counter != 2) {
								counter++;
								checked[i+1][j] = true;
								firstOne = false;
								BFS(firstOne, secondOne, checked, coordi, checking, i+1, j);
							}
							if(checking == 'D' && counter == 2 && secondOne) {
								coordi[i+1][j] = 'E'; 
								coordi[i][j] = 'E'; 
							}
						}
					}
				}
		}
	}
	public static String getAlphabet(int x, int y) {
	
	String z = "";
	if(x == 0) {
		z ="A" + (y+1);
	}
	else if(x == 1) {
		z = "B" + (y+1);
	}
	else if(x == 2) {
		z = "C" + (y+1);
	}
	else if(x == 3) {
		z = "D" + (y+1);
	}
	else if(x == 4) {
		z = "E" + (y+1);
	}
	else if(x == 5) {
		z = "F" + (y+1);
	}
	else if(x == 6) {
		z = "G" + (y+1);
	}
	else if(x == 7) {
		z = "H" + (y+1);
	}
	else if(x == 8) {
		z = "I" + (y+1);
	}
	else if(x == 9) {
		z = "J" + (y+1);
	}
	
	return z;	
}

	public void placeShip(String ship, char direction, int x, int y) {
	
	try{
		if(coordi[x-1][y-1] == 'X') {
			if(direction == 'N') {
				switch(ship) {
					case "Aircraft Carrier":
						for(int i = 0; i <5; i++) {
							if(coordi[x-1-i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'A';
						coordi[x-2][y-1] = 'A';
						coordi[x-3][y-1] = 'A';
						coordi[x-4][y-1] = 'A';
						coordi[x-5][y-1] = 'A';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(A);
						((OceanJLabel) OJ[x-2][y-1]).setShip(A);
						((OceanJLabel) OJ[x-3][y-1]).setShip(A);
						((OceanJLabel) OJ[x-4][y-1]).setShip(A);
						((OceanJLabel) OJ[x-5][y-1]).setShip(A);
						
						deleteOption("Aircraft Carrier");
						break;
						
					case "Battleship":
						for(int i = 0; i <4; i++) {
							if(coordi[x-1-i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'B';
						coordi[x-2][y-1] = 'B';
						coordi[x-3][y-1] = 'B';
						coordi[x-4][y-1] = 'B';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(B);
						((OceanJLabel) OJ[x-2][y-1]).setShip(B);
						((OceanJLabel) OJ[x-3][y-1]).setShip(B);
						((OceanJLabel) OJ[x-4][y-1]).setShip(B);
				
						deleteOption("Battleship");
						break;
					case "Cruiser":
						for(int i = 0; i <3; i++) {
							if(coordi[x-1-i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'C';
						coordi[x-2][y-1] = 'C';
						coordi[x-3][y-1] = 'C';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(C);
						((OceanJLabel) OJ[x-2][y-1]).setShip(C);
						((OceanJLabel) OJ[x-3][y-1]).setShip(C);
		
						deleteOption("Cruiser");
						break;
					case "Destroyer":
						for(int i = 0; i < 2; i++) {
							if(coordi[x-1-i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}

						if(destroyerD == false) {
							coordi[x-1][y-1] = 'D';
							coordi[x-2][y-1] = 'D';
							destroyerD = true;
						}
						else {
							coordi[x-1][y-1] = 'E';
							coordi[x-2][y-1] = 'E';
						}

						((OceanJLabel) OJ[x-1][y-1]).setShip(D);
						((OceanJLabel) OJ[x-2][y-1]).setShip(D);
		
						destroyCounter--;
						if(destroyCounter == 0){
							deleteOption("Destroyer");
						}
						
						break;
				}
			}
			else if(direction == 'S') {
				switch(ship) {
					case "Aircraft Carrier":
						for(int i = 0; i <5; i++) {
							if(coordi[x-1+i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'A';
						coordi[x][y-1] = 'A';
						coordi[x+1][y-1] = 'A';
						coordi[x+2][y-1] = 'A';
						coordi[x+3][y-1] = 'A';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(A);
						((OceanJLabel) OJ[x][y-1]).setShip(A);
						((OceanJLabel) OJ[x+1][y-1]).setShip(A);
						((OceanJLabel) OJ[x+2][y-1]).setShip(A);
						((OceanJLabel) OJ[x+3][y-1]).setShip(A);
						
						deleteOption("Aircraft Carrier");
						
						break;
					case "Battleship":
						for(int i = 0; i <4; i++) {
							if(coordi[x-1+i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'B';
						coordi[x][y-1] = 'B';
						coordi[x+1][y-1] = 'B';
						coordi[x+2][y-1] = 'B';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(B);
						((OceanJLabel) OJ[x][y-1]).setShip(B);
						((OceanJLabel) OJ[x+1][y-1]).setShip(B);
						((OceanJLabel) OJ[x+2][y-1]).setShip(B);
						
						deleteOption("Battleship");
						break;
					case "Cruiser":
						for(int i = 0; i <3; i++) {
							if(coordi[x-1+i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'C';
						coordi[x][y-1] = 'C';
						coordi[x+1][y-1] = 'C';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(C);
						((OceanJLabel) OJ[x][y-1]).setShip(C);
						((OceanJLabel) OJ[x+1][y-1]).setShip(C);
					
						deleteOption("Cruiser");
		
						break;
					case "Destroyer":
						for(int i = 0; i <2; i++) {
							if(coordi[x-1+i][y-1] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}

						if(destroyerD == false) {
							coordi[x-1][y-1] = 'D';
							coordi[x][y-1] = 'D';
							destroyerD = true;
						}
						else {
							coordi[x-1][y-1] = 'E';
							coordi[x][y-1] = 'E';
						}

						((OceanJLabel) OJ[x-1][y-1]).setShip(D);
						((OceanJLabel) OJ[x][y-1]).setShip(D);
				
						destroyCounter--;
						if(destroyCounter == 0){
							deleteOption("Destroyer");
						}
						break;
				}
			}
			else if(direction == 'E') {
				switch(ship) {
					case "Aircraft Carrier":
						for(int i = 0; i <5; i++) {
							if(coordi[x-1][y-1+i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'A';
						coordi[x-1][y] = 'A';
						coordi[x-1][y+1] = 'A';
						coordi[x-1][y+2] = 'A';
						coordi[x-1][y+3] = 'A';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(A);
						((OceanJLabel) OJ[x-1][y]).setShip(A);
						((OceanJLabel) OJ[x-1][y+1]).setShip(A);
						((OceanJLabel) OJ[x-1][y+2]).setShip(A);
						((OceanJLabel) OJ[x-1][y+3]).setShip(A);
						
						deleteOption("Aircraft Carrier");
						break;
					case "Battleship":
						for(int i = 0; i <4; i++) {
							if(coordi[x-1][y-1+i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'B';
						coordi[x-1][y] = 'B';
						coordi[x-1][y+1] = 'B';
						coordi[x-1][y+2] = 'B';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(B);
						((OceanJLabel) OJ[x-1][y]).setShip(B);
						((OceanJLabel) OJ[x-1][y+1]).setShip(B);
						((OceanJLabel) OJ[x-1][y+2]).setShip(B);
			
						deleteOption("Battleship");
						break;
					case "Cruiser":
						for(int i = 0; i <3; i++) {
							if(coordi[x-1][y-1+i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'C';
						coordi[x-1][y] = 'C';
						coordi[x-1][y+1] = 'C';
	
						((OceanJLabel) OJ[x-1][y-1]).setShip(C);
						((OceanJLabel) OJ[x-1][y]).setShip(C);
						((OceanJLabel) OJ[x-1][y+1]).setShip(C);
			
						deleteOption("Cruiser");
						break;
					case "Destroyer":
						for(int i = 0; i <2; i++) {
							if(coordi[x-1][y-1+i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						if(destroyerD == false) {
							coordi[x-1][y-1] = 'D';
							coordi[x-1][y] = 'D';
							destroyerD = true;
						}
						else {
							coordi[x-1][y-1] = 'E';
							coordi[x-1][y] = 'E';
						}

						((OceanJLabel) OJ[x-1][y-1]).setShip(D);
						((OceanJLabel) OJ[x-1][y]).setShip(D);
						
						destroyCounter--;
						if(destroyCounter == 0){
							deleteOption("Destroyer");
						}
						break;
				}
			}
			else if(direction == 'W') {
				switch(ship) {
					case "Aircraft Carrier":
						for(int i = 0; i <5; i++) {
							if(coordi[x-1][y-1-i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'A';
						coordi[x-1][y-2] = 'A';
						coordi[x-1][y-3] = 'A';
						coordi[x-1][y-4] = 'A';
						coordi[x-1][y-5] = 'A';
							
						((OceanJLabel) OJ[x-1][y-1]).setShip(A);
						((OceanJLabel) OJ[x-1][y-2]).setShip(A);
						((OceanJLabel) OJ[x-1][y-3]).setShip(A);
						((OceanJLabel) OJ[x-1][y-4]).setShip(A);
						((OceanJLabel) OJ[x-1][y-5]).setShip(A);
						
						deleteOption("Aircraft Carrier");
						
						break;
					case "Battleship":
						for(int i = 0; i <4; i++) {
							if(coordi[x-1][y-1-i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'B';
						coordi[x-1][y-2] = 'B';
						coordi[x-1][y-3] = 'B';
						coordi[x-1][y-4] = 'B';
						
						((OceanJLabel) OJ[x-1][y-1]).setShip(B);
						((OceanJLabel) OJ[x-1][y-2]).setShip(B);
						((OceanJLabel) OJ[x-1][y-3]).setShip(B);
						((OceanJLabel) OJ[x-1][y-4]).setShip(B);
						
						deleteOption("Battleship");
						break;
					case "Cruiser":
						for(int i = 0; i <3; i++) {
							if(coordi[x-1][y-1-i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						coordi[x-1][y-1] = 'C';
						coordi[x-1][y-2] = 'C';
						coordi[x-1][y-3] = 'C';

						((OceanJLabel) OJ[x-1][y-1]).setShip(C);
						((OceanJLabel) OJ[x-1][y-2]).setShip(C);
						((OceanJLabel) OJ[x-1][y-3]).setShip(C);
						
						deleteOption("Cruiser");
						break;
					case "Destroyer":
						for(int i = 0; i <2; i++) {
							if(coordi[x-1][y-1-i] != 'X') {
								warning wn = new warning();
								wn.setVisible(true);
								return;
							}
						}
						if(destroyerD == false) {
							coordi[x-1][y-1] = 'D';
							coordi[x-1][y-2] = 'D';
							destroyerD = true;
						}
						else {
							coordi[x-1][y-1] = 'E';
							coordi[x-1][y-2] = 'E';
						}

						((OceanJLabel) OJ[x-1][y-1]).setShip(D);
						((OceanJLabel) OJ[x-1][y-2]).setShip(D);
					
						destroyCounter--;
						if(destroyCounter == 0){
							deleteOption("Destroyer");
						}
						break;
				}
			}
		}//if
		else {
			reset(x-1,y-1);
				
		}	
	}
	catch(ArrayIndexOutOfBoundsException e) {
		warning wn = new warning();
		wn.setVisible(true);
		if(ship.equalsIgnoreCase("Aircraft Carrier")) {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'A') {
						coordi[i][j] = 'X';
					}
				}
			}	
		}
		else if(ship.equalsIgnoreCase("Battleship")) {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'B') {
						coordi[i][j] = 'X';
					}
				}
			}
		}
		else if(ship.equalsIgnoreCase("Cruiser")) {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'C') {
						coordi[i][j] = 'X';
					}
				}
			}
		}
		else if(ship.equalsIgnoreCase("Destroyer")) {
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					if(coordi[i][j] == 'D') {
						coordi[i][j] = 'X';
					}
				}
			}
		}
	}

}
	public void deleteOption(String ship) {
	for(int i = 0; i < shipList.size(); i++) {
		if(shipList.get(i).equalsIgnoreCase(ship))
		{
			shipList.remove(i);
		}
	 }
	}
	public void shipAdd(String ship) {
		shipList.add(ship);
	}
	
	public static void computerCoordi(char[][] coordiC) {
	
	if(fileChecking(coordiC, 'A').size() != 1) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}
	else if(fileChecking(coordiC, 'A').get(0) != 5) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}
	if(fileChecking(coordiC, 'B').size() != 1) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}
	else if(fileChecking(coordiC, 'B').get(0) != 4) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}
	if(fileChecking(coordiC, 'C').size() != 1) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}
	else if(fileChecking(coordiC, 'C').get(0) != 3) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}
	Vector<Integer> errorchecking = fileChecking(coordiC, 'D');

	if(errorchecking.size() != 2) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}
	if(errorchecking.get(0) != 2 || errorchecking.get(1) != 2) {
		System.out.println("Your file is incorrect format. Check it again");
		System.exit(0);
	}

}
	
	public void startTimerGUI(boolean hostLogin, String IpAddress1, int port1) {
		JDialog jd = new JDialog();
		jd.setTitle("Wait for another user");
		jd.setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel timeText = new JLabel("Waiting for another player... "+ "30s until timeout");
		jd.add(timeText, BorderLayout.NORTH);
		jd.setVisible(true);

		if(hostLogin) {
			Thread td = new Thread(){
				public void run() {
					isHost = true;
					IpAddress = IpAddress1;
					port = port1;
					createConnection cc = new createConnection();
				}
			};
			td.start();
		} else if(!hostLogin) {
			Thread td = new Thread(){
				public void run() {
					isClient = true;
					IpAddress = IpAddress1;
					port = port1;
					createConnection cc = new createConnection();
				}
			};
			td.start();
			
		}
		
		time = new Timer();
		task = new TimerTask(){
			public void run() {
				String timeUpdate = String.valueOf(timeCounter);
				timeText.setText("Waiting for another player... "+ timeUpdate+"s until timeout");
				
				if(gameConnection) {
					System.out.println("Game connected");
					jd.setVisible(false);
					setVisible(true);
					time.cancel();
				}
				if(!hostLogin) {
					time.cancel();
					setVisible(true);
					jd.setVisible(false);
				}
				if(timeCounter == 0) {
					jd.setVisible(false);
					gameConnection = true;
					timeCounter = 30;
					time.cancel();
					timeText.setText("30");
					im.setVisible(true);
					stopServer();
				}
				timeCounter--;
			}
		};
		time.schedule(task, 1000, 1000);	
	}
	public void stopServer() {
		try {
			s.close();
			sso.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	class TimeSetting extends TimerTask
	{
		JLabel jl;
		int remaining;
		public TimeSetting(JLabel jl, int remaining) {
			this.jl = jl;
			this.remaining = remaining;
		}
		public void run() {
	
			while(true) {
				jl.setText("Time - 0:"+ remaining);
			}
		}
	}
	public void sendMessageToClients(ServerThread ct, String str) {
		System.out.println("asdasd");
		System.out.println("inside : "+str);
		for (ServerThread ct1 : ctVector) {
			if (!ct.equals(ct1)) {
				System.out.println("outside : "+str);
				ct1.sendMessage(str);
			}
		}
	}
	
	public class ServerThread extends Thread {
		private Socket s;
		private ObjectOutputStream OOS;
		private ObjectInputStream OIS;
		//private String text;
		public ServerThread(Socket s) {
			this.s = s;
			try {
				this.OIS = new ObjectInputStream(s.getInputStream());
				this.OOS = new ObjectOutputStream(s.getOutputStream());
				
			} catch (IOException ioe) {
				System.out.println("IOE in ChatThread constructor: " + ioe.getMessage());
			}
		}

		public void sendMessage(String str) {
			try {
				this.OOS.writeObject(str);
				this.OOS.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void run() {
			try {
				while (true) {
					//reading from the client 
					String line = (String) this.OIS.readObject();
					System.out.println(line);
					StringTokenizer tok = new StringTokenizer(line, ":");
					String command = tok.nextToken();
					System.out.println(command);
					if(command.equals("chat")) {
						String content = tok.nextToken();
						chattingRecord = chattingRecord + "\n" +otherPlayer+" : " + content;
						bothRecord = bothRecord + "\n" + chattingRecord;
						gameLog.setText(chattingRecord);
					}else if(command.equals("ship")) {
						String shipName = tok.nextToken();
						char shipDirection = tok.nextToken().charAt(0);
						int ix = Integer.parseInt(tok.nextToken()) - 1;
						int iy = Integer.parseInt(tok.nextToken()) - 1;
						System.out.println(ix + " , " + iy);
						if(shipName.equals("Aircraft Carrier")) {
							if(shipDirection == 'N') {
								for(int i = 0; i < 5; i++) {
									coordiC[ix-i][iy] = 'A';
								}
							}else if(shipDirection == 'S') {
								for(int i = 0; i < 5; i++) {
									coordiC[ix+i][iy] = 'A';
								}
							}else if(shipDirection == 'E') {
								for(int i = 0; i < 5; i++) {
									coordiC[ix][iy+i] = 'A';
								}
							}else if(shipDirection == 'W') {
								for(int i = 0; i < 5; i++) {
									coordiC[ix][iy-i] = 'A';
								}
							}
						}else if(shipName.equals("Battleship")) {
							if(shipDirection == 'N') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix-i][iy] = 'B';
								}
							}else if(shipDirection == 'S') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix+i][iy] = 'B';
								}
							}else if(shipDirection == 'E') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix][iy+i] = 'B';
								}
							}else if(shipDirection == 'W') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix][iy-i] = 'B';
								}
							}
						}else if(shipName.equals("Cruiser")) {
							if(shipDirection == 'N') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix-i][iy] = 'C';
								}
							}else if(shipDirection == 'S') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix+i][iy] = 'C';
								}
							}else if(shipDirection == 'E') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix][iy+i] = 'C';
								}
							}else if(shipDirection == 'W') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix][iy-i] = 'C';
								}
							}
						}else if(shipName.equals("Destroyer")) {
							if(!placeD) { //use D
								if(shipDirection == 'N') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix-i][iy] = 'D';
									}
								}else if(shipDirection == 'S') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix+i][iy] = 'D';
									}
								}else if(shipDirection == 'E') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix][iy+i] = 'D';
									}
								}else if(shipDirection == 'W') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix][iy-i] = 'D';
									}
								}
							} 
						}
					}else if(command.equals("other")) {
						int x = Integer.parseInt(tok.nextToken()) - 1;
						int y = Integer.parseInt(tok.nextToken()) - 1;
						OtherPlayer(x, y);
						
					}else if(command.equals("start")) {
						String content = tok.nextToken();
						oReady = "B";
					}
					
				}
			} catch (IOException | ClassNotFoundException ioe) {
				System.out.println(s.getInetAddress() + ":" + s.getPort() + " disconnected.");
				setVisible(false);
				System.out.println("IOE in ChatClient.run(): " + ioe.getMessage());
				System.out.println("lost with server");
				JDialog jd = new JDialog();
				jd.setTitle("lost Connection with other player");
				jd.setSize(250,250);
				jd.setLocation(250,250);
				jd.setAlwaysOnTop(true);
				jd.setModal(false);

				JLabel timeText = new JLabel("connection with other player");
				JButton Button = new JButton("OKAY");
				jd.add(timeText, BorderLayout.NORTH);
				jd.add(Button,  BorderLayout.SOUTH);
				jd.setVisible(true);
				setVisible(false);
				Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						im.setVisible(true);
						jd.setVisible(false);
						
					}
				});
				
				
				
			}
		}
	}
	
	public class Client extends Thread {

		public void run() {
			try {
				while (true) {
					//update gui
					String line = (String) OIS.readObject();
					System.out.println(line);
					StringTokenizer tok = new StringTokenizer(line, ":");
					String command = tok.nextToken();
					System.out.println(command);
					
					if(command.equals("chat")) {
						String content = tok.nextToken();
						chattingRecord = chattingRecord + "\n" +otherPlayer+" : " + content;
						bothRecord = bothRecord + "\n" + chattingRecord;
						gameLog.setText(chattingRecord);
					}else if(command.equals("ship")) {
						String shipName = tok.nextToken();
						char shipDirection = tok.nextToken().charAt(0);
						int ix = Integer.parseInt(tok.nextToken()) - 1;
						int iy = Integer.parseInt(tok.nextToken()) - 1;
						System.out.println(ix + " , " + iy);
						if(shipName.equals("Aircraft Carrier")) {
							System.out.println("asd");
							if(shipDirection == 'N') {
								System.out.println("111111111");
								for(int i = 0; i < 5; i++) {
									System.out.println("111111111");
									coordiC[ix-i][iy] = 'A';
								}
							}else if(shipDirection == 'S') {
								for(int i = 0; i < 5; i++) {
									coordiC[ix+i][iy] = 'A';
								}
							}else if(shipDirection == 'E') {
								for(int i = 0; i < 5; i++) {
									coordiC[ix][iy+i] = 'A';
								}
							}else if(shipDirection == 'W') {
								for(int i = 0; i < 5; i++) {
									coordiC[ix][iy-i] = 'A';
								}
							}
						}else if(shipName.equals("Battleship")) {
							if(shipDirection == 'N') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix-i][iy] = 'B';
								}
							}else if(shipDirection == 'S') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix+i][iy] = 'B';
								}
							}else if(shipDirection == 'E') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix][iy+i] = 'B';
								}
							}else if(shipDirection == 'W') {
								for(int i = 0; i < 4; i++) {
									coordiC[ix][iy-i] = 'B';
								}
							}
						}else if(shipName.equals("Cruiser")) {
							if(shipDirection == 'N') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix-i][iy] = 'C';
								}
							}else if(shipDirection == 'S') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix+i][iy] = 'C';
								}
							}else if(shipDirection == 'E') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix][iy+i] = 'C';
								}
							}else if(shipDirection == 'W') {
								for(int i = 0; i < 3; i++) {
									coordiC[ix][iy-i] = 'C';
								}
							}
						}else if(shipName.equals("Destroyer")) {
							if(!placeD) { //use D
								if(shipDirection == 'N') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix-i][iy] = 'D';
									}
								}else if(shipDirection == 'S') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix+i][iy] = 'D';
									}
								}else if(shipDirection == 'E') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix][iy+i] = 'D';
									}
								}else if(shipDirection == 'W') {
									for(int i = 0; i < 2; i++) {
										coordiC[ix][iy-i] = 'D';
									}
								}
							} 
						}
					}else if(command.equals("other")) {
						int x = Integer.parseInt(tok.nextToken()) - 1;
						int y = Integer.parseInt(tok.nextToken()) - 1;
						OtherPlayer(x, y);
					}else if(command.equals("start")) {
						String content = tok.nextToken();
						oReady = "B";
					}
				}
			} catch (IOException | ClassNotFoundException ioe) {
				setVisible(false);
				System.out.println("IOE in ChatClient.run(): " + ioe.getMessage());
				System.out.println("lost with server");
				JDialog jd = new JDialog();
				jd.setTitle("lost Connection with Server");
				jd.setSize(250,250);
				jd.setLocation(250,250);
				JLabel timeText = new JLabel("connection lost");
				JButton Button = new JButton("OKAY");
				jd.add(timeText, BorderLayout.NORTH);
				jd.add(Button,  BorderLayout.SOUTH);
				jd.setVisible(true);
				setVisible(false);
				Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						im.setVisible(true);
						jd.setVisible(false);
					}
				});
			}
		}
	}

	public class createConnection {
		public createConnection() {
			coordiC = new char[10][10];
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					coordiC[i][j] = 'X';
				}
			}
			if(isHost) {
				sso = null;
				try {
					sso = new ServerSocket(port);
					s = sso.accept();
					gameConnection = true;
					ct = new ServerThread(s);
					ctVector.add(ct);
					ct.start();
					
				} catch (IOException ie) {
					System.out.println("createConnection");
				} finally {
					if (sso != null) {
						try {
							sso.close();
						} catch (IOException ioe) {
							System.out.println("IOE closing ServerSocket: " + ioe.getMessage());
						}
					}
				}
			}else if(isClient) {
				boolean nostart = false;
				try {
					s = new Socket(IpAddress, port);
					OOS = new ObjectOutputStream(s.getOutputStream());
					OIS = new ObjectInputStream(s.getInputStream());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					setVisible(false);
					
					System.out.println("lost with server");
					JDialog jd = new JDialog();
					jd.setTitle("Error");
					jd.setSize(250,250);
					jd.setLocation(250,250);
					JLabel timeText = new JLabel("two players are already playing");
					JButton Button = new JButton("OKAY");
					jd.add(timeText, BorderLayout.NORTH);
					jd.add(Button,  BorderLayout.SOUTH);
					jd.setVisible(true);
					jd.setAlwaysOnTop(true);
					jd.setModal(false);
					nostart=true;
					Button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							im.setVisible(true);
							jd.setVisible(false);
							setVisible(false);

						}
					});
				}catch(ConnectException CE) {
				setVisible(false);
					
					System.out.println("lost with server");
					JDialog jd = new JDialog();
					jd.setTitle("Error");
					jd.setSize(300,300);
					jd.setLocation(200,200);
					JLabel timeText = new JLabel("two players are already playing");
					JButton Button = new JButton("OKAY");
					jd.add(timeText, BorderLayout.NORTH);
					jd.add(Button,  BorderLayout.SOUTH);
					jd.setVisible(true);
					jd.setAlwaysOnTop(true);
					jd.setModal(false);
					nostart=true;
					Button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							im.setVisible(true);
							jd.setVisible(false);
							setVisible(false);

						}
					});
					
				}catch (IOException e) {
					// TODO Auto-generated catch block
					setVisible(false);
					
					System.out.println("lost with server");
					JDialog jd = new JDialog();
					jd.setTitle("Error");
					jd.setSize(250,250);
					jd.setLocation(250,250);
					JLabel timeText = new JLabel("two players are already playing");
					JButton Button = new JButton("OKAY");
					jd.add(timeText, BorderLayout.NORTH);
					jd.add(Button,  BorderLayout.SOUTH);
					jd.setVisible(true);
					jd.setAlwaysOnTop(true);
					jd.setModal(false);
					nostart=true;
					Button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							im.setVisible(true);
							jd.setVisible(false);
							setVisible(false);

						}
					});
					e.printStackTrace();
				}
				if(!nostart)
				{
					Client client = new Client();
					client.start();
				}
			}
		}

	}
	public static void OtherPlayer(int x, int y) {
		System.out.println("otherPlay: "+x+":"+y);
	    String computerUpdate = " ";
	    turnCount++;

		if(coordi[x][y] == 'X')
		{
			if(computerSaver[x][y] == false)
			{
				((OceanJLabel) OJ[x][y]).missShip(miss);
				((OceanJLabel) OJ[x][y]).soundSplash(false);
				computerSaver[x][y] = true;
				gameRecord = gameRecord +"\n" + otherPlayer+ " hit " + computerUpdate + " and missed " + "(0:" +second +")";
				gameLog.setText(gameRecord);
				Document d = gameLog.getDocument();
				gameLog.select(d.getLength(), d.getLength());
			}
			
		}
		else if(coordi[x][y] == 'A')
		{
			if(computerSaver[x][y] == false)
			{
				CountaircraftC--;
				computerSaver[x][y] = true;
				if(CountaircraftC == 0)
				{

					((OceanJLabel) OJ[x][y]).sameTime(hit);
					((OceanJLabel) OJ[x][y]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'A') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit " + computerUpdate + " and hit aircraft " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ otherPlayer + " sunk " +currentPlayer+"'s aircraft";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else
				{
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit " + computerUpdate + " and hit aircraft " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
			
		}
		else if(coordi[x][y] == 'B')
		{
			if(computerSaver[x][y] == false)
			{
				CountbattlshipC--;
				computerSaver[x][y] = true;
				if(CountbattlshipC == 0)
				{
					((OceanJLabel) OJ[x][y]).sameTime(hit);
					((OceanJLabel) OJ[x][y]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'B') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit " + computerUpdate + " and hit battleship " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ otherPlayer + " sunk " +currentPlayer+"'s battleship";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else
				{
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit " + computerUpdate + " and hit battleship " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}

		}
		else if(coordi[x][y] == 'C')
		{
			if(computerSaver[x][y] == false)
			{
				CountcruiserC--;
				computerSaver[x][y] = true;
				if(CountcruiserC == 0)
				{
					((OceanJLabel) OJ[x][y]).sameTime(hit);
					((OceanJLabel) OJ[x][y]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'C') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit " + computerUpdate + " and hit cruiser " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ otherPlayer + " sunk " +currentPlayer+"'s cruiser";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else
				{
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit " + computerUpdate + " and hit cruiser " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}
			
		}
		else if(coordi[x][y] == 'D')
		{
			if(computerSaver[x][y] == false)
			{
				CountdestroyerC--;
				computerSaver[x][y] = true;
				if(CountdestroyerC == 0)
				{
					((OceanJLabel) OJ[x][y]).sameTime(hit);
					((OceanJLabel) OJ[x][y]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'D') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ otherPlayer + " sunk " +currentPlayer+"'s destroyer";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else
				{
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}

		}
		else if(coordi[x][y] == 'E')
		{
			if(computerSaver[x][y] == false)
			{
				CountdestroyerC1--;
				computerSaver[x][y] = true;
				if(CountdestroyerC1 == 0)
				{
					((OceanJLabel) OJ[x][y]).sameTime(hit);
					((OceanJLabel) OJ[x][y]).soundSinking();
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(coordi[i][j] == 'D') {
								if( !((x-1)==i && (y-1)==j) )
								{
									((OceanJLabel) OJ[i][j]).sinkingShip(hit);
									((OceanJLabel) OJ[i][j]).soundSplash(true);
								}
							}
						}
					}
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameRecord = gameRecord +"\n"+ otherPlayer + " sunk " +currentPlayer+"'s destroyer";
					gameLog.setText(gameRecord);
					Document d1 = gameLog.getDocument();
					gameLog.select(d1.getLength(), d1.getLength());
				}
				else
				{
					((OceanJLabel) OJ[x][y]).setBlowShip(hit);
					((OceanJLabel) OJ[x][y]).soundExplosion();
					
					gameRecord = gameRecord +"\n" + otherPlayer+ " hit "+ computerUpdate + " and hit destroyer " + "(0:" +second +")";
					gameLog.setText(gameRecord);
					Document d = gameLog.getDocument();
					gameLog.select(d.getLength(), d.getLength());
				}
			}

		}
		
		if(CountaircraftC==0&&CountbattlshipC==0&&CountcruiserC==0&&CountdestroyerC==0&&CountdestroyerC1==0){
			
			Thread th = new Thread() {
				public void run() {
					try {
						//countStop = true;
						time.cancel();
						sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//////////////
					
					GameOver go = new GameOver("Computer Won!");
					go.setVisible(true);
					go.getOK().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {

							A = new ImageIcon("A.png");
							B = new ImageIcon("B.png");
							C = new ImageIcon("C.png");
							D = new ImageIcon("D.png");
							miss = new ImageIcon("no.png");
							hit = new ImageIcon("fire.png");
							question  = new ImageIcon("question.png");
							//question.setImage("asd.sd");
							starter = false;
							Scanner input = new Scanner(System.in);

							coordi = new char [10][10]; // it is char 2D array size of 10
							for(int i = 0; i < 10; i++) {
								for(int j = 0; j < 10; j++) {
									coordi[i][j] = 'X';
								}
							}
							shipList = new Vector<String>();
							shipList.add("Select Ship");
							shipList.add("Aircraft Carrier");
							shipList.add("Battleship");
							shipList.add("Cruiser");
							shipList.add("Destroyer");
							
							Countdestroyer = 2;
							Countdestroyer1 = 2;
							Countaircraft = 5;
							Countcruiser = 4;
							Countbattlship = 3;
							second = 15;
							countStop = false;
							playerSaver = new boolean[10][10];
							computerSaver = new boolean[10][10];
							BattleShip bs = new BattleShip();
							bs.setVisible(true);
							//System.exit(0);
							

						}
					});
				}
			};
			
			th.start();	
			
	
		}
		computerFinish = true;
		//second = 15;
	    
	}
	
	
}//end of main class
