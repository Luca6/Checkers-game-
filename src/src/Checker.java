import java.awt.*;
import java.awt.Color;
import java.applet.*;

public class Checker {

    private int posX;
    private int posY;
    public Color color;

    public Checker(Color col, int x, int y){
        posX = x;
        posY = y;
        color = col;

    }

    public void paintChecker(Graphics g) {
        g.setColor(color);
        g.fillOval(posX*100+10, posY*100+10, 80, 80);
    }
}
