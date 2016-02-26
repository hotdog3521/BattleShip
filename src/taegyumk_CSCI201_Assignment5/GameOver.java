package taegyumk_CSCI201_Assignment5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JFrame {
	public static final long serialVersionUID = 1;

	private JLabel winning;
	private JButton ok;
	private JButton no;
	private String message;
	
	public GameOver(String message) {
		super("Battleship");
		setSize(200, 100);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.message = message;
		//setLayout(new GridLayout(2,1));
		
		JPanel back = new JPanel();
		back.setLayout(new GridLayout(2,1));
		winning = new JLabel("You Won!");
		JPanel button = new JPanel();
		ok = new JButton("OK");
		no = new JButton("NO");
		button.add(ok);
		button.add(no);
		
		back.add(winning);
		back.add(button);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
	
				//System.exit(0);

			}
		});
		winning.setText(message);
		add(back);
		
	}
	
	public static void main(String [] args) {
		GameOver go = new GameOver("You Won!");
		go.setVisible(true);
	}
	public JButton getOK() {
		return ok;
	}
	public JButton getNO() {
		return no;
	}
}
