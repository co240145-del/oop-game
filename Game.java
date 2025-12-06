import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class Game extends JPanel implements KeyListener, ActionListener {
   private ArrayList<PowerUp> powerUps = new ArrayList<>();
   private boolean play = false;
   private int score = 0;
   private int level = 1;
   //Buff flags
   private boolean bigPaddleActive = false;


   // Buff timer duration (ms)
   private int BUFF_DURATION = 5000; // 5 seconds
   private int Bricks = 21;
   private int paddleWidth = 100; // default
   private Timer time;
   private int delay = 8;


   private int PlayerX = 310;

   //Ball Position
   private int BallX = 120;
   private int BallY = 350;

   //Ball Direction
   private int BallXdir = -1;
   private int BallYdir = -2;
   
   private Timer bigPaddleTimer;

   private mapGen map;

   public Game(){
      map = new mapGen(3, 7, level);
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
      gameObject.drawString("Points: " + score, 20, 30);

      //Paddle
      gameObject.setColor(Color.green);
      gameObject.fillRect(PlayerX, 550, paddleWidth, 8);

      //Ball
      gameObject.setColor(Color.red);
      gameObject.fillOval(BallX, BallY, 20, 20);
      for (PowerUp p : powerUps) {
         p.draw(gameObject);
      }
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
            if(new Rectangle(BallX, BallY, 20, 20).intersects(new Rectangle(PlayerX, 550, paddleWidth, 8))){
               BallY = 550 - 20;//fix for the ball getting stuck in paddle
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
                        //collision w/ block
	         				map.setBlockValue(0, i, j);
	         				Bricks--;
	         				score += 1;

                            // 20% chance to drop a power-up
                            if (Math.random() < 0.20) {

                                int dropX = blockX + blockWidth / 2;
                                int dropY = blockY;

                                int type = (int)(Math.random() * 2); 
    

                                powerUps.add(new PowerUp(dropX, dropY, type));
                            }   
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
            level++;
            map = new mapGen(3, 7, level);
            Bricks = 3 * 7;
            //map reset when all brick get destroyed
            
            }

         }
         // Move power-ups
    for (int i = 0; i < powerUps.size(); i++) {
        PowerUp p = powerUps.get(i);
        p.update();
    
        // If power-up touches paddle
        if (new Rectangle(p.x, p.y, p.width, p.height).intersects(
                new Rectangle(PlayerX, 550, 100, 8))) {
                
            applyPowerUp(p.type);
            p.active = false;
        }
    
        // Remove if off-screen
        if (p.y > 600 || !p.active) {
            powerUps.remove(i);
            i--;
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

    map = new mapGen(3, 7, level);
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
               map = new mapGen(3, 7, level);
               repaint();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
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

    public void applyPowerUp(int type) {

        switch (type) {

            case 0: // BIG PADDLE
                activateBigPaddle();
                break;

            case 1: // EXTRA POINTS
                score += 5;
                break;
        }
    }
    private void activateBigPaddle() {

        if (!bigPaddleActive) {


            bigPaddleActive = true;

            // Increase paddle size
            paddleWidth = 150;   // <-- Change paddle to use paddleWidth

            // Timer removes effect after duration
            bigPaddleTimer = new Timer(BUFF_DURATION, e -> {


                paddleWidth = 100; // restore original
                bigPaddleActive = false;
            });

            bigPaddleTimer.setRepeats(false);
            bigPaddleTimer.start();
        }
    }
}
