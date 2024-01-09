package br.com.kirk.cm;

import br.com.kirk.cm.model.Board;

public class App{

    public static void main(String[] args) {
        Board board = new Board(3, 3, 9);

        board.openField(1, 1);
    }
}
