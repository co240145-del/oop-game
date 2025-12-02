
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;





public class Game extends JPanel implements KeyListener, ActionListener {
   private boolean play = false;
   private int score = 0;

   private int Bricks = 21;

   private Timer time;
   private int delay = 8;

   private int PlayerX = 310;

   //Ball Position
   private int BallX = 120;
   private int BallY = 350;

   //Ball Direction
   private int BallXdir = -1;
   private int BallYdir = -2;



   private mapGen map;

   public Game(){
      map = new mapGen(3, 7);
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      time = new Timer(delay, this);
      time.start();
   }

   @Override
   public void paint(Graphics gameObject){
      //BG
      gameObject.setColor(Color.BLACK);
      gameObject.fillRect(1, 1, 692, 592);

      //map
      map.draw((Graphics2D) gameObject);

      //Border
      gameObject.setColor(Color.YELLOW);
      gameObject.fillRect(0, 0, 3, 592);
      gameObject.fillRect(0, 0, 692, 3);
      gameObject.fillRect(682, 0, 3, 592);

      // Score
      gameObject.setColor(Color.white);
      gameObject.setFont(new Font( "arial", Font.BOLD, 25));
      gameObject.drawString("Points:"+score, 20, 30);

      //Paddle
      gameObject.setColor(Color.green);
      gameObject.fillRect(PlayerX, 550, 100, 8);

      //Ball
      gameObject.setColor(Color.red);
      gameObject.fillOval(BallX, BallY, 20, 20);

      if(BallY > 570){
         play = false;
         BallXdir = 0;
         BallYdir = 0;
         gameObject.setColor(Color.red);
         gameObject.setFont(new Font("arial", Font.BOLD, 30));
         gameObject.drawString("You Died. \nScore: " + score, 190, 300);

         gameObject.setFont(new Font("arial", Font.BOLD, 30));
         gameObject.drawString("Continue?", 230, 350);
      }
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if(play){
            if(new Rectangle(BallX, BallY, 20, 20).intersects(new Rectangle(PlayerX, 550, 100, 8))){
               BallYdir = -BallYdir;
            }

            A: for(int i = 0; i < map.map.length; i++){
	         	for(int j = 0; j< map.map[0].length; j++){
	         		if(map.map[i][j] > 0){
	         			int blockX = j * map.blockWidth + 80;
	         			int blockY = i * map.blockHeight + 50;
	         			int blockWidth = map.blockWidth;
	         			int blockHeight = map.blockHeight;
                  
	         			//Creation of Rectangle around the brick
	         			Rectangle rect = new Rectangle(blockX, blockY, blockWidth, blockHeight);
	         			Rectangle ballRect = new Rectangle(BallX, BallY, 20, 20);
	         			Rectangle blockRect = rect;
                  
	         			if(ballRect.intersects(blockRect)){
	         				map.setBlockValue(0, i, j);
	         				Bricks--;
	         				score += 1;
                     
	         				if(BallX + 19 <= blockRect.x || BallX + 1 >= blockRect.x + blockRect.width){
	         					BallXdir = -BallXdir;
	         				}else{
	         					BallYdir = -BallYdir;
	         				}
                     
	         				break A;
	         			}
	         		}	
	         	}
	         }
            BallX += BallXdir;
            BallY += BallYdir;
            if(BallX < 0){
               BallXdir = -BallXdir;
            }
            if(BallY < 0){
               BallYdir = -BallYdir;
            }
            if(BallX > 670){
               BallXdir = -BallXdir;
            }

            if (Bricks <= 0) {
               play = false;
               BallXdir = 0;
               BallYdir = 0;

               // Win message
               JOptionPane.showMessageDialog(this, "YOU WIN!\nScore: " + score);
               resetGame();
               }

         }
        repaint();
   }
   public void resetGame() {
    play = false;

    BallX = 120;
    BallY = 350;
    BallXdir = -1;
    BallYdir = -2;

    PlayerX = 310;

    score = 0;

    map = new mapGen(3, 7);
    Bricks = 3 * 7; // automatically set to 21

    repaint();
}

 @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(PlayerX >= 600){
               PlayerX = 600;
            }
            else{
               playerRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(PlayerX < 10){
               PlayerX = 10;
            }
            else{
               playerLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
               play = true;


               BallX = 120;
               BallY = 350;
               BallXdir = -1;
               BallYdir = -2;
               PlayerX = 310;
               score = 0;
               Bricks = 21;
               map = new mapGen(3, 7);
               repaint();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            //Return to menu
        }
    }
    //Player Movements
    public void playerRight(){
      play = true;
      PlayerX+=20;
    }
   public void playerLeft(){
      play = true;
      PlayerX-=20;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
