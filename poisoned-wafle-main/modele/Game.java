package modele;

import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;



////////////////////////////////////////////LLL
/////////////////////////////////////////////////////////////////////////////
//class to represent the action of the game (the move of the player) in order to be able to undo and redo it
class ActionJeu{
    private int i ;
    private int j;
    private int type; //type of the case 
   
    private int player;
    private int lost;
   

    public ActionJeu(int i, int j, int type, int player, int lost) {
        this.i = i;
        this.j = j;
        this.type = type ; //the type of the case (i, j)
        this.player = player;
        this.lost = lost;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getType(){
        return type;
    }

    public int getPlayer() {
        return player;
    }

    public int getLost() {
        return lost;
    }

}
/////////////////////////
/// THE MAIN CLASS /////////////////////////

public class Game {

    private Grid grid; // the grid in the Grid class (this grid is used to represent the wafle)
    Random r = new Random();
    private boolean LOST = false; // if a player lost
    public boolean AgainstIA = false; // if the player is playing against the IA or not (extension: we can add more
                                      // player, so we will change this variable to a list of players or something
                                      // like that)
    // attributes for the players
    private boolean RandomAI = false;
    private boolean MediumAI = false;
    private boolean SmartAI = false;
    

    private int NUMBER_OF_PLAYERS = 2;
    private Player[] players; // the player is a human and(OR) IA(HUMAN) players
    private int currentPlayerIndex;
    ////////////////////////////////////LL
    private Stack<ArrayList<ActionJeu>> undoStack;
    private Stack<ArrayList<ActionJeu>> redoStack;
    ///////////////////////////////////////////

    // constructor
    public Game(Grid grid) {
        if (AgainstIA) {
            System.out.println("Game created in IA mode");
        } else {
            System.out.println("Game created in MULTI mode");
        }
        System.out.println("Game created");
        this.grid = grid;
        initPlayers();
        undoStack = new Stack<>();////////////////////////////LL
        redoStack = new Stack<>();/////////////////////////LL
    }
//////////////////////////////////////////////////
    // method to set the difficulty of the IA
    public void setAIDifficulty() {
        if (players != null && players.length > 1 && players[1] instanceof IaPlayer) {
            IaPlayer ia = (IaPlayer) players[1];
            if (SmartAI) {
                ia.setDifficulty(IaPlayer.Difficulty.SMART);
            } else if (MediumAI) {
                ia.setDifficulty(IaPlayer.Difficulty.MEDIUM);
            } else {
                ia.setDifficulty(IaPlayer.Difficulty.RANDOM);
            }
        }
    }
//////////////////////////////////////////////////////
//////////////////METHOD TO RESET THE GAME////////////
    public void reset(){
        //we reset every attributes
        grid.setGrid();//init the grid
        resetPlayers();
        setLost(false);
        
        setAIDifficulty();
        //init the stacks (undo and redo)
        undoStack = new Stack<>();
        redoStack = new Stack<>();


    }
    /************** . METHODS **********************/
    //init the list of players
    private void initPlayers() {
        if (isAgainstIA()) {
            players = new Player[] {
                new HumanPlayer("HUMAN"),
                new IaPlayer("IA")
            };
            currentPlayerIndex = 0;
        } else {
            players = new Player[] {
                new HumanPlayer("Joueur 1"),
                new HumanPlayer("Joueur 2")
            };
             currentPlayerIndex = r.nextInt(getNumberOfPlayers());

        }
       
       
    }
    

     
    //reset the players 
    private void resetPlayers(){
        for(Player p : players) p.reset();
        
        if (isAgainstIA()) {
            currentPlayerIndex = 0; 
        } 
        else {
            currentPlayerIndex = r.nextInt(getNumberOfPlayers());
        }
         
    }
       

    // return the Grid
    public Grid getGrid() {
        return this.grid;
    }

   
    // return the Lost variable value
    public boolean getLost() {
        return LOST;
    }

    public boolean isAgainstIA() {
        return AgainstIA;
    }

    //to know if it's the turn of the human player
    public boolean isHumanTurn(){
        return ! ((getCurrentPlayer().getName()).equals("IA"));
    }

    ////

    public void setAgainstIA(boolean value) {
        this.AgainstIA = value;
        initPlayers(); // reinitialize the players based on the new mode
    }

   
    // to get the currentPlayer
    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    

    // get the list of players
    public Player[] getPlayers() {
        return players;
    }

    public int getRedoStackSize() {
        return redoStack.size();
    }
    public int getUndoStackSize() {
        return undoStack.size();
    }

    // get the number of players
    public int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    /************ METHOD(S) TO SET THE ATTRIBUT(s) ***************/

    // set the variable lost
    public void setLost(boolean bool) {
        this.LOST = bool;

    }

    // set the number of players
    public void setNumberOfPlayers(int nbPlayers) {
        this.NUMBER_OF_PLAYERS = nbPlayers;
    }

    // set the players
    public void setPlayers(Player player1, Player player2) {
        // IF WE WANT TO SET MANY PLAYERS (MORE THAN 2), WE WILL CHANGE THIS FUNCTION
        players[0] = player1;
        players[1] = player2;
    }

    public void setRandomAI(boolean value) {
        this.RandomAI = value;
    }

    public void setMediumAI(boolean value) {
        this.MediumAI = value;
    }

    public void setSmartAI(boolean value) {
        this.SmartAI = value;
    }

    /////// METHODS FOR PLAYERS///////////////////
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

    }
    ///////////////////////////////////////////////////////



    ////////////////////METHODS MOVE FOR HUMAN////////////////////////

    //check if a position is in an arraylist<Position>
    public boolean isInListPosition(ArrayList<Position>Cases , Position p){
        int n = Cases.size();
        for(int i=0 ;i<n; i++){
            if(Cases.get(i).isEqual(p)) return true;
        }
        return false;
    }

    // return an ARRAYlist of the eatable cases from the case (i,j)
    public ArrayList<Position> getEatenCases(int i, int j) {
        int nbLines = grid.getNbLines();
        int nbColumns = grid.getNbColumns();

        ArrayList<Position> eatenCases = new ArrayList<>();

        for (int line = i; line < nbLines; line++) {
            for (int column = j; column < nbColumns; column++) {

                if (grid.isEatable(line, column)) {
                    eatenCases.add(new Position(line, column));
                    // System.out.print()
                } else if (grid.isPoisoned(line, column)) {
                    eatenCases.add(new Position(line, column));
                }
            }
        }

        return eatenCases;
    }

    // check if among the eaten cases, we have the poisoned one (that's mean it's
    // the end of the game)
    public boolean poisonEaten(ArrayList<Position> eatenCases) {
        int n = eatenCases.size();
        for (int i = 0; i < n; i++) {
            int line = eatenCases.get(i).getI();
            int column = eatenCases.get(i).getJ();

            if (grid.isPoisoned(line, column))
                return true;
        }
        return false;
    }
////////////////////////////////////////////////LL
    // MAKE THE MOVE TO EAT (this one is the main function here)
    public void makeMove(int i, int j) {
        if (!grid.isInGrid(i, j))
            return;
        if (grid.isNotEatable(i, j)) {
            System.out.println("This case (" + i + ", " + j + ") can't be eaten");
            return;
        }

        ArrayList<Position> eatenCases = getEatenCases(i, j);
        boolean moveCausesLoss = poisonEaten(eatenCases);

        //add the move to the undo stack
        ArrayList<ActionJeu> actions = new ArrayList<>();
        int playerIndex = currentPlayerIndex;
        int lostFlag = moveCausesLoss ? 1 : 0;
        for (Position position : eatenCases) {
            int type = grid.getCaseType(position.getI(),position.getJ());
            actions.add(new ActionJeu(position.getI(), position.getJ(),type, playerIndex, lostFlag));
        }
        undoStack.push(actions);
        redoStack.clear(); // clear the redo stack when a new move is made
        
        // check if the poison was eaten
        boolean bool = poisonEaten(eatenCases);
        if (bool) {
            System.out.println(getCurrentPlayer().getName() + " LOST!");
            LOST = true;
            getCurrentPlayer().lose(); // the current player lost (alive = false)
            return; // game over, we stop the function
        }

        else {
            // we make the move by changing nature of the eaten cases
            int n = eatenCases.size();
            int value = grid.getNotEatable(); // not eatable value for the case
            for (int k = 0; k < n; k++) {
                int line = eatenCases.get(k).getI();
                int column = eatenCases.get(k).getJ();
                grid.setCase(line, column, value);
            }
            // then switch the player
            nextPlayer();
            // for debug
            System.out.println();
            grid.displayGrid(); // display the grid(help for debug)
            ///////////

        }

    }
    /////////////////////////////////////////////////////////
/////////////////////////////////////////////////////
    ////////////:METHODS FOR MOVES OF AI/////////////////////////////////



    public ArrayList<Position> getvalideCases() {
        int nbLines = grid.getNbLines();
        int nbColumns = grid.getNbColumns();

        ArrayList<Position> valideCases = new ArrayList<>();

        for (int line = 0; line < nbLines; line++) {
            for (int column = 0; column < nbColumns; column++) {
                if (grid.isEatable(line, column)) {
                    valideCases.add(new Position(line, column));
                }
            }
        }
        return valideCases;
    }


    ////METHOD FOR THE AI TO PLAY RANDOMLY
    public void playRandomAI() {
        if (LOST)
            return;

        ArrayList<Position> moves = getvalideCases();

        if (moves.isEmpty()) {
            LOST = true;
            return;
        }
        Position p = moves.get(r.nextInt(moves.size()));
        makeMove(p.getI(), p.getJ());
    }

    public void playAI() {
        if (LOST) return;
        
        if (players == null || players.length < 2) return;
        if (!(players[1] instanceof IaPlayer)) return;
        
        IaPlayer ia = (IaPlayer) players[1];
        int[] move = ia.chooseMove(this);
        
        if (move != null) {
            makeMove(move[0], move[1]);
        } else {
            LOST = true;
        }
    }


    //////////////////////////////////////LLL 
    public void undo() {
        if (!undoStack.isEmpty()) {
            ArrayList<ActionJeu> lastActions = undoStack.pop();
            int undonePlayer = -1;
            boolean undoneLoss = false;

            // Revert the grid changes
            for (ActionJeu action : lastActions) {
                grid.setCase(action.getI(), action.getJ(),action.getType()); //it's here
                undonePlayer = action.getPlayer();
                if (action.getLost() == 1) {
                    undoneLoss = true;
                }
            }
            // If the undone move caused a loss, we need to revert that as well
            if (undoneLoss && undonePlayer >= 0) {
                getPlayers()[undonePlayer].reset();
                LOST = false;
            }
            // Set the current player back to the one who made the undone move
            if (undonePlayer >= 0) {
                currentPlayerIndex = undonePlayer;
            }

            redoStack.push(lastActions);}
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            ArrayList<ActionJeu> lastUndoneActions = redoStack.pop();
            int redoPlayer = -1;// to keep track of the player who made the move that we're redoing
            boolean redoLoss = false;

            // Reapply the grid changes
            for (ActionJeu action : lastUndoneActions) {
                grid.setCase(action.getI(), action.getJ(), grid.getNotEatable());
                redoPlayer = action.getPlayer();
                if (action.getLost() == 1) {
                    redoLoss = true;
                }
            }
            // If the redone move caused a loss, we need to set that again
            if (redoLoss && redoPlayer >= 0) {
                getPlayers()[redoPlayer].lose();// set the player as lost again
                LOST = true;
                currentPlayerIndex = redoPlayer;
            } 
            //if the redone move didn't cause a loss, we just set the current player to the one who made the move
            else if (redoPlayer >= 0) {
                currentPlayerIndex = (redoPlayer + 1) % players.length;
            }

            undoStack.push(lastUndoneActions);
        }
    }   
    ////////////////////////////////////////////
}
