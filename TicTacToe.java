//TicTacToe Class
package com.pns.projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TicTacToe extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Random rand = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JPanel promo_panel = new JPanel();
	JButton[] buttons = new JButton[9];
	JButton reset_button = new JButton("Reset");
	boolean player_1_turn ,not_drawn;
	JLabel textfield = new JLabel();

	TicTacToe() {
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Tic-Tac-Toe by Priyabrata Nayak");
		
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		textfield.setBounds(0, 0, 650, 100);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		promo_panel.setLayout(new BorderLayout());
		promo_panel.setBounds(750,0,800,50);
		promo_panel.add(reset_button);
		reset_button.setFont(new Font("Serif",Font.BOLD,30));
		reset_button.addActionListener(this);
		reset_button.setBounds(0,0,800,50);
		reset_button.setBackground(new Color(255,50,50));
		reset_button.setForeground(Color.BLACK);
		reset_button.setBorder(null);
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(Color.cyan);
		for(int i=0;i<9;i++) {
			buttons[i]=new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		frame.add(promo_panel,BorderLayout.SOUTH);
		
		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==reset_button) {
			new TicTacToe();
			return;
		}
		for(int i=0;i<9;i++) {
			drawCheck();
			if(e.getSource()==buttons[i]) {
				if(player_1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setText("X");
						buttons[i].setForeground(new Color(0,255,0));
						player_1_turn = false;
						textfield.setText("O's turn");
						check();
					}
				}
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setText("O");
						buttons[i].setForeground(new Color(255,0,0));
						player_1_turn = true;
						textfield.setText("X's turn");
						check();
					}
				}
			}
		}
	}
	//to check all the possible outcomes for a player to win
	public void check() {
		if(buttons[0].getText()==buttons[1].getText() && buttons[0].getText()==buttons[2].getText()) {
			getWinner(0,1,2,buttons[0].getText());return;
		}
		if(buttons[0].getText()==buttons[4].getText() && buttons[0].getText()==buttons[8].getText()) {
			getWinner(0,4,8,buttons[0].getText());return;
		}
		if(buttons[0].getText()==buttons[3].getText() && buttons[0].getText()==buttons[6].getText()) {
			getWinner(0,3,6,buttons[0].getText());return;
		}
		if(buttons[1].getText()==buttons[4].getText() && buttons[1].getText()==buttons[7].getText()) {
			getWinner(1,4,7,buttons[1].getText());return;
		}
		if(buttons[2].getText()==buttons[5].getText() && buttons[2].getText()==buttons[8].getText()) {
			getWinner(2,5,8,buttons[2].getText());return;
		}
		if(buttons[2].getText()==buttons[4].getText() && buttons[2].getText()==buttons[6].getText()) {
			getWinner(2,4,6,buttons[2].getText());return;
		}
		if(buttons[3].getText()==buttons[4].getText() && buttons[3].getText()==buttons[5].getText()) {
			getWinner(3,4,5,buttons[3].getText());return;
		}
		if(buttons[6].getText()==buttons[7].getText() && buttons[6].getText()==buttons[8].getText()) {
			getWinner(6,7,8,buttons[6].getText());return;
		}
	}
	public void getWinner(int a,int b,int c,String winner) {
		if(!winner.equals("")) {//to ensure that the null buttons are not being compared
		if(winner=="X") {
			for(int i=0;i<9;i++) {
				buttons[i].setEnabled(false);
			}
			buttons[a].setBackground(new Color(0,255,0));
			buttons[b].setBackground(new Color(0,255,0));
			buttons[c].setBackground(new Color(0,255,0));
		}
		else if(winner=="O"){
			for(int i=0;i<9;i++) {
				buttons[i].setEnabled(false);
			}
			buttons[a].setBackground(new Color(0,255,0));
			buttons[b].setBackground(new Color(0,255,0));
			buttons[c].setBackground(new Color(0,255,0));
		}
		textfield.setText("Game over! "+winner+" wins");
		}
	}

	public void drawCheck() {
		for(int i=0;i<9;i++) {
			if(buttons[i].getText()=="") {
				not_drawn=true;
				return;
			}
//			System.out.println(buttons[i].getText());
		}
		if(not_drawn) {
			textfield.setBackground(Color.YELLOW);
			textfield.setForeground(Color.RED);
			textfield.setText("Match Drawn");
		}
	}

	public void firstTurn() {
		//the first turn is decided after 1 seconds(1000 miliseconds)
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(rand.nextInt(2)==0) {
			player_1_turn = true;
			textfield.setText("X's turn");
		}
		else {
			player_1_turn = false;
			textfield.setText("O's turn");
		}
	}
}


//Main Class

 class MainClass {

	public static void main(String[] args) {
		new TicTacToe();
	}

}