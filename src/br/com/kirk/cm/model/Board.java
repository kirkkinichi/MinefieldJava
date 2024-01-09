package br.com.kirk.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Board implements FieldObserver{

    private int row;
	private int columns;
	private int mines;
	
	private final List<Field> fields = new ArrayList<>();
	private final List<Consumer<Boolean>> observers = new ArrayList<>();

	public Board(int row, int columns, int mines) {
		this.row = row;
		this.columns = columns;
		this.mines = mines;
		
		generateFields();
		associateNeighbors();
		sortMines();
	}	

	public void observerRegister(Consumer<Boolean> observer) {
		observers.add(observer);
	}

	//Notificar que evento aconteceu
	public void notifyObservers(boolean result){
		observers.stream().forEach(o -> o.accept(result));
	}

	//Mostrar campos que possuem minas
	private void showMines() {
		fields.stream().filter(c -> c.isMined()).forEach(c -> c.setOpened(true));
	}
	
    //Abre o campo
	public void openField(int row, int column) {		
		fields.parallelStream().filter(c->c.getRow() == row && c.getColumn() == column).findFirst().ifPresent(c -> c.openField());
	}
	
    //Muda a marcação do campo
	public void changeMark(int row, int column) {
		fields.parallelStream().filter(c->c.getRow() == row && c.getColumn() == column).findFirst().ifPresent(c -> c.changeMark());;
	}
	
    //Gera as linhas e colunas do campo
	private void generateFields () {
		for(int l = 0; l < row; l++) {
			for(int c = 0; c < columns; c++) {				
				Field field = new Field(l, c);
				field.registerObserver(this);
				fields.add(field);
			}
		}
	}
	
    //Associa os campos vizinhos
	private void associateNeighbors() {
		for(Field c1: fields) {
			for (Field c2: fields) {
				c1.addNeighbors(c2);
			}
		}
	}
	
    //Distribuí aleatoriamente as minas no tabuleiro
	private void sortMines() {
		long minasArmadas = 0;
		Predicate<Field> minado = c -> c.isMined();
		
		do {			
			int aleatorio = (int) (Math.random() * fields.size());
			fields.get(aleatorio).mine();
			minasArmadas = fields.stream().filter(minado).count();
		} while(minasArmadas < mines);
	}
	
    //Condição de objetivo alcançado
	public boolean goalAchieved() {
		return fields.stream().allMatch(c -> c.goalAchieved());	
	}
	
    //Reinicia o jogo e sorteia as minas pelo tabuleiro
	public void restart( ) {
		fields.stream().forEach(c -> c.restart());
		sortMines();
	}		

	@Override
	public void eventOccurred(Field field, FieldEvent event) {
		if (event == FieldEvent.EXPLODE) {
			showMines();
			System.out.println("Lose!");
			notifyObservers(false);
		}
		else if (goalAchieved()) {
			System.out.println("Win!");
			notifyObservers(true);
		}
	}
}
