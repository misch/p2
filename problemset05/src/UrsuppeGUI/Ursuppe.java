package UrsuppeGUI;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.Queue;

import javax.swing.*;

import org.junit.Test;

import ursuppe.Board;
import ursuppe.GUIOutput;
import ursuppe.Game;
import ursuppe.Output;
import ursuppe.Player;

/** This class is responsible to set up a Graphical User Interface
 * for the Ursuppe game. */

public class Ursuppe extends JFrame {

	private Game game = new Game();
	private JTextArea displayBoard;

	public Ursuppe() {
		setLayout(new BorderLayout());
		
		displayBoard = new JTextArea(game.toString());
		displayBoard.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		
		JScrollPane scrollPane = new JScrollPane(displayBoard);
		
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(scrollPane);
		
		this.add (setButtons()[0], BorderLayout.PAGE_START);
		this.add (setButtons()[1], BorderLayout.PAGE_END);
		this.add(panel, BorderLayout.CENTER);

		setTitle("Ursuppe!");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Output.setOutput(new GUIOutput(displayBoard));

	}

	private JButton[] setButtons() {
		JButton[] buttons= new JButton[2];
		
		JButton newGame = new JButton("New Game");

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				initNewGame();
			}
		});
		
		JButton close = new JButton("exit");
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		
		buttons[0] = newGame;
		buttons[1] = close;
		
		return buttons;
		
		
	}

	public void initNewGame() {
		game = new Game();
		game.play();
	}

	public static void main(String[] args) {
		Ursuppe ursuppe = new Ursuppe();
		ursuppe.setVisible(true);
	}
}
