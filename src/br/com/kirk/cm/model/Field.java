package br.com.kirk.cm.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
    
    private boolean mined = false;
	private boolean open = false;
	private boolean marked = false; 

    private final int row;
	private final int column;
	
	private List<Field> neighbors = new ArrayList<>();
	private List<FieldObserver> observers = new ArrayList<>();
		
	Field(int row, int column) {
		this.row = row;
		this.column = column;
	}

	//Registra Observador
	public void registerObserver(FieldObserver observer){
		observers.add(observer);
	}

	//Notificar que evento aconteceu
	public void notifyObservers(FieldEvent event){
		observers.stream().forEach(o -> o.eventOccurred(this, event));
	}

    //Adiciona Vizinhos
    boolean addNeighbors(Field neighbor) {
        
        boolean difRow = column != neighbor.column;
		boolean difColumn = column != neighbor.column;
		boolean diagonal = difRow && difColumn;
		
		int deltaRow = Math.abs(row - neighbor.row);
		int deltaColumn = Math.abs(column - neighbor.column);
		int delta = deltaColumn + deltaRow;
		
		if(delta == 1 && !diagonal) {
			neighbors.add(neighbor);
			return true;            
		} 
        else if (delta == 2 && diagonal){
			neighbors.add(neighbor);
			return true;
		} 
        else {
			return false;
		}
    }

    //Reinicia o Campo Minado
    void restart() {
		marked = false;
		mined = false;
		open = false;
	}   

    //Altera marcação no Campo
    void changeMark() {
		if(!open) {
			marked = !marked;

			if (marked) {
				notifyObservers(FieldEvent.MARK);
			} else {
				notifyObservers(FieldEvent.MARKOFF);
			}
		}
	}
	
    //Abre campo selecionado
	boolean openField() {
		
		if(!open && !marked) {
			
			if(mined) {
				notifyObservers(FieldEvent.EXPLODE);
				return true;
			}
			
			setOpened(true);

			if(chekNeighboursSecurity()) {
				neighbors.forEach(v -> v.openField());
			}
			
			return true;
		} else {
			return false;
		}		
	}

    //Verifica a quantidades de vizinhos de um campo
    long countNeighborsMine() {
		return neighbors.stream().filter(v -> v.mined).count();
	}
	
    //Verifica a proximidade de minas
	boolean chekNeighboursSecurity () {
		return neighbors.stream().noneMatch(v -> v.mined);
	}	
    
	//Verifica se o campo está marcado
	public boolean isMarked() {
		return marked;
	}
	
    //Mina um campo
	void mine() {		
		mined = true;		
	}	
	
    //Retorna mina ativa
	public boolean isMined() {
		return mined;
	}
	
    //Retorna campo revelado
	public boolean isOpened() {
		return open;
	}
	
    //Retorna campo fechado
	public boolean isClosed() {
		return !isOpened();
	}

    //Getters e Setters    
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

    void setOpened(boolean open) {
		this.open = open;

		if (open) {
			notifyObservers(FieldEvent.OPEN);
		}
	}
	
    //Retorna objetivo
	boolean goalAchieved() {
		boolean desvendado = !mined && open;
		boolean protegido = mined && marked;
		
		return desvendado || protegido;		
	}    
}
