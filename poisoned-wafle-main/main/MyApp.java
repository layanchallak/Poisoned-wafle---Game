package main;

import javax.swing.*;
import java.awt.*;

import modele.Game;
import modele.Grid;
import view.LevelSelect;
import view.Menu;
import view.RoundedButton;
import view.View;
import controller.Controller;
import controller.MouseAdapter;
import controller.KeyAdapter;

class MyApp {

    private JFrame frame;

    public MyApp(JFrame frame) {
        this.frame = frame;
    }

    public void start() {

        frame.setLayout(new BorderLayout(10, 10));

        // ===== MODEL =====
        Grid grid = new Grid();
        grid.displayGrid(); // for debug

        Game game = new Game(grid);

        // ===== VIEWS =====
        Menu menu = new Menu();
        View view = new View(game);
        LevelSelect difficulty = new LevelSelect();

        // ===== CARD LAYOUT =====
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        container.add(menu, "MENU");
        container.add(view, "GAME");
        container.add(difficulty, "LEVELSELECT");

        // ===== UI ELEMENTS =====
        JLabel playerLabel = new JLabel(game.getCurrentPlayer().getName());
        playerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        // JButton RandomAIButton = new JButton("Random AI");
        // JButton MediumAIButton = new JButton("Medium AI");
        // JButton SmartAIButton = new JButton("Smart AI");
        RoundedButton resetButton = new RoundedButton("Reset");
        resetButton.setBackground(new Color(70, 130, 180));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setMargin(new Insets(15, 30, 15, 30));
        
        RoundedButton abandonnerButton = new RoundedButton("Quitter la partie");
        abandonnerButton.setBackground(new Color(220, 20, 60));
        abandonnerButton.setForeground(Color.WHITE);
        abandonnerButton.setFont(new Font("Arial", Font.BOLD, 16));
        abandonnerButton.setMargin(new Insets(15, 30, 15, 30));

        /////////////////////////////////// L
        JButton UndoButton = new JButton("◀");
        JButton RedoButton = new JButton("▶");
        /// ///////////////////////////////

        Box bottomBox = Box.createHorizontalBox();
        bottomBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        bottomBox.setPreferredSize(new Dimension(70, 70));
        Box topBox = Box.createHorizontalBox();
        topBox.add(Box.createHorizontalGlue());
        topBox.add(UndoButton);
        topBox.add(RedoButton);

        bottomBox.add(resetButton);
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(playerLabel);
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(abandonnerButton);
        ///////////////////////////// LL

        // ===== CONTROLLER =====
        Controller controller = new Controller(game, view, playerLabel, cardLayout, container, bottomBox, topBox,
                UndoButton, RedoButton);
        MouseAdapter mouse = new MouseAdapter(controller);

        // ===== BIND MENU BUTTONS =====
        menu.iaButton.addActionListener(controller);
        menu.multiButton.addActionListener(controller);

        // ===== BIND GAME INPUT =====
        view.addMouseListener(mouse);
        view.addMouseMotionListener(mouse);

        // ===== BUTTON ACTIONS =====
        resetButton.addActionListener(e -> controller.reset());
        abandonnerButton.addActionListener(e -> {

            // texte plus lisible
            JLabel message = new JLabel("Voulez-vous vraiment abandonner ?");
            message.setFont(new Font("Arial", Font.PLAIN, 18));
            message.setHorizontalAlignment(SwingConstants.CENTER);

            // panel custom pour agrandir la fenêtre
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(650, 250));
            panel.setLayout(new BorderLayout());

            panel.add(message, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(
                    frame,
                    panel,
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                controller.abandonner();
                controller.reset();
                cardLayout.show(container, "MENU");
            }
        });

        difficulty.levelEasyButton.addActionListener(controller);
        difficulty.levelMediumButton.addActionListener(controller);
        difficulty.levelHardButton.addActionListener(controller);

        difficulty.backButton.addActionListener(e -> controller.back());
        /////////////////////////////////// L
        UndoButton.addActionListener(e -> controller.undo());
        RedoButton.addActionListener(e -> controller.redo());
        /// ///////////////////////////////

        // ===== FRAME =====
        frame.add(topBox, BorderLayout.NORTH);
        frame.add(container, BorderLayout.CENTER);
        frame.add(bottomBox, BorderLayout.SOUTH);
        frame.addKeyListener(new KeyAdapter(controller));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        bottomBox.setVisible(false);
        topBox.setVisible(false);
        UndoButton.setEnabled(false);
        RedoButton.setEnabled(false);

        ////////////// PREVENT THE BUTTONS TO BE FOCUSABLE (OTHERWISE,space button calls
        ////////////// some of them)///////////////////

        difficulty.levelEasyButton.setFocusable(false);
        difficulty.levelMediumButton.setFocusable(false);
        difficulty.levelHardButton.setFocusable(false);
        resetButton.setFocusable(false);
        abandonnerButton.setFocusable(false);

        menu.iaButton.setFocusable(false);
        menu.multiButton.setFocusable(false);

        // ===== START ON MENU =====
        cardLayout.show(container, "MENU");

        SwingUtilities.invokeLater(() -> view.requestFocusInWindow());
    }
}