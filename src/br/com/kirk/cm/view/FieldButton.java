package br.com.kirk.cm.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.kirk.cm.model.Field;
import br.com.kirk.cm.model.FieldEvent;
import br.com.kirk.cm.model.FieldObserver;

public class FieldButton extends JButton implements FieldObserver{


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
        setBorder(BorderFactory.createBevelBorder(0));
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
        
    }

    private void applyExplodeStyle() {

    }

    private void applyMarkStyle() {

    }    

    private void applyMarkOffStyle() {

    }

    private void applyDefaultStyle() {

    }
}
