package view;

import modele.Position;

import java.util.ArrayList;
import java.awt.Dimension;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

import modele.Game;


public class View extends JComponent {
    Game game;
    private Image waffleImage;
    private Image poisonImage;
    private Image emptyImage;

    // the cellsize
    private int CELLSIZE;

    //color cells
    private ArrayList<Position> eatenCases; 
    private Position p; //the position of the mouse(in order to color the cell),this position is sent by controller


    public View(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(600, 600));

        // load the images
        try {
            waffleImage = ImageIO.read(getClass().getResource("/images/waffle.jpeg"));
            poisonImage = ImageIO.read(getClass().getResource("/images/poison.jpeg"));
            emptyImage = ImageIO.read(getClass().getResource("/images/empty.jpeg"));

        } catch (IOException e) {
            System.out.println("Can't read images");
            e.printStackTrace();
        }
    }


    ////setter() method(s)////////////
    public void setPosition(int i, int j){
        p = new Position(i , j);
    }
    ///////////////////////////////


    /*************** get  MEHODS ***********/

    // to get the cellsize (used in the controller in order to convert the
    // dimensions)
    public int getCellSize() {
        return CELLSIZE;
    }
    ///////////////////////////////////////

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // let's draw
        int nbLines = game.getGrid().getNbLines();
        int nbColumns = game.getGrid().getNbColumns();

        CELLSIZE = getWidth() / nbColumns; // the cellsize
        
      
        ////////////////////////////////////
        //drawing the whole grid
        
        for (int i = 0; i < nbLines; i++) {
            for (int j = 0; j < nbColumns; j++) {
                // the position on the screen
                int x = j * CELLSIZE;
                int y = i * CELLSIZE;

                

                if (game.getGrid().isPoisoned(i, j)) {
                    g.drawImage(poisonImage, x, y, CELLSIZE, CELLSIZE, null);

                } else if (game.getGrid().isEatable(i, j)) {
                    g.drawImage(waffleImage, x, y, CELLSIZE, CELLSIZE, null);

                } else {
                    g.drawImage(emptyImage, x, y, CELLSIZE, CELLSIZE, null);
                }
            }
        }

        
        
        //END OF THE GAME
        if (game.getLost()) {

            String text = game.getCurrentPlayer().getName() + " a perdu!";

            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.RED);

            FontMetrics fm = g.getFontMetrics();

            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            g.drawString(text, x, y);
            return ; //STOP

        }

          /////for coloring the cell ////////////////////
       
        g.setColor(new Color(255, 0, 0, 100)); // //for the colored cell 

        if(p!=null){
            //it means we have received the positon of the initial cell that we want to eat
            //(the mouse is on that position)

            eatenCases = game.getEatenCases( p.getI(), p.getJ() );
            int n = eatenCases.size();
            for(int i=0;i<n;i++){
                int x = eatenCases.get(i).getJ() * CELLSIZE;
                int y = eatenCases.get(i).getI() * CELLSIZE ;
                g.fillRect(x,y,CELLSIZE,CELLSIZE);
            }
           

        }
       

    }

}