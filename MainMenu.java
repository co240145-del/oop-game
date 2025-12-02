
import java.awt.*;
import javax.swing.*;

public class MainMenu {
    public static void main(String[] args) {
        // Create the main frame
        JFrame MainMenu = new JFrame("Main Menu");
        MainMenu.setBounds(10, 10, 700, 600);
        MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenu.setLocationRelativeTo(null); // Center window

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        // Create buttons
        JButton startButton = new JButton("Start");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");

        // Game Scene frame
        JFrame GameScene = new JFrame();
        GameScene.setBounds(10, 10, 700, 600);
        GameScene.setTitle("Brick crash");
        GameScene.setResizable(false);
        GameScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameScene.setLocationRelativeTo(null);
        Game game = new Game();
        GameScene.add(game);

        // Button actions
        startButton.addActionListener(e -> {
            MainMenu.setVisible(false);
            GameScene.setVisible(true);
        }
            
            
        );

        settingsButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(MainMenu, "Settings clicked!")
        );

        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        panel.add(startButton);
        panel.add(settingsButton);
        panel.add(exitButton);

        // Add panel to frame
        MainMenu.add(panel);
        MainMenu.setVisible(true);
    }
}