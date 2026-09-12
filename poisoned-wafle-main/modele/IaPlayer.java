package modele;

import java.util.ArrayList;
import java.util.Random;


public class IaPlayer extends Player{

    public enum Difficulty {
        RANDOM,
        MEDIUM,
        SMART
    }

    private Difficulty difficulty;
    private Random random;

    public IaPlayer(String name) {
        super(name);
        this.difficulty = Difficulty.RANDOM; // Default difficulty
        this.random = new Random();
    }

    

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public int[] chooseMove(Game game) {
        switch (difficulty) {
            case RANDOM:
                return chooseRandom(game);
            case MEDIUM:
                return chooseMedium(game);
            case SMART:
                return chooseSmart(game);
            default:
                return chooseRandom(game);
        }
    }

    // Moves
        // EASY: Random
    private int[] chooseRandom(Game game) {
        ArrayList<Position> moves = game.getvalideCases();
        if (moves.isEmpty()) return null;
        Position p = moves.get(random.nextInt(moves.size()));
        return new int[] { p.getI(), p.getJ() };
    }

    // MEDIUM: Avoid poison 

    /* Another suggestion to implement this: we use an algorithme like the minMax but the difference will
        be that,to choose a path,we randomly choose between a win and a losing path*/ 
    private int[] chooseMedium(Game game) {
        ArrayList<Position> moves = game.getvalideCases();
        if (moves.isEmpty()) return null;

        ArrayList<Position> safeMoves = new ArrayList<>();
        for (Position p : moves) {
            ArrayList<Position> eaten = game.getEatenCases(p.getI(), p.getJ());
            if (!game.poisonEaten(eaten)) {
                safeMoves.add(p);
            }
        }

        if (!safeMoves.isEmpty()) {
            //we chose a random in the safeMove
            Position p = safeMoves.get(random.nextInt(safeMoves.size()));
            return new int[] { p.getI(), p.getJ() };
        }

        //poison in safeMove(so we play it randomly)
        Position p = moves.get(random.nextInt(moves.size()));
        return new int[] { p.getI(), p.getJ() };
    }



    private static final int SMART_DEPTH = 8;

    // HARD: Minimax // 

    private int[] chooseSmart(Game game) {

        ArrayList<Position> moves = game.getvalideCases();

        if (moves.isEmpty()) 
            return null;

        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Position p : moves) {

            ArrayList<Position> eaten = game.getEatenCases(p.getI(), p.getJ());
            int score;

            if (game.poisonEaten(eaten)) {
                score = -1; // IA mange le poison (loose)
            } else {
                game.makeMove(p.getI(), p.getJ());
                score = minimax(game, false, alpha, beta, SMART_DEPTH - 1);
                game.undo();
            }

            if (score > bestScore) {
                bestScore = score;
                bestMove = new int[] { p.getI(), p.getJ() };
            }

            alpha = Math.max(alpha, bestScore);
        }

        return bestMove;
    }

    private int minimax(Game game, boolean isMaxTurn, int alpha, int beta, int depth) {
        ArrayList<Position> moves = game.getvalideCases();

        // cas terminal : plus de coups valide donc le joueur courant doit manger le poison

        if (moves.isEmpty()) {
            return isMaxTurn ? -1 : 1;
        }

        // Limite de profondeur atteinte
        if (depth == 0) {
            return 0;
        }

        if (isMaxTurn) {

            int best = Integer.MIN_VALUE;

            for (Position p : moves) {
                ArrayList<Position> eaten = game.getEatenCases(p.getI(), p.getJ());
                int score;

                if (game.poisonEaten(eaten)) {
                    score = -1; // MAX mange le poison donc il  perd
                } else {
                    game.makeMove(p.getI(), p.getJ());
                    score = minimax(game, false, alpha, beta, depth - 1);
                    game.undo();
                }

                best = Math.max(best, score);
                alpha = Math.max(alpha, best);

                if (alpha >= beta) break; // copure beta
            }
            return best;

        } else {
            int best = Integer.MAX_VALUE;

            for (Position p : moves) {

                ArrayList<Position> eaten = game.getEatenCases(p.getI(), p.getJ());
                int score;

                if (game.poisonEaten(eaten)) {
                    score = 1; // MIN mange le poison donc MAX gagne
                } else {
                    game.makeMove(p.getI(), p.getJ());
                    score = minimax(game, true, alpha, beta, depth - 1);
                    game.undo();
                }

                best = Math.min(best, score);
                beta = Math.min(beta, best);
                
                if (beta <= alpha) 
                    break; // coupure alpha
            }
            return best;
        }
    }

}

