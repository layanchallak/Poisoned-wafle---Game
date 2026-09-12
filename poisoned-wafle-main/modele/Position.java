package modele;

public class Position {
    private int i;
    private int j;

   public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /********** GETTER  AND SETTER METHODS *******/
    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
    /**************METHODS **************/
    
    public boolean isEqual(Position p2){
        return getI()==p2.getI() && getJ()==p2.getJ();
    }
}
