import java.awt.*;
	
public class mapGen {
   public int map[][]; //To contain the bricks
   public int blockWidth;
	public int blockHeight;

	public mapGen(int row, int col){
		map = new int [row][col];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				map[i][j] = 1; //to not be intercepted with the ball


			}
		}
		blockWidth = 540/col; //Change for preference
		blockHeight = 150/row; //Change for preference
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