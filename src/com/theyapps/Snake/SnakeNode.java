package com.theyapps.Snake;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ryan on 3/11/14.
 */
public class SnakeNode extends Rectangle{
    private int m_row, m_col;
    private SnakeDirection m_direction;

    public SnakeNode(int row, int col){
        super();
        m_row = row;
        m_col = col;
        m_direction = SnakeDirection.RIGHT;
        setBounds();
    }

    public SnakeNode(SnakeNode orig){
        super();
        this.m_row = orig.m_row;
        this.m_col = orig.m_col;
        this.m_direction = orig.m_direction;
        setBounds();
    }

    public void setDirection(SnakeDirection d){
        if(SnakeGlobals.gameMode == SnakeGlobals.PLAY)
            m_direction = d;
    }
    public SnakeDirection getDirection(){
        return m_direction;
    }
    public int getRow(){
        return m_row;
    }
    public int getCol(){
        return m_col;
    }

    public void setBounds(){
        setBounds(m_row * SnakeGlobals.CELL_SIZE + SnakeGlobals.CELL_BORDER,
                m_col * SnakeGlobals.CELL_SIZE + SnakeGlobals.CELL_BORDER,
                SnakeGlobals.CELL_SIZE,
                SnakeGlobals.CELL_SIZE);
    }

    public void move(){
        switch (m_direction){
            case LEFT:
                m_row--;
            break;
            case RIGHT:
                m_row++;
            break;
            case UP:
                m_col--;
            break;
            case DOWN:
                m_col++;
            break;
        }
        setBounds();
    }

    public void moveReverse(){
        switch (m_direction){
            case LEFT:
                m_row++;
                break;
            case RIGHT:
                m_row--;
                break;
            case UP:
                m_col++;
                break;
            case DOWN:
                m_col--;
                break;
        }
        setBounds();
    }
}
