package modele;


public abstract class Player {
    protected String name;
    protected boolean alive;
    protected int score;

    public Player(String name) {
        this.name = name;
        this.alive = true;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getScore() {
        return score;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void lose() {
        alive = false;
    }

    public void reset() {
       setAlive(true);
        setScore(0);
    }

    public void addScore() {
        score++;
    }

    public abstract int[] chooseMove(Game game);
}