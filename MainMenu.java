import javax.swing.*;
import java.awt.*;


public class MainMenu {
    public static void main(String[] args) {
  
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 





        //background
        Image backgroundImage = new ImageIcon("background.jpg").getImage();
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.darkGray);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); 





        //label/title
        JLabel label = new JLabel("hvhv");
        label.setOpaque(false);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.CENTER);

       

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); 
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
    

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(300,100));
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");




        //button responce
        startButton.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Start clicked!")
        );

        settingsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Settings clicked!")
        );

        exitButton.addActionListener(e -> System.exit(0));



        buttonPanel.add(startButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(exitButton);

        // Add button panel to background panel
        backgroundPanel.add(buttonPanel);
        backgroundPanel.add(label);

        // Add background panel to frame
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}



