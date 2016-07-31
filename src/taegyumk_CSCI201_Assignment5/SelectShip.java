package taegyumk_CSCI201_Assignment5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelectShip extends JFrame {
	public static final long serialVersionUID = 1;
	
	private String coordination;
	private JComboBox<String> shipTypeSelection;
	
	private JRadioButton north;
	private JRadioButton south;
	private JRadioButton west;
	private JRadioButton east;
	
	private JLabel shipSelect;
	private String [] shipType;
	private JButton selectShip;
	
	private int column;
	private char row;
	private char shipDirection;
	
	private static Vector<String> shipList;
	private String chosenShip;
	
	private boolean buttonini = false;
	private boolean buttonini2 = false;
	
	private boolean icon = false;
	public SelectShip(String coordination, Vector<String> shipList) {
		super("Select ship at"+" "+coordination);

		this.coordination = coordination;
		setLocation(300, 300);
		setSize(350,150);
		setLayout(new GridLayout(4,1));
		
		JPanel top = new JPanel(); 

		shipTypeSelection = new JComboBox<String>(shipList);
	
		JPanel second = new JPanel();
		second.setLayout(new GridLayout(1,1));
		
		JPanel third =  new JPanel();
		third.setLayout(new GridLayout(1,1));
		shipSelect = new JLabel("Select Ship:");
		
		north = new JRadioButton("north");
		south = new JRadioButton("south");
		west = new JRadioButton("west");
		east = new JRadioButton("east");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(north);
		bg.add(south);
		bg.add(west);
		bg.add(east);
		
		selectShip = new JButton("Place Ship");
		selectShip.setEnabled(false);
		selectShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setShip();

			}
		});
		top.add(shipSelect);
		top.add(shipTypeSelection);
		second.add(north);
		second.add(south);
		third.add(east);
		third.add(west);

		add(top);
		add(second);
		add(third);
		add(selectShip);
		
		setShip();
	}
	public static void main (String [] args) {
		shipList = new Vector<String>();
		shipList.add("taegyum");
		shipList.add("winston");
		shipList.add("emma");
		shipList.add("josh");
		SelectShip ss = new SelectShip("taegyum", shipList);
		ss.setVisible(true);
		
	}

	public JComboBox<String> getSelection() {
		return shipTypeSelection;
	}
	public JButton getSelectionButton() {
		return selectShip;
	}
	public void setShip() {
		shipTypeSelection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if (ie.getStateChange() == ItemEvent.SELECTED) {
					chosenShip = (String)ie.getItem();
					if(chosenShip.equalsIgnoreCase("Select Ship")) {
						selectShip.setEnabled(false);
						buttonini2 = false;
					}
					else {
						buttonini2 = true;
						if(buttonini == true) {
							selectShip.setEnabled(true);
						}
						else {
							selectShip.setEnabled(false);
						}
					}
				}
			}
		});
		north.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				buttonini = true;
				if(buttonini2 == true) {
					selectShip.setEnabled(true);
				}
				else {
					selectShip.setEnabled(false);
				}
				if(north.isSelected()) {
					shipDirection = 'N';
					
				}
			}
		});
		south.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				buttonini = true;
				if(buttonini2 == true) {
					selectShip.setEnabled(true);
				}
				else {
					selectShip.setEnabled(false);
				}
				if(south.isSelected()) {
					shipDirection = 'S';
					
				}
			}
		});
		west.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				buttonini = true;
				if(buttonini2 == true) {
					selectShip.setEnabled(true);
				}
				else {
					selectShip.setEnabled(false);
				}
				if(west.isSelected()) {
					shipDirection = 'W';
					
				}
			}
		});
		east.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				buttonini = true;
				if(buttonini2 == true) {
					selectShip.setEnabled(true);
				}
				else {
					selectShip.setEnabled(false);
				}
				if(east.isSelected()) {
					shipDirection = 'E';
					
				}
			}
		});
	}
	public String getchosenShip() {
		return chosenShip;
	}
	public char getShipDirection() {
		return shipDirection;
	}
	public String getCoordination() {
		return coordination;
	}
	public JComboBox<String> getShipTypeSelection() {
		return shipTypeSelection;
	}
	
}
