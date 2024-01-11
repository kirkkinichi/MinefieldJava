package br.com.kirk.cm.view;

import javax.swing.JFrame;

import br.com.kirk.cm.model.Board;

public class MainView extends JFrame{

    public MainView(){

        Board board = new Board(15, 15, 12);
        add(new BoardPanel(board));

        //Configuração da Tela
        setTitle("Minesweeper (Campo Minado)");
        setSize(768,530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainView();        
    }
}
