package com.ddona.tank.manager;

import com.ddona.tank.util.Const;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImageMgr {
    public static ArrayList<Image> arrItemsImages;
    public static ArrayList<Image> arrPlayerImages;
    public static ArrayList<Image> arrBoss1Images;
    public static ArrayList<Image> arrBoss21Images;
    public static ArrayList<Image> arrBoss22Images;
    public static ArrayList<Image> arrBoss31Images;
    public static ArrayList<Image> arrBoss32Images;
    public static ArrayList<Image> arrBoss33Images;
    public static ArrayList<Image> arrBulletImages;
    public static ArrayList<Image> arrGiftImages;
    public static ArrayList<Image> arrBumImages;
    public static ArrayList<Image> arrNumImages;
    public static ArrayList<Image> arrBirdImages;
    public static Image imageStart;
    public static Image imageIconBoss;
    public static Image imageLeft;
    public static Image imageRight;

    public ImageMgr() {
        arrItemsImages = getImage("unit", Const.ITEM_SIZE, Const.ITEM_SIZE, 6);
        arrPlayerImages = getImage("player", Const.TANK_SIZE, Const.TANK_SIZE, 4);
        arrBoss1Images = getImage("boss1", Const.TANK_SIZE, Const.TANK_SIZE, 4);
        arrBoss21Images = getImage("boss2", Const.TANK_SIZE, Const.TANK_SIZE, 4);
        arrBoss22Images = getImage("boss22", Const.TANK_SIZE, Const.TANK_SIZE, 4);
        arrBoss31Images = getImage("boss31", Const.TANK_SIZE, Const.TANK_SIZE, 4);
        arrBoss32Images = getImage("boss32", Const.TANK_SIZE, Const.TANK_SIZE, 4);
        arrBoss33Images = getImage("boss33", Const.TANK_SIZE, Const.TANK_SIZE, 4);
        arrBulletImages = getImage("bullet", Const.BULLET_SIZE, Const.BULLET_SIZE, 4);
        arrBumImages = getImage("bum", Const.TANK_SIZE * 2, Const.TANK_SIZE * 2, 9);
        arrGiftImages = getImage("gift", Const.TANK_SIZE, Const.TANK_SIZE, 2);
        arrNumImages = getImage("num_level", 16, 16, 10);
        arrBirdImages = getImage("bird", Const.TANK_SIZE, Const.TANK_SIZE, 2);
        imageStart = new ImageIcon(getClass().getResource("/IMAGES/game_start.png")).getImage();
        imageIconBoss = new ImageIcon(getClass().getResource("/IMAGES/icon_boss.png")).getImage();
        imageLeft = new ImageIcon(getClass().getResource("/IMAGES/left.png")).getImage();
        imageRight = new ImageIcon(getClass().getResource("/IMAGES/right.png")).getImage();
    }

    private ArrayList<Image> getImage(String imgName, int width, int height, int number) {
        ArrayList<Image> arrImages = new ArrayList<>();
        try {
            BufferedImage pImage = ImageIO.read(getClass().getResourceAsStream("/IMAGES/" + imgName + ".png"));
            BufferedImage image;
            for (int i = 0; i < number; i++) {
                image = pImage.getSubimage(0, i * height, width, height);
                arrImages.add(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrImages;
    }
}
