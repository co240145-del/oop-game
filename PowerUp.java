import java.awt.*;

public class PowerUp {

    public int x, y;
    public int width = 20;
    public int height = 20;

    public int speed = 2;
    public boolean active = true;

    public int type; // 0 = bigger paddle, 1 = slower ball, etc.

    public PowerUp(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        if (!active) return;

        switch (type) {
            case 0: g.setColor(Color.CYAN); break;       // bigger paddle
            case 1: g.setColor(Color.ORANGE); break;     // extra points  
        }

        g.fillOval(x, y, width, height);
    }

    public void update() {
        y += speed;
    }
}