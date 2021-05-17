package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

public abstract class Tank extends TankObject {
    protected int id;
    protected int orient;
    protected float speed;

    public Tank(int id) {
        this.id = id;
        this.speed = Const.STANDARD_SPEED;
        orient = Const.UP_ORIENT;
        x = 8 * Const.ITEM_SIZE;
        y = 24 * Const.ITEM_SIZE;
        width = Const.TANK_SIZE;
        height = Const.TANK_SIZE;
        icon = ImageMgr.arrPlayerImages.get(orient);
    }

    public abstract boolean beHit();

    public abstract void updateIcon();

    public Bullet fireBullet() {
        return new Bullet(id, x + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2,
                y + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2, this.orient);
    }

    public int getId() {
        return id;
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
        x = (x + Const.ITEM_SIZE / 2) / Const.ITEM_SIZE * Const.ITEM_SIZE;
        y = (y + Const.ITEM_SIZE / 2) / Const.ITEM_SIZE * Const.ITEM_SIZE;
        updateIcon();
    }
}
