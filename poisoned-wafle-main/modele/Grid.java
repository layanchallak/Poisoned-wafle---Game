package modele;




public class Grid {
    private int NBLINES = 6 ;
    private int NBCOLUMNS = 8 ;
    
    //attributes specific for setting the grid
    private int POISON = 0 ;
    private int EATABLE = 1;
    public int NOTEATABLE = -1; 
    
    ////////////////////////////

    
    private int [][] grid;


    /******constructor******/

    //in order to modify this later with my group, we will just add the nblines and columns as args in the constructors
    // if we want to set the number of lines and columns 
    
    public Grid(){ 
        setGrid();
    }

    /***************** MEHODS *****************/


    //set the grid(initialisation);
    public void setGrid(){
       int nbLines = getNbLines();
       int nbColumns = getNbColumns();

        this.grid = new int [nbLines][nbColumns];//init the grid itself
        
        for(int i=0; i<nbLines; i++){
            for(int j=0; j<nbColumns; j++){
                //the first case is poisonned(that's the rule of the game)
                //for the extension, we could generate the position randomly
                //and remove the following condition just below,and adding a condtion 
                //in order not to modify the poison position while setting the eatable cases in the loop
                //nb:the random position will be set out of the loop
                //THIS IS HERE IN ORDER TO HELP US QUICKLY MAKE THE EXTENSION (IF WE WANT)
                if(i==0 && j==0){
                    setCase(i, j, POISON);
                }
            
                else{
                    setCase(i, j, EATABLE);
                }
            }
        }
   }

    //display the grid(help for debug)
   public void displayGrid(){
        int nbLines = getNbLines();
        int nbColumns = getNbColumns();
        for(int i=0; i<nbLines;i++){
            for(int j=0; j<nbColumns;j++){
            System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

   }

    /*the following methods are here in order to get the attributes*/

    //return the number of lines
    public int getNbLines(){
        return this.NBLINES;
    }

    
    //return the number of columns
    public int  getNbColumns(){
        return this.NBCOLUMNS;
    }
    
    //return the grid
    public int [][] getGrid(){
        return this.grid;
    }
    //return the notEatable value(cause i need it in the game class)
    public int getNotEatable(){
        return this.NOTEATABLE ;
    }
    //return the poison value
    public int getPoison(){
        return this.POISON ;
    }
    //return the eatable value
    public int getEatable(){
        return this.EATABLE ;
    }
    //get the poison position(the line or I)
    // i used the loop instead of directly returning (0,0) as the rule of the game want it
    // because if we want to extend it, let's say we want to put the poison randomly
    //so the 2  following functions will help to get the position (line,column) of the poison
    public int getPoisonI(){
        int nbLines = getNbLines();
        int nbColumns = getNbColumns();
        for(int i=0; i<nbLines;i++){
            for(int j=0; j<nbColumns;j++){
            
                if(isPoisoned(i,j)) return i;
            }
        }
        return -1; //not found (so the problem is in the init we don't have the poison)
        
    }
    public int getPoisonJ(){
        int nbLines = getNbLines();
        int nbColumns = getNbColumns();
        for(int i=0; i<nbLines;i++){
            for(int j=0; j<nbColumns;j++){
                if(isPoisoned(i,j)) return j;
            }

        }
        return -1 ; //poison not found
    }

    //to get the grid(i,j) type
    public int getCaseType(int i, int j){
        return grid[i][j];
    }



    /*the following methods are here in order to set the attributes*/

    //set the number of lines
    public void setNbLines(int nbLines){
        this.NBLINES = nbLines;
    }

     //set the number of columns
    public void setNbColumns(int nbColumns){
        this.NBCOLUMNS = nbColumns;
    }
    
    

   //set a specific case
   public void setCase(int i, int j, int value){
        if(!isInGrid(i,j)) return;
        this.grid[i][j] = value;

   }

 

   //check that we're not out of the grid limits
    public boolean isInGrid(int i, int j){
        if(i > getNbLines()){
            System.out.println("line " + i + " out of the grid");
            return false;
        }
        if(j > getNbColumns()){
            System.out.println("column " + j + " out of the grid");
            return false;
        }
        return true;
    }

    /*the following methodes are here to check what's in a specific case in the grid(the wafle)*/

   //is the case(i,j) poisonned?
   public boolean isPoisoned(int i, int j){
    return this.grid[i][j] == POISON ;
   }
   
   //is the case(i,j) eatable?
   public boolean isEatable(int i, int j){
    return this.grid[i][j] == EATABLE ;
   }
   
   //is the case (i,j) noteatable?
   public boolean isNotEatable(int i, int j){
    return this.grid[i][j] == NOTEATABLE ;
   }








}