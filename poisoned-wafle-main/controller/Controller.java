package controller;

import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import modele.Game;
import view.View;

public class Controller implements ActionListener {
    private Game game;
    private View view;
    private JLabel playerLabel;
    Box bottomBox;

    private CardLayout layout;
    private JPanel container;
    private Box topBox;
    private JButton UndoButton;
    private JButton RedoButton;

    public Controller(Game game, View view, JLabel playerLabel, CardLayout layout, JPanel container, Box bottomBox,
            Box topBox, JButton UndoButton, JButton RedoButton) {
        this.game = game;
        this.view = view;
        this.playerLabel = playerLabel;
        this.layout = layout;
        this.container = container;
        this.bottomBox = bottomBox;
        this.topBox = topBox;
        this.UndoButton = UndoButton;
        this.RedoButton = RedoButton;

    }

    //////////////////// these 2 mehtods are used to convert the value from the
    //////////////////// screen to the grid
    private int convertColumn(int x) {
        return x / view.getCellSize();

    }

    private int convertLine(int y) {
        return y / view.getCellSize();

    }
    /////////////////////////

    // method for the player Label////////////////////
    private void updatePlayerLabel() {
        playerLabel.setText(game.getCurrentPlayer().getName());
    }
    ////////////////////////////////////////////////

    // to mark the cell of the waffle that are gonna be it from the cell (x , y)
    public void corlorCells(int x, int y) {
        int i = convertLine(y);
        int j = convertColumn(x);

        //// to implement/////////
        view.setPosition(i, j); // send the position of the case to the view

        view.repaint(); // view repaint it

    }

    public void initTimer(){
        
    }

    // eat the chosen part (the principal function to eat ----the most important)

    public void eat(int x, int y) {
        if (game.getLost())
            return;

         //System.out.println(game.isHumanTurn());

        if(! game.isHumanTurn()) return ;
            


        int i = convertLine(y);
        int j = convertColumn(x);

        game.makeMove(i, j);
        topBox.setVisible(true);
        updatePlayerLabel();
        updateUndoRedoButtons();

        view.repaint();


        if (!game.getLost() && game.isAgainstIA() && !game.isHumanTurn()) {
            javax.swing.Timer timer = new javax.swing.Timer(800, e -> {
            game.playAI();
            updatePlayerLabel();
            view.repaint();
            });

        timer.setRepeats(false);
        timer.start();
    }

        

       
        
    }

    // reset the game
    public void reset() {
        // reset the game
        game.reset();
        updatePlayerLabel();
        updateUndoRedoButtons();
        // we repaint the view
        view.repaint();
    }

    public void abandonner() {
        game.setLost(true);
        bottomBox.setVisible(false);
        topBox.setVisible(false);
        view.repaint();
    }

    //////////////////////////////////////////////////////// L
    public void undo() {
        game.undo();
        updatePlayerLabel();
        updateUndoRedoButtons();
        view.repaint();
        
        //the IA PLAYS
        if (!game.getLost() && game.isAgainstIA() && !game.isHumanTurn()) {
        javax.swing.Timer timer = new javax.swing.Timer(800, e -> {
            game.playAI();
            updatePlayerLabel();
            view.repaint();
        });
        timer.setRepeats(false);
        timer.start();
        }
        
    }

    public void redo() {
        game.redo();
        updatePlayerLabel();
        updateUndoRedoButtons();
        view.repaint();

        //IA PLAYS
        if (!game.getLost() && game.isAgainstIA() && !game.isHumanTurn()) {
        javax.swing.Timer timer = new javax.swing.Timer(800, e -> {
            game.playAI();
            updatePlayerLabel();
            view.repaint();
        });
        timer.setRepeats(false);
        timer.start();
        }

    }

    private void updateUndoRedoButtons() {
        UndoButton.setEnabled(game.getUndoStackSize() > 0);
        RedoButton.setEnabled(game.getRedoStackSize() > 0);
    }

    ////////////////////////////////////////////////// L

    /////////////// METHODS FOR AI////////////////

    public void setRandomAI() {

        game.setAgainstIA(true);
        game.setRandomAI(true);
        game.setMediumAI(false);
        game.setSmartAI(false);
        game.setAIDifficulty(); // la difficulté
        //updatePlayerLabel();  //(why???_____à revoir________)
        //view.repaint();
    }

    public void setMediumAI() {
        game.setAgainstIA(true);
        game.setRandomAI(false);
        game.setMediumAI(true);
        game.setSmartAI(false);
        game.setAIDifficulty(); // la difficulté
        //updatePlayerLabel();
        //view.repaint();
    }

    public void setSmartAI() {
        game.setAgainstIA(true);
        game.setRandomAI(false);
        game.setMediumAI(false);
        game.setSmartAI(true);
        game.setAIDifficulty(); // la difficulté
       // updatePlayerLabel();
        //view.repaint();
    }

    public void back() {
        layout.show(container, "MENU");
        topBox.setVisible(false);
        bottomBox.setVisible(false);
        System.out.println("Back to menu");
    }
    ///////////////////////////////////////////////

    ///////////////////////////////////////////////

    // this method is here to handle the actions of the main menu buttons and start
    // the selected game mode
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("IA")) {

            layout.show(container, "LEVELSELECT");
            System.out.println("IA mode selected");
            bottomBox.setVisible(false);    
            game.setAgainstIA(true);
           
        }

        if (e.getActionCommand().equals("MULTI")) {

            game.setAgainstIA(false);
            layout.show(container, "GAME");
            topBox.setVisible(true);
            bottomBox.setVisible(true);
            System.out.println("MULTI mode selected");
            updatePlayerLabel(); 
            updateUndoRedoButtons(); 

        }

        if (e.getActionCommand().equals("LevelSelect")) {
            layout.show(container, "LEVELSELECT");
            System.out.println("Level menu selected");
        }

        if (e.getActionCommand().equals("BACK")) {
            layout.show(container, "MENU");
            topBox.setVisible(false);
            bottomBox.setVisible(false);
            System.out.println("Back to menu");
        }

        if (e.getActionCommand().equals("EASY")) {
            setRandomAI();
            layout.show(container, "GAME");
            bottomBox.setVisible(true);
            topBox.setVisible(true);
            System.out.println("Easy level selected");
            updatePlayerLabel(); 
            updateUndoRedoButtons(); 
        }
        if (e.getActionCommand().equals("MEDIUM")) {
            setMediumAI();
            layout.show(container, "GAME");
            bottomBox.setVisible(true);
            topBox.setVisible(true);
            System.out.println("Medium level selected");
            updatePlayerLabel(); 
            updateUndoRedoButtons(); 
        }

        if (e.getActionCommand().equals("HARD")) {
            setSmartAI();
            layout.show(container, "GAME");
            bottomBox.setVisible(true);
            topBox.setVisible(true);
            System.out.println("Hard level selected");
            updatePlayerLabel(); 
            updateUndoRedoButtons(); 
        }
    }

    ////////////////////////////////////////////
}