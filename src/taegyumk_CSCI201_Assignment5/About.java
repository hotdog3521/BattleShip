package taegyumk_CSCI201_Assignment5;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JFrame {
	public static final long serialVersionUID = 1;

	public About() {
		super("About");
		setLocation(100, 100);
		setSize(400,200);
		setLayout(new BorderLayout());
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		ImageIcon me = new ImageIcon("me1.png");
		
		JLabel jl1 = new JLabel("Made By Taegyum Kim");
		JLabel jl2 = new JLabel(me);
		JLabel jl3 = new JLabel("CSCI201 USC:Assignment3");
		
		jp1.add(jl1);
		jp2.add(jl2);
		jp3.add(jl3);
		
		add(jp1, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		About a = new About();
		a.setVisible(true);

	}

}
