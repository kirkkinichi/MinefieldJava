package br.com.kirk.cm;

import br.com.kirk.cm.model.Board;
import br.com.kirk.cm.view.BoardView;

public class App{

    public static void main(String[] args) {
        Board board = new Board(8, 8, 5);

        new BoardView(board);
    }
}
