package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;




public class Main implements Runnable {

    @Override
    public void run() {
     
        JFrame frame = new JFrame("Poisoned Waffle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create and start the app
        MyApp app = new MyApp(frame);
        app.start();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 1000);
        frame.setVisible(true); 
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
}