package com.theyapps.Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created by ryan on 3/11/14.
 */

/**
 * KNOWN BUGS
 * Sometimes trying to press switch to the next column or row can cause a false collision
 * and hence a game loss.
 */
public class MainFrame extends JFrame implements ActionListener, KeyListener{
    private static GamePanel gamePanel;
    private static Timer timer;
    private static Random rand = new Random();

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });
    }

    public static GamePanel getGamePanel(){
        return gamePanel;
    }

    public MainFrame(){
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        gamePanel.getFood().add(new SnakeNode(
                rand.nextInt(SnakeGlobals.BOARD_SIZE),
                rand.nextInt(SnakeGlobals.BOARD_SIZE))
        );
        initMenu();
        initUI();
        setContentPane(gamePanel);
        addKeyListener(this);
        timer = new Timer(SnakeGlobals.DELAY, this);
        timer.start();
    }

    private void initMenu(){}

    private void initUI(){
        setTitle("Snake");
        setSize((SnakeGlobals.BOARD_SIZE * SnakeGlobals.CELL_SIZE) + 20,
                (SnakeGlobals.BOARD_SIZE * SnakeGlobals.CELL_SIZE) + 50);

        //setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == timer){
            gamePanel.tick();
            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if(gamePanel.getSnake().getDirection() != SnakeDirection.RIGHT)
                    gamePanel.getSnake().setDirection(SnakeDirection.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if(gamePanel.getSnake().getDirection() != SnakeDirection.LEFT)
                    gamePanel.getSnake().setDirection(SnakeDirection.RIGHT);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if(gamePanel.getSnake().getDirection() != SnakeDirection.DOWN)
                    gamePanel.getSnake().setDirection(SnakeDirection.UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if(gamePanel.getSnake().getDirection() != SnakeDirection.UP)
                    gamePanel.getSnake().setDirection(SnakeDirection.DOWN);
                break;
            case KeyEvent.VK_G:
                gamePanel.getSnake().grow();
            break;
            case KeyEvent.VK_SPACE:
                switch(SnakeGlobals.gameMode){
                    case SnakeGlobals.PAUSE:
                    case SnakeGlobals.WIN:
                    case SnakeGlobals.LOSE:
                        SnakeGlobals.gameMode = SnakeGlobals.PLAY;
                    break;
                    case SnakeGlobals.PLAY:
                        SnakeGlobals.gameMode = SnakeGlobals.PAUSE;
                    break;
                }
            break;
        }
    }

    /* Unused Implements */
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
}
