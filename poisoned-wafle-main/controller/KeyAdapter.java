package controller;
import  java.awt.event.KeyEvent;

public class KeyAdapter extends java.awt.event.KeyAdapter {
    private Controller controller;

    public KeyAdapter(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                controller.undo();
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                controller.redo();
                break;
            case java.awt.event.KeyEvent.VK_R:
                controller.reset();
                break;
            default:
                break;
        }
    }
}