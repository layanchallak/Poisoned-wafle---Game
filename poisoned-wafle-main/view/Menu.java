package view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public RoundedButton iaButton;
    public RoundedButton multiButton;

    public Menu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Welcome to Poisoned Waffle");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

      
        iaButton = new RoundedButton("Jouer contre l'ordinateur");
        ImageIcon originalIcon = new ImageIcon(
                getClass().getResource("/images/Robot.png"));

        Image img = originalIcon.getImage();
        Image scaled = img.getScaledInstance(100, 50, Image.SCALE_SMOOTH);

        iaButton.setIcon(new ImageIcon(scaled));
        iaButton.setHorizontalTextPosition(SwingConstants.LEFT);
        iaButton.setIconTextGap(5);

        multiButton = new RoundedButton("Jouer contre un ami");
        ImageIcon versusIcon = new ImageIcon(
                getClass().getResource("/images/v.jpg"));

        Image versus_img = versusIcon.getImage();
        Image scaled_versus = versus_img.getScaledInstance(100, 60, Image.SCALE_SMOOTH);

        multiButton.setIcon(new ImageIcon(scaled_versus));
        multiButton.setHorizontalTextPosition(SwingConstants.LEFT);
        multiButton.setIconTextGap(18);

        iaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        multiButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // tailles
        iaButton.setMaximumSize(new Dimension(490, 400));
        multiButton.setMaximumSize(new Dimension(490, 400));

        // fonts
        iaButton.setFont(new Font("Arial", Font.BOLD, 25));
        multiButton.setFont(new Font("Arial", Font.BOLD, 25));
        iaButton.setMargin(new Insets(15, 30, 15, 30));
        multiButton.setMargin(new Insets(15, 30, 15, 30));
        // style
        iaButton.setBackground(new Color(18, 18, 18));
        iaButton.setForeground(Color.WHITE);
        iaButton.setOpaque(true);

        multiButton.setBackground(new Color(18, 18, 18));
        multiButton.setForeground(Color.WHITE);
        multiButton.setOpaque(true);

        // bordures arrondies

        iaButton.setBorderPainted(true);
        multiButton.setBorderPainted(true);

        iaButton.setContentAreaFilled(false);
        multiButton.setContentAreaFilled(false);

        iaButton.setOpaque(false);
        multiButton.setOpaque(false);

        // enlever focus moche
        iaButton.setFocusPainted(false);
        multiButton.setFocusPainted(false);

        // actions
        iaButton.setActionCommand("IA");
        multiButton.setActionCommand("MULTI");

        // layout
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(iaButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(multiButton);
        add(Box.createVerticalGlue());
    }
}