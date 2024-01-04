package br.com.kirk.cm.model;

import java.util.ArrayList;
import java.util.List;

import br.com.kirk.cm.exception.ExplosionException;

public class Field {
    
    private boolean mined = false;
	private boolean open = false;
	private boolean marked = false; 

    private final int row;
	private final int column;
	
	private List<Field> neighbors = new ArrayList<>();
		
	Field(int row, int column) {
		this.row = row;
		this.column = column;
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
    void restartField () {
		marked = false;
		mined = false;
		open = false;
	}   

    //Altera marcação no Campo
    void changeMark() {
		if(!open) {
			marked = !marked;
		}
	}
	
    //Abre campo selecionado
	boolean openField() {
		
		if(!open && !marked) {
			open = true;
			
			if(mined) {
				throw new ExplosionException();
			}
			
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

}
