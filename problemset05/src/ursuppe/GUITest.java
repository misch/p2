package ursuppe;

import java.awt.*;

import javax.swing.*;

public class GUITest extends JFrame {
		public static void main(String args[]) {
			new GUITest();
		}
		GUITest() {
			JLabel jlbHelloWorld = new JLabel("Hello World", SwingConstants.CENTER);
			JButton b = new JButton("Push me");
			b.setBounds(50, 20, 100, 150);
			b.setSize(100, 50);
			this.setSize(600, 400);
			
			GridLayout grid=new GridLayout(5,5);
			JPanel p = new JPanel(grid);
			p.add(new JButton("Button 1"));
	        p.add(new JButton("Button 2"));
	        p.add(new JButton("Button 3"));
	        p.add(new JButton("Long-Named Button 4"));
	        p.add(new JButton("5"));
			this.setLayout(new BorderLayout());
			
			p.add(b);
			p.add(jlbHelloWorld);
			add(p, BorderLayout.CENTER);
			this.add(jlbHelloWorld, BorderLayout.NORTH);
			pack();
			setVisible(true);
		}
	}
