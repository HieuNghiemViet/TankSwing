package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.util.Const;

public class Bullet extends TankObject {
    private int id;
    private int orient;
    private MapManager mapManager;
    private Bird bird;

    public Bullet(int id, int x, int y, int orient) {
        super(x, y);
        this.id = id;
        this.orient = orient;
        this.width = Const.BULLET_SIZE;
        this.height = Const.BULLET_SIZE;
        this.icon = ImageMgr.arrBulletImages.get(orient);
    }

    public boolean moveBullet() {
        switch (orient) {
            case Const.UP_ORIENT:
                if (y > 0) {
                    this.y -= Const.STANDARD_SPEED;
                    return true;
                } else {
                    return false;
                }
            case Const.DOWN_ORIENT:
                if (y < Const.MAP_SIZE) {
                    this.y += Const.STANDARD_SPEED;
                    return true;
                } else {
                    return false;
                }

            case Const.RIGHT_ORIENT:
                if (x < Const.MAP_SIZE) {
                    this.x += Const.STANDARD_SPEED;
                    return true;
                } else {
                    return false;
                }

            case Const.LEFT_ORIENT:
                if (x > 0) {
                    this.x -= Const.STANDARD_SPEED;
                    return true;
                } else {
                    return false;
                }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }
}
