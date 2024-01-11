package br.com.kirk.cm.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.kirk.cm.model.Field;
import br.com.kirk.cm.model.FieldEvent;
import br.com.kirk.cm.model.FieldObserver;

public class FieldButton extends JButton implements FieldObserver, MouseListener{


    private final Color BG_DEFAULT = new Color(167,217,72);
    private final Color BG_DEFAULT2 = new Color(142,204,57);

    private Field field;


    public FieldButton(Field field) {
        this.field = field;
        
        if ((field.getRow() + field.getColumn()) % 2 == 0) {
            setBackground(BG_DEFAULT);
        } else {
            setBackground(BG_DEFAULT2);
        }	
        setOpaque(true);  
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        field.registerObserver(this);
    }

    @Override
    public void eventOccurred(Field field, FieldEvent event){
        switch (event) {
            case OPEN:
                applyOpenStyle();
                break;
            case EXPLODE:
                applyExplodeStyle();
                break;
            case MARK:
                applyMarkStyle();
                break;
            case MARKOFF:
                applyMarkOffStyle();
                break;
            default:
                applyDefaultStyle();
        }
    }    

    private void applyOpenStyle() {

        if (field.isMined()) {
            setBackground(Color.RED);
            return;
        }

        setBackground(Color.WHITE);

        switch (field.countNeighborsMine()) {
            case 1:
                setForeground(Color.BLUE);
                break;
            case 2:
                setForeground(Color.GREEN);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4:
                setForeground(Color.ORANGE);
                break;
            case 5:
                setForeground(Color.RED);
                break;
            case 6:
                setForeground(Color.PINK);
                break;
            default:
                setForeground(Color.BLACK);
                break;
        }

        String value = !field.checkNeighboursSecurity() ? field.countNeighborsMine() + "" : "";
        setText(value);
    }

    private void applyExplodeStyle() {
        setBackground(Color.RED);
        setText("");
    }

    private void applyMarkStyle() {
        setBackground(Color.CYAN);
        setText("X");
    }    

    private void applyMarkOffStyle() {
        if ((field.getRow() + field.getColumn()) % 2 == 0) {
            setBackground(BG_DEFAULT);
        }
        else {
            setBackground(BG_DEFAULT2);
        }        
        setText("");
    }

    private void applyDefaultStyle() {
        if ((field.getRow() + field.getColumn()) % 2 == 0) {
            setBackground(BG_DEFAULT);
        } else {
            setBackground(BG_DEFAULT2);
        }	
        setText("");
    }

    //Interface dos eventos do mouse
    @Override
    public void mousePressed(MouseEvent e){
        if (e.getButton() == 1) {
            field.openField();
        } else {
            field.changeMark();
        }
    }

    public void mouseClicked(MouseEvent e){

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){

    }

    
}
