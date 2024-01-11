package br.com.kirk.cm.model;

public class ResultEvent {
    
    private final boolean won;

    public ResultEvent(boolean won) {
        this.won = won;
    }

    public boolean isWon(){
        return won;
    }
}
