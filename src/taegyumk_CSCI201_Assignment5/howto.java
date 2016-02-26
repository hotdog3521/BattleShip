package taegyumk_CSCI201_Assignment5;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class howto extends JFrame {
	public static final long serialVersionUID = 1;

	public JTextArea jt;
	
	public howto(String str) {
		super("How TO");
		setLocation(100, 100);
		setSize(400,200);
	
		jt = new JTextArea();
		jt.setText(str);
		jt.setLineWrap(true);
		add(new JScrollPane(jt));
	}

	public static void main(String[] args) {

		howto hw = new howto("What");
		hw.setVisible(true);
	

	}
	public JTextArea getArea() {
		return jt;
	}

}
