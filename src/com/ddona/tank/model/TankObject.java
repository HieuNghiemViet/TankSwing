package com.ddona.tank.model;

import java.awt.*;

public class TankObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image icon;

    public TankObject() {
    }

    public TankObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TankObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(icon, x, y, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

}
