package com.ddona.tank.model;

import com.ddona.tank.manager.BossManager;
import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.manager.SoundMgr;
import com.ddona.tank.util.Const;

import java.awt.*;


public class TankPlayer extends TankObject {
    private int id;
    private int orient;
    private float speed;
    private int liveCount;
    private boolean canFire;
    private MapManager mapManager;
    private Bird bird;
    private BossManager bossManager;

    public TankPlayer() {
        id = Const.TANK_ID;
        orient = Const.UP_ORIENT;
        liveCount = 3;
        speed = Const.STANDARD_SPEED;
        icon = ImageMgr.arrPlayerImages.get(orient);
        x = 9 * Const.ITEM_SIZE;
        y = 24 * Const.ITEM_SIZE;
        width = Const.TANK_SIZE;
        height = Const.TANK_SIZE;
        canFire = true;
    }

    public void setReference(MapManager mapManager, Bird bird, BossManager bossManager) {
        this.mapManager = mapManager;
        this.bird = bird;
        this.bossManager = bossManager;
    }

    public void moveTank(int orient) {
        if (this.orient != orient) {
            // xu li tank de di chuyen hon
            x = (x + Const.ITEM_SIZE / 2) / Const.ITEM_SIZE * Const.ITEM_SIZE;
            y = (y + Const.ITEM_SIZE / 2) / Const.ITEM_SIZE * Const.ITEM_SIZE;
            this.orient = orient;
            this.icon = ImageMgr.arrPlayerImages.get(orient);
        }
        switch (orient) {
            case Const.UP_ORIENT:
                if (y > 0) {
                    y -= speed;
                    if (checkIntersects()) {
                        y += speed;
                    }
                }
                break;
            case Const.DOWN_ORIENT:
                if (y < Const.MAP_SIZE - Const.TANK_SIZE) {
                    y += speed;
                    if (checkIntersects()) {
                        y -= speed;
                    }
                }
                break;
            case Const.RIGHT_ORIENT:
                if (x < Const.MAP_SIZE - Const.TANK_SIZE) {
                    x += speed;
                    if (checkIntersects()) {
                        x -= speed;
                    }
                }
                break;
            case Const.LEFT_ORIENT:
                if (x > 0) {
                    x -= speed;
                    if (checkIntersects()) {
                        x += speed;
                    }
                }
                break;
        }
    }

    private boolean checkIntersects() {
        int sizeMap = mapManager.getArrMaps().size();
        for (int i = 0; i < sizeMap; i++) {
            if (getRect().intersects(mapManager.getArrMaps().get(i).getRect()) &&
                    !mapManager.getArrMaps().get(i).isAllowTankPass()) {
                return true;
            }
        }

//        int sizeBoss = bossManager.getmBosses().size();
//        Rectangle rectBoss;
//        for (int i = 0; i < sizeBoss; i++) {
//            rectBoss = bossManager.getmBosses().get(i).getRect();
//            if (getRect().intersects(rectBoss)) {
//                return true;
//            }
//        }

        if (getRect().intersects(bird.getRect())) {
            return true;
        }
        return false;
    }

    public boolean beHit() {
        SoundMgr.play(SoundMgr.newTank);
        liveCount--;
        orient = Const.UP_ORIENT;
        x = 9 * Const.ITEM_SIZE;
        y = 24 * Const.ITEM_SIZE;
        icon = ImageMgr.arrPlayerImages.get(orient);
        return true;
    }


    public Bullet fireBullet() { // vi tri dan ban di
        return new Bullet(Const.TANK_ID,
                x + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2,
                y + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2,
                orient);
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

}
