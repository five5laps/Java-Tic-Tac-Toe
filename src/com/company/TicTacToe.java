package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    boolean isGame = true;

    //constructor
    TicTacToe(){
        //frame set up
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));

        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setFont(new Font(null,Font.BOLD,75));
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800, 100);

        //3x3
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i=0;i<9;i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font(null, Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        frame.setResizable(false);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        checkWin();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textField.setText("X turn");
                        checkWin();
                    }
                }
            }
        }
    }
    public void firstTurn() {
        if(random.nextInt(2) == 0){
            player1_turn = true;
            textField.setText("X turn");
        }
        else{
            player1_turn = false;
            textField.setText("O turn");
        }
    }
    //and maybe add unique solution for extending grid
    public void checkWin() {

        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical
                {0, 4, 8}, {2, 4, 6} // diagonal
        };
        String[] players = {"X", "O"};

        for (String player : players) {
            for (int[] condition : winConditions) {
                if (    buttons[condition[0]].getText().equals(player) &&
                        buttons[condition[1]].getText().equals(player) &&
                        buttons[condition[2]].getText().equals(player))
                {
                    if (player.equals("X")) {
                        xWins(condition[0], condition[1], condition[2]);
                        isGame = false;
                    }
                    if (player.equals("O")){
                        oWins(condition[0], condition[1], condition[2]);
                        isGame = false;
                    }
                }
            }
        }
        int i = 0;
        if(isGame) {
            while (!buttons[i].getText().equals("")) {
                if (i == buttons.length - 1) {
                    draw();
                    break;
                }
                i++;
            }
        }
    }


    public void draw(){
        for(int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("DRAW!");
    }


    public void xWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }

        textField.setText("X wins");
    }

    public void oWins(int a,int b,int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("O wins");
    }

}
