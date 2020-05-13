
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class gPlay extends JPanel implements KeyListener, ActionListener {
    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private ImageIcon rHeadImgObj;
    private ImageIcon lHeadImgObj;
    private ImageIcon uHeadImgObj;
    private ImageIcon dHeadImgObj;
    private ImageIcon snakeImgObj;
    private ImageIcon titleImgObj;

    private int snakeLength = 3;
    private Timer timerObj;
    private int delay = 100;
    private int score = 0;
    private int moves = 0;
    private int[] enemyXPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650};
    private int[] enemyYPos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600};
    private ImageIcon enemyImgObj;
    private Random randomObj = new Random();
    private int xPos = randomObj.nextInt(25);
    private int yPos = randomObj.nextInt(21);

    public gPlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timerObj = new Timer(delay, this);
        timerObj.start();
    }

    public void paint(Graphics gObj) {
        if (moves == 0) {
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;
            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;
        }
        //title img border
        gObj.setColor(Color.white);
        gObj.drawRect(24, 10, 851, 55);
        //title img
        titleImgObj = new ImageIcon("snaketitle.jpg");
        titleImgObj.paintIcon(this, gObj, 25, 11);
        //border
        gObj.setColor(Color.white);
        gObj.drawRect(24, 74, 851, 577);
        //bg
        gObj.setColor(Color.black);
        gObj.fillRect(25, 75, 850, 575);
        //draw snake
        rHeadImgObj = new ImageIcon("rightmouth.png");
        rHeadImgObj.paintIcon(this, gObj, snakeXlength[0], snakeYlength[0]);

        for (int i = 0; i < snakeLength; i++) {
            if (i == 0 && right) {
                rHeadImgObj = new ImageIcon("rightmouth.png");
                rHeadImgObj.paintIcon(this, gObj, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && left) {
                lHeadImgObj = new ImageIcon("leftmouth.png");
                lHeadImgObj.paintIcon(this, gObj, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && down) {
                dHeadImgObj = new ImageIcon("downmouth.png");
                dHeadImgObj.paintIcon(this, gObj, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && up) {
                uHeadImgObj = new ImageIcon("upmouth.png");
                uHeadImgObj.paintIcon(this, gObj, snakeXlength[i], snakeYlength[i]);
            }
            if (i != 0) {
                snakeImgObj = new ImageIcon("snakeimage.png");
                snakeImgObj.paintIcon(this, gObj, snakeXlength[i], snakeYlength[i]);
            }
        }
        //enemy
        enemyImgObj = new ImageIcon("enemy.png");
        if ((enemyXPos[xPos] == snakeXlength[0] && enemyYPos[yPos] == snakeYlength[0])) {
            snakeLength++;
            score++;
            xPos = randomObj.nextInt(25);
            yPos = randomObj.nextInt(21);
        }
        enemyImgObj.paintIcon(this, gObj, enemyXPos[xPos], enemyYPos[yPos]);
        //score
        gObj.setColor(Color.white);
        gObj.setFont(new Font("arial",Font.PLAIN,14));
        gObj.drawString("Score : "+ score,780,30);
        gObj.drawString("Length : "+ snakeLength,780,50);

        //game over
        for(int b=1;b<snakeLength;b++){
            if (snakeXlength[b]==snakeXlength[0] && snakeYlength[b]==snakeYlength[0]){
                right=false;
                left=false;
                up=false;
                down=false;
                gObj.setColor(Color.white);
                gObj.setFont(new Font("arial",Font.BOLD,50));
                gObj.drawString("Game Over",300,300);
                gObj.setFont(new Font("arial",Font.BOLD,20));
                gObj.drawString("Press Space To Restart",320,340);
                timerObj.stop();
            }
        }

        gObj.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timerObj.start();
        if (right) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeYlength[i + 1] = snakeYlength[i];
            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeXlength[i] = snakeXlength[i] + 25;
                } else {
                    snakeXlength[i] = snakeXlength[i - 1];
                }
                if (snakeXlength[0] > 850) {
                    snakeXlength[0] = 25;
                }
            }
            repaint();
        }
        if (left) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeYlength[i + 1] = snakeYlength[i];
            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeXlength[i] = snakeXlength[i] - 25;
                } else {
                    snakeXlength[i] = snakeXlength[i - 1];
                }
                if (snakeXlength[i] < 25) {
                    snakeXlength[i] = 850;
                }
            }
            repaint();
        }
        if (down) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeXlength[i + 1] = snakeXlength[i];
            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeYlength[i] = snakeYlength[i] + 25;
                } else {
                    snakeYlength[i] = snakeYlength[i - 1];
                }
                if (snakeYlength[i] > 625) {
                    snakeYlength[i] = 75;
                }
            }
            repaint();
        }
        if (up) {
            for (int i = snakeLength - 1; i >= 0; i--) {
                snakeXlength[i + 1] = snakeXlength[i];
            }
            for (int i = snakeLength; i >= 0; i--) {
                if (i == 0) {
                    snakeYlength[i] = snakeYlength[i] - 25;
                } else {
                    snakeYlength[i] = snakeYlength[i - 1];
                }
                if (snakeYlength[i] < 75) {
                    snakeYlength[i] = 625;
                }
            }
            repaint();
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            right = true;
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            left = true;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            right = false;
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            timerObj.start();
            moves=0;
            score=0;
            snakeLength=3;
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
