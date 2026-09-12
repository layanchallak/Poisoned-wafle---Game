package view;

import javax.swing.*;
import java.awt.*;

public class LevelSelect extends JPanel {

    public RoundedButton levelEasyButton;
    public RoundedButton levelMediumButton;
    public RoundedButton levelHardButton;
    public RoundedButton backButton;

    public LevelSelect() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // ===== TOP PANEL (RETUR EN HAUT À DROITE) =====
        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        topPanel.setPreferredSize(new Dimension(0, 60));
        topPanel.setMinimumSize(new Dimension(0, 60));
        topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ===== bouton retour =====
        backButton = new RoundedButton("Retour");
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setMaximumSize(new Dimension(100, 300));
        backButton.setActionCommand("BACK");

        // ===== WRAPPER pour margin top =====
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false); // important pour garder style propre

        // espace en haut (margin top)
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // bouton
        rightPanel.add(backButton);

        // placement à droite
        topPanel.add(rightPanel, BorderLayout.EAST);

        // ===== TITLE =====
        JLabel title = new JLabel("Niveau de difficulté");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ===== BUTTONS =====
        levelEasyButton = new RoundedButton("Facile");
        levelMediumButton = new RoundedButton("Moyen");
        levelHardButton = new RoundedButton("Difficile");

        // couleurs et styles
        levelEasyButton.setBackground(new Color(0, 128, 0));
        levelEasyButton.setForeground(Color.WHITE);
        levelEasyButton.setFont(new Font("Arial", Font.BOLD, 25));
        levelEasyButton.setMaximumSize(new Dimension(300, 300));
        levelEasyButton.setMargin(new Insets(15, 30, 15, 30));

        levelMediumButton.setBackground(new Color(255, 165, 0));
        levelMediumButton.setForeground(Color.WHITE);
        levelMediumButton.setFont(new Font("Arial", Font.BOLD, 25));
        levelMediumButton.setMaximumSize(new Dimension(300, 300));
        levelMediumButton.setMargin(new Insets(15, 30, 15, 30));

        levelHardButton.setBackground(new Color(255, 0, 0));
        levelHardButton.setForeground(Color.WHITE);
        levelHardButton.setFont(new Font("Arial", Font.BOLD, 25));
        levelHardButton.setMaximumSize(new Dimension(300, 300));
        levelHardButton.setMargin(new Insets(15, 30, 15, 30));

        // alignement
        levelEasyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelMediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelHardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // action commands
        levelEasyButton.setActionCommand("EASY");
        levelMediumButton.setActionCommand("MEDIUM");
        levelHardButton.setActionCommand("HARD");

        // ===== AJOUT UI =====
        add(topPanel); // header propre

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(levelEasyButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(levelMediumButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(levelHardButton);
        add(Box.createVerticalGlue());
    }
}