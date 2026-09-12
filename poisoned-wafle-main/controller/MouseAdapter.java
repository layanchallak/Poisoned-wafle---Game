package controller;




import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseAdapter implements  MouseListener, MouseMotionListener{
    private Controller controller;

    //constructor
    public MouseAdapter(Controller controller){
        this.controller = controller;
    }

    //when the mouse is clicked, we eat
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        controller.eat(x,y);

    }
    
    //when the mouse is dragged, we just color it
     @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        controller.corlorCells(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }




}