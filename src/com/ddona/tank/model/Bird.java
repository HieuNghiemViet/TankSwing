package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;

public class Bird extends TankObject {
    private boolean isAlive;

    public Bird() {
        isAlive = true;
        icon = ImageMgr.arrBirdImages.get(0);
    }

    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
        isAlive = true;
        icon = ImageMgr.arrBirdImages.get(0);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
        this.icon = ImageMgr.arrBirdImages.get(1);
    }
}
