package br.com.kirk.cm.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.kirk.cm.exception.ExplosionException;
import br.com.kirk.cm.exception.SairException;
import br.com.kirk.cm.model.Board;

public class BoardView {
    private Board board;
	private Scanner entrada = new Scanner(System.in);
	
	public BoardView(Board board) {
		this.board = board;
		
		runGame();
	}
	
    //Executa o jogo
	private void runGame() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				gameCycle();
				
				System.out.println("New match? (s/n) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) { //ignora letra maiuscula / minuscula
					continuar = false;
				} else {
					board.restart();
				}
			}
		} catch(SairException e) {
			System.out.println("Bye!!");
		} finally {
			entrada.close();
		}
	}

	//Recebe valor digitado pelo usuário
	private String getUserInput(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}

    //Gera ciclo no jogo
	private void gameCycle() {
		try {
			while(!board.goalAchieved()) {
				System.out.println(board);
				
				String digitado = getUserInput("Digite (x,y): ");
									
				Iterator<Integer> rc = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = getUserInput("1 - Open Field ou 2 - Mark Field: ");
				
				if("1".equals(digitado)) {
					board.openField(rc.next(), rc.next());
				} else if("2".equals(digitado)) {
					board.changeMark(rc.next(), rc.next());
				}
				
			}			
			System.out.println(board);
			System.out.println("Win! ");			
		} 
		catch (ExplosionException e) {
			System.out.println(board);
			System.out.println("Lose! ");
		}	
	}
}
