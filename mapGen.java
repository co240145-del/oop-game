import java.awt.*;
import java.util.Random;
	
public class mapGen {
   public int map[][]; //To contain the bricks
   public int blockWidth;
	public int blockHeight;
	private Random random;

	public mapGen(int row, int col, int level){
		map = new int [row][col];
		blockWidth = 540/col; //Change for preference
		blockHeight = 150/row; //Change for preference

		Random random = new Random();

		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				//Increase brick durability with level
				// map[i][j] = random.nextInt(Math.min(1 + level, 5)); //Durability from 0 to 4
				if(level == 1){
					map[i][j] = 1;
				} else{
					map[i][j] = (random.nextFloat() < 0.5) ? 1 : 0; //50% chance for durability 1 or 2
				}
			}
		}
	}
	
	public void draw(Graphics2D g){   
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * blockWidth + 80, i * blockHeight + 50, blockWidth, blockHeight);

					g.setStroke(new BasicStroke(3)); //Click then import
					g.setColor(Color.black);
					g.drawRect(j * blockWidth + 80, i * blockHeight + 50, blockWidth, blockHeight); 
				}
			}
		}
	}
	public void setBlockValue(int value, int row, int col){
		map[row][col] = value;
	}
}

//ball speed
//brick color
//paddle color
//ball color
//background colors