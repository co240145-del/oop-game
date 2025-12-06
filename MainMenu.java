import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {
    public static void main(String[] args) {

        //Main Menu
        JFrame frame = new JFrame("Brick Crash: Main Menu");
        frame.setSize(700, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Game Scene
        JFrame GameScene = new JFrame();
        GameScene.setBounds(10,10,700,600);
        GameScene.setTitle("Brick Crash");
        GameScene.setResizable(false);
        GameScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameScene.setLocationRelativeTo(null);
        Game game = new Game();
        GameScene.add(game);

        // Background
        Image backgroundImage = new ImageIcon("Assets\\Background").getImage();
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.darkGray);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); 


        //label/title
        JLabel label = new JLabel("Brick Crash");
        label.setFont(new Font("Arial", Font.BOLD, 86));
        label.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(label, gbc);


        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));


        Font customFont = new Font("Serif", Font.BOLD, 50);

        JButton startButton = new JButton("Start");
        startButton.setFont(customFont);
        startButton.setPreferredSize(new Dimension(300, 100));
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.BLUE);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(customFont);
        exitButton.setPreferredSize(new Dimension(300, 100));
        exitButton.setBackground(Color.white);
        exitButton.setForeground(Color.BLUE);


        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setBackground(new Color(100, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setBackground(Color.white);
            }
        });

         exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(new Color(100, 0, 0)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(Color.white);
            }
        });


        //button responce
        startButton.addActionListener(e ->{
            frame.setVisible(false);
            GameScene.setVisible(true);
            }       
        );
        exitButton.addActionListener(e -> System.exit(0));

        //add to frame
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);


        gbc.gridy = 1;
        backgroundPanel.add(buttonPanel, gbc);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}
