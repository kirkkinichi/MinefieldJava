package br.com.kirk.cm.view;

import javax.swing.JFrame;

import br.com.kirk.cm.model.Board;

public class MainView extends JFrame{

    public MainView(){

        Board board = new Board(8, 8, 9);
        add(new BoardPanel(board));

        setTitle("Minesweeper (Campo Minado)");
        setSize(690,438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainView();        
    }
}
