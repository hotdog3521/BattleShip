package taegyumk_CSCI201_Assignment5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class warning extends JFrame {

	public static final long serialVersionUID = 1;
	
	public warning() {
		super("Warning!!!");
		setSize(250, 120);
		setLocation(200,200);
		
		setLayout(new GridLayout(2,1));
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JLabel jl = new JLabel("Not Valid Placing Ship! Try again");
		JButton jb = new JButton("Confirm");
		
		jp1.add(jl);
		jp2.add(jb);
		
		
		add(jp1);
		add(jp2);
		
		
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
		        setVisible(false);
			}
		});
		
		
	}
	
	public static void main(String[] args) {
		warning wn = new warning();
		wn.setVisible(true);

	}

}
