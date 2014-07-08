package com.theyapps.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by ryan on 3/11/14.
 */
public class GamePanel extends JPanel{
    private SnakeList snake;
    private LinkedList<SnakeNode> food;

    public GamePanel(){
        super();
        setBackground(SnakeGlobals.BACKGROUND_COLOR);
        snake = new SnakeList();
        food = new LinkedList<SnakeNode>();
    }

    public SnakeList getSnake(){
        return snake;
    }
    public LinkedList<SnakeNode> getFood(){
        return food;
    }

    public void tick(){
        if(SnakeGlobals.gameMode == SnakeGlobals.PLAY)
            snake.tick();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        // Clear Screen
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        // Draw Border
        g2d.setColor(SnakeGlobals.BORDER_COLOR);
        g2d.drawRect(SnakeGlobals.CELL_BORDER, SnakeGlobals.CELL_BORDER,
                SnakeGlobals.BOARD_SIZE * SnakeGlobals.CELL_SIZE,
                SnakeGlobals.BOARD_SIZE * SnakeGlobals.CELL_SIZE);

        // Draw Food
        g2d.setColor(SnakeGlobals.FOOD_COLOR);
        for(SnakeNode s:food){
            g2d.fill(s);
        }
        // Draw Snake
        g2d.setColor(SnakeGlobals.SNAKE_COLOR);
        for(SnakeNode s:snake){
            g2d.fill(s);
        }

        if(SnakeGlobals.gameMode != SnakeGlobals.PLAY){
            String msg = new String();
            switch (SnakeGlobals.gameMode){
                case SnakeGlobals.LOSE:
                    msg = "You Lose!!";
                break;
                case SnakeGlobals.WIN:
                    msg = "You Win!!";
                break;
                case SnakeGlobals.PAUSE:
                    msg = "PAUSED!!";
                break;
            }
            g2d.setColor(Color.WHITE);
            g2d.drawString(msg,
                    (SnakeGlobals.BOARD_SIZE * SnakeGlobals.CELL_SIZE/2) - 25,
                    SnakeGlobals.BOARD_SIZE * SnakeGlobals.CELL_SIZE/2);
        }
        // draw score
        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + snake.getScore(),
                SnakeGlobals.CELL_SIZE,
                SnakeGlobals.BOARD_SIZE * SnakeGlobals.CELL_SIZE + SnakeGlobals.CELL_SIZE);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.paint(g);
    }
}
