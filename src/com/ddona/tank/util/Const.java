package com.ddona.tank.util;

import java.awt.*;

public class Const {
    public static final int MAP_SIZE = 728;
    public static final int LEFT_RIGHT_SIZE = 250;
    public static int WIDTH_FRAME;
    public static int HEIGHT_FRAME;
    public static int PADDING_LEFT;
    public static int PADDING_TOP;
    public static int RIGHT_START_X;
    public static final int NUM_ITEM = 26;
    public static final int ITEM_SIZE = 28;
    public static final int TANK_SIZE = 56;
    public static final int BULLET_SIZE = 16;
    public static final float STANDARD_SPEED = 4;
    public static final int UP_ORIENT = 0;
    public static final int DOWN_ORIENT = 1;
    public static final int LEFT_ORIENT = 2;
    public static final int RIGHT_ORIENT = 3;
    public static final int TANK_ID = 1;
    public static final int BOSS_ID = -1;

    public Const() {
        WIDTH_FRAME = Toolkit.getDefaultToolkit().getScreenSize().width;
        HEIGHT_FRAME = Toolkit.getDefaultToolkit().getScreenSize().height;
        PADDING_TOP = (HEIGHT_FRAME - MAP_SIZE) / 2;
        PADDING_LEFT = (WIDTH_FRAME - MAP_SIZE - 2 * LEFT_RIGHT_SIZE) / 4;
        RIGHT_START_X = WIDTH_FRAME - PADDING_LEFT - LEFT_RIGHT_SIZE;
    }
}
