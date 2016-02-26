package taegyumk_CSCI201_Assignment5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IpMenu extends JFrame{
	public static final long serialVersionUID = 1;
	
	private static JPanel P1;
	private static JPanel P2;
	private static JPanel P3;
	private static JPanel P4;
	private static JPanel P5;
	private static JPanel P6;
	
	//first
	private static JLabel yourIP;
	private static JLabel IpAddress;
	//second
	private static JLabel name;
	private static JTextField inputName;
	//third
	private static JCheckBox hostCBox;
	private static JLabel EnterIP;
	private static JTextField inputIP;
	//forth
	private static JCheckBox portCBox;
	private static JLabel portLabel;
	private static JTextField inputPort;
	//fifth
	private static JCheckBox mapCheck;
	private static JTextField mapField;
	//sixth
	private static JButton refreshButton;
	private static JButton connectButton;

	
	private static String ip;
	private static boolean connection_true = false;
	private static BattleShip bs;
	//timer gui
	
	private static JPanel timeWindow;
	private static JLabel timeText;
	private static int timeCounter = 30;
	private static Timer time;
	private static TimerTask task;
	
	private static String mapGetText;
	private static String hostGetText;
	private static String portGetText;
	
	private static char[][] playMap;
	
	
	public IpMenu(BattleShip bs, String ip) {
		super("Battleship Menu");
		setSize(450, 350);
		setLocation(100,100);
		setLayout(new GridLayout(6,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.bs = bs;
		this.ip = ip;
		playMap = new char[10][10];
		
		P1 = new JPanel();
		P2 = new JPanel();
		P3 = new JPanel();
		P4 = new JPanel();
		P5 = new JPanel();
		P6 = new JPanel();
		
		//first
		yourIP = new JLabel("Your IP:");
		IpAddress = new JLabel(ip);
		P1.add(yourIP);
		P1.add(IpAddress);
		//second
		name = new JLabel("Name:");
		inputName = new JTextField("Player1");
		inputName.setColumns(8);
		P2.add(name);
		P2.add(inputName);
		//third
		hostCBox = new JCheckBox("Host Game");
		EnterIP = new JLabel("Enter an IP");
		inputIP = new JTextField("localhost");
		inputIP.setEnabled(true);
		inputIP.setColumns(8);
		P3.add(hostCBox);
		P3.add(EnterIP);
		P3.add(inputIP);
		//forth
		portCBox = new JCheckBox("Custon Port");
		portLabel = new JLabel("Port:");
		inputPort = new JTextField("1234");
		inputPort.setEnabled(true);
		inputPort.setColumns(8);
		P4.add(portCBox);
		P4.add(portLabel);
		P4.add(inputPort);
		//fifth
		mapCheck = new JCheckBox("201 Map");
		mapField = new JTextField();
		mapField.setColumns(8);
		mapField.setEnabled(false);
		P5.add(mapCheck);
		P5.add(mapField);
		//sixth
		refreshButton = new JButton("Refresh");
		connectButton = new JButton("Connect");
		P6.add(refreshButton);
		P6.add(connectButton);
		
		//checkbox handler
		ButtonGroup bg = new ButtonGroup();
		bg.add(hostCBox);
		bg.add(portCBox);
		bg.add(mapCheck);
		
		//when each check are clicked!
		hostCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				inputIP.setEnabled(false);
				inputPort.setEnabled(true);
				mapField.setEnabled(false);

			}
		});
		
		portCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				inputIP.setEnabled(true);
				inputPort.setEnabled(true);
				mapField.setEnabled(false);
			}
		});
		
		mapCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				inputIP.setEnabled(false);
				inputPort.setEnabled(false);
				mapField.setEnabled(true);

			}
		});
		//buttons; refresh and connect
		//refresh
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				inputName.setText("Player1");
				inputIP.setText("localhost");
				inputPort.setText("1234");
				mapField.setText("");
				
				hostCBox.setSelected(false);
				portCBox.setSelected(false);
				mapCheck.setSelected(false);
				if(!isConnected()) {
					IpAddress.setText("Error");
				} else {
					BufferedReader in;
					try {
						URL toCheckIp = new URL("http://checkip.amazonaws.com");
						in = new BufferedReader(new InputStreamReader(toCheckIp.openStream()));
						String ip = in.readLine();
						IpAddress.setText(ip);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		
		//connect
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if(hostCBox.isSelected()) {
					System.out.println("hostCBox");
					hostGetText = inputIP.getText();
					portGetText = inputPort.getText();
					setVisible(false);
					bs.setMyName(inputName.getText());
					bs.startTimerGUI(true, hostGetText, Integer.parseInt(portGetText)); 
				}else if(portCBox.isSelected()||(!hostCBox.isSelected()&&!portCBox.isSelected()&&!mapCheck.isSelected())) {
					System.out.println("portCBox");
					hostGetText = inputIP.getText();
					portGetText = inputPort.getText();
					setVisible(false);
					bs.setMyName(inputName.getText());
					bs.startTimerGUI(false, hostGetText, Integer.parseInt(portGetText)); 
				}else if(mapCheck.isSelected()) {
					System.out.println("mapCheck");
					mapGetText = mapField.getText();
					try{
						
						URL toCheckIp = new URL("http://www-scf.usc.edu/~csci201/assignments/"+mapGetText+".battle");
						BufferedReader in = new BufferedReader(new InputStreamReader(toCheckIp.openStream()));
						for(int i = 0; i < 10; i++) {
							String ip = in.readLine();
							for(int j = 0; j < 10; j++) {
								playMap[i][j] = ip.charAt(j);
								System.out.print(playMap[i][j]);
							}
							System.out.println("");
						}
						for(int i = 0; i < 10; i++) {
							for(int j = 0; j <10; j++) {
								//System.out.println(playMap[i][j]);
							}
						}
						
					}catch(IOException ioe){
						
						System.out.println("IOE");
					}
					bs.computerWithComputer(playMap);
					bs.showToUser();
				}
				
				
				//bs.showToUser();
				connection_true = true;

			}
		});
		
		add(P1);
		add(P2);
		add(P3);
		add(P4);
		add(P5);
		add(P6);
		System.out.println("huhu");
		setVisible(true);
	}
	public JButton getConnectButton() {
		return connectButton;
	}
	public boolean connection() {
		return connection_true;
	}
	public boolean isConnected() {
		Socket sock = new Socket();
		InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
		
		try{
			sock.connect(address,300);
			return true;
		}
		catch(IOException e) {
			return false;
		}
		finally {
			try{sock.close();}
			catch(IOException e) {
			
			}
		}
		
	}
	
	public static void main(String[] args) {
		new IpMenu(bs, ip);
		
		BufferedReader in;
		try {
			URL toCheckIp = new URL("http://checkip.amazonaws.com");
			in = new BufferedReader(new InputStreamReader(toCheckIp.openStream()));
			ip = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ip);
		IpAddress.setText(ip);
		

	}
	

}

