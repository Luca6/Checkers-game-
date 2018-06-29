import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Panel extends JPanel{

    private boolean first = true;
    private boolean firstClickB = true;
    private boolean firstClickR = true;
    private int[][] checkers = new int[8][8];
    private int turn=0;
    private int fx;
    private int fy;
    private Checker[][] checkArray = new Checker[8][8];

    public Panel(){

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                sort(e.getX(), e.getY());

            }
        });

    }

    public void sort(int x, int y){
        int xpos = 0;
        int ypos = 0;

        for (int i=0; i<9; i++){
            if (x<i*100){
                xpos=i-1;
                break;
            }
        }

        for (int i=0; i<9; i++){
            if (y<i*100){
                ypos=i-1;
                break;
            }
        }

        System.out.println(xpos + " " + ypos);


        if (firstClickR == false){
            moveR(xpos, ypos);
            checkWin();
            repaint();
            turn=0;
            //checkKing();
        }

        if (firstClickB == false){
            moveB(xpos, ypos);
            checkWin();
            repaint();
            turn=1;
            //checkKing();
        }

        if (checkers[xpos][ypos]==1 && firstClickB == true  && turn==0){
            firstClickB = false;
            fx = xpos;
            fy = ypos;

        }

        else if (checkers[xpos][ypos]==2 && firstClickR == true  && turn==1){
            firstClickR = false;
            fx = xpos;
            fy = ypos;
        }



    }

    public void checkWin(){

        boolean winB = true;
        boolean winR = true;
        for (int n=0; n<8; n++){
            for (int m=0; m<8; m++){
                if (checkers[m][n]==2){
                    winB = false;
                }
                else if (checkers[m][n]==1){
                    winR = false;
                }
            }
        }

        if (winB){
            System.out.println("YOU WIN");
        }
        if (winR){
            System.out.println("YOU LOST");
        }
    }

    public void moveB(int xpos, int ypos){
        //TO THE RIGHT
        if ((fx == xpos+1) && (fy==ypos-1)){
            //SIMPLE MOVE
            if (checkers[xpos][ypos]==0) {
                checkers[xpos][ypos] = 1;
                checkers[fx][fy] = 0;
                checkArray[xpos][ypos].color = Color.BLUE;

            }
            //STEAL PIECE
            else if (checkers[xpos][ypos]==2){
                if (checkers[xpos-1][ypos+1]==0){
                    checkers[xpos-1][ypos+1]=1;
                    checkers[xpos][ypos]=0;
                    checkers[fx][fy] = 0;
                    checkArray[xpos-1][ypos+1].color = Color.BLUE;
                }
            }
            firstClickB = true;
        }
        //TO THE LEFT
        else if ((fx == xpos-1) && (fy==ypos-1)){
            if (checkers[xpos][ypos]==0) {

                checkers[fx][fy] = 0;
                checkArray[xpos][ypos].color = Color.BLUE;
                checkers[xpos][ypos] = 1;

            }
            else if (checkers[xpos][ypos]==2){
                if (checkers[xpos+1][ypos+1]==0){
                    checkers[xpos+1][ypos+1]=1;
                    checkers[xpos][ypos]=0;
                    checkers[fx][fy] = 0;
                    checkArray[xpos+1][ypos+1].color = Color.BLUE;
                }
            }
            firstClickB = true;


        }
        repaint();
    }

    public void moveR(int xpos, int ypos){
        //TO THE LEFT
        if ((fx == xpos+1) && (fy==ypos+1)){

            //SIMPLE MOVE
            if (checkers[xpos][ypos]==0) {
                checkers[xpos][ypos] = 2;
                checkers[fx][fy] = 0;
                checkArray[xpos][ypos].color = Color.RED;

            }
            //STEAL PIECE
            else if (checkers[xpos][ypos]==1){
                    if (checkers[xpos-1][ypos-1]==0){
                    checkers[xpos-1][ypos-1]=2;
                    checkers[xpos][ypos]=0;
                    checkers[fx][fy] = 0;
                    checkArray[xpos-1][ypos-1].color = Color.RED;
                }
            }
            firstClickR = true;
        }
        //TO THE RIGHT
        else if ((fx == xpos-1) && (fy==ypos+1)){

            if (checkers[xpos][ypos]==0) {
                checkers[xpos][ypos] = 2;
                checkers[fx][fy] = 0;
                checkArray[xpos][ypos].color = Color.RED;
            }

            else if (checkers[xpos][ypos]==1){
                if (checkers[xpos+1][ypos-1]==0){
                    checkers[xpos+1][ypos-1]=2;
                    checkers[xpos][ypos]=0;
                    checkers[fx][fy] = 0;
                    checkArray[xpos+1][ypos-1].color = Color.RED;
                }
            }
            firstClickR = true;
        }
    }

    public void paint (Graphics g) {

        for (int x = 0; x < 800; x += 200) {
            for (int y = 0; y < 800; y += 200) {
                g.fillRect(x, y, 100, 100);
            }
        }

        for (int x = 100; x < 800; x += 200) {
            for (int y = 100; y < 800; y += 200) {
                g.fillRect(x, y, 100, 100);
            }
        }

        if (first == true) {

            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    checkArray[x][y] = new Checker(null, x, y);
                }
            }

            for (int y = 0; y < 3; y++) {
                int x = 0;
                if (y == 1)
                    x = 1;
                for (; x < 8; x += 2) {
                    checkers[x][y] = 1;
                    checkArray[x][y].color = Color.BLUE;
                }
            }

            for (int y = 5; y < 8; y++) {
                int x = 0;
                if (y == 5 || y == 7)
                    x = 1;
                for (; x < 8; x += 2) {
                    checkers[x][y] = 2;
                    checkArray[x][y].color = Color.RED;
                }
            }

            checkers[1][3]=2;
            checkArray[1][3].color = Color.RED;
            first = false;
        }

        for (int y=0; y<8; y++){
            for (int x = 0;x<8; x++){
                if (checkers[x][y]==1||checkers[x][y]==2)
                    checkArray[x][y].paintChecker(g);
            }
        }
    }
}
