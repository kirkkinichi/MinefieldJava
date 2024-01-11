package br.com.kirk.cm.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.kirk.cm.model.Board;

public class BoardPanel extends JPanel{
    public BoardPanel(Board board){

        setLayout(new GridLayout(board.getRow(), board.getColumns()));

        board.runEachField(c -> add(new FieldButton(c)));
        board.observerRegister(event -> {

            SwingUtilities.invokeLater(() -> {
                if (event.isWon()) {
                    JOptionPane.showMessageDialog(this, "Won !");
                }
                else {
                    JOptionPane.showMessageDialog(this, "Lose!");
                }

                board.restart();
            });            
        });
    }
}
