package br.com.kirk.cm.view;

import java.util.Scanner;
import br.com.kirk.cm.model.Board;

public class BoardView {
    private Board board;
	private Scanner entrada = new Scanner(System.in);
	
	public BoardView(Board board) {
		this.board = board;
		
		//runGame();
	}
	
    //Executa o jogo
	private void runGame() {
		//TODO Implementar função de execução do jogo
	}

    //Gera ciclo no jogo
	private void gameCycle() {
		//TODO Implementar função de ciclo no jogo
	}
}
