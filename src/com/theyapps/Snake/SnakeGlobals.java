package com.theyapps.Snake;

import java.awt.*;

/**
 * Created by ryan on 3/11/14.
 */
public class SnakeGlobals{
    final static public int CELL_SIZE = 10, CELL_BORDER = 5, BOARD_SIZE = 30, DELAY = 100;
    final static public Color
            SNAKE_COLOR = Color.RED,
            BACKGROUND_COLOR = Color.BLACK,
            BORDER_COLOR = Color.BLUE,
            FOOD_COLOR = Color.GREEN;

    final static public int PAUSE = 0, PLAY = 1, WIN = 2, LOSE = 3;

    static public int gameMode = PAUSE;
}
