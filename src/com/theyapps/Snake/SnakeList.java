package com.theyapps.Snake;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by ryan on 3/11/14.
 */
public class SnakeList extends LinkedList<SnakeNode>{

    private Random rand = new Random();
    private int score;

    public SnakeList(){
        add(new SnakeNode(0,0));
        score = 0;
    }

    public void setDirection(SnakeDirection d){
        getFirst().setDirection(d);
    }

    public SnakeDirection getDirection(){
        return getFirst().getDirection();
    }

    public int getScore(){
        return score;
    }

    public void grow(){
        SnakeNode temp = new SnakeNode(getLast());
        temp.moveReverse();
        addLast(temp);
        score++;
    }

    public void tick(){
        SnakeNode temp = new SnakeNode(getFirst());
        boolean fail = false;
        temp.move();
        // Test for wall collision
        if(temp.getRow() < 0 ||
                temp.getRow() >= SnakeGlobals.BOARD_SIZE ||
                temp.getCol() < 0 ||
                temp.getCol() >= SnakeGlobals.BOARD_SIZE){
            fail = true;
        }
        // Test for snake body collision
        for(SnakeNode n:this){
            if(temp.intersects(n))
                fail = true;
        }
        for(SnakeNode n:MainFrame.getGamePanel().getFood()){
            if(temp.intersects(n)){
                grow();
                MainFrame.getGamePanel().getFood().remove();
                MainFrame.getGamePanel().getFood().add(new SnakeNode(
                        rand.nextInt(SnakeGlobals.BOARD_SIZE),
                        rand.nextInt(SnakeGlobals.BOARD_SIZE))
                );
            }
        }
        if(!fail){
            if(size() == SnakeGlobals.BOARD_SIZE * SnakeGlobals.BOARD_SIZE){
                SnakeGlobals.gameMode = SnakeGlobals.WIN;
                this.clear();
                this.add(new SnakeNode(0,0));
            }
            else{
                addFirst(temp);
                removeLast();
            }
        }
        else{
            SnakeGlobals.gameMode = SnakeGlobals.LOSE;
            this.clear();
            score = 0;
            this.add(new SnakeNode(0,0));
        }
    }
}
