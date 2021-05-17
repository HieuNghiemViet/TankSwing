package com.ddona.tank.model;

import com.ddona.tank.manager.BossManager;
import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.util.Const;

import java.util.Random;

public class Boss extends TankObject{
    private int id;
    private int level;
    private float speed;
    private int orient;
    private int hp;

    public Boss(int x, int y, int level) {
        super(x, y);
        this.width = Const.TANK_SIZE;
        this.height = Const.TANK_SIZE;
        this.level = level;
        this.hp = level;
        this.id = Const.BOSS_ID;
        this.speed = Const.STANDARD_SPEED / 2;
        this.orient = Const.DOWN_ORIENT;
        updateIcon();
    }


    private void updateIcon() {
        if (level == 1) {
            this.icon = ImageMgr.arrBoss1Images.get(orient);
        } else if (level == 2) {
            if (hp == 2) {
                this.icon = ImageMgr.arrBoss22Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss21Images.get(orient);
            }
        } else {
            if (hp == 3) {
                this.icon = ImageMgr.arrBoss33Images.get(orient);
            } else if (hp == 2) {
                this.icon = ImageMgr.arrBoss32Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss31Images.get(orient);
            }
        }
    }

    public void moveBoss(MapManager mapManager,TankPlayer tankPlayer, Bird bird, BossManager bossManager, boolean changeOrient) {
        if (changeOrient) {
            getNewOrient();
        }
        switch (this.orient) {
            case Const.UP_ORIENT:
                if (y > 0) {
                    y -= speed;
                    if (bossIntersectWithMapAndBird(mapManager, bird)) {
                        y += speed;
                        getNewOrient();
                    } else if (isIntersectWithPlayer(tankPlayer)) {
                        y += speed;
                        this.orient = Const.DOWN_ORIENT;
                    } else if (isIntersectWithBoss(bossManager)) {
                        y += speed;
                        getNewOrient();
                    }
                }
                break;
            case Const.DOWN_ORIENT:
                if (y < Const.MAP_SIZE - Const.TANK_SIZE) {
                    y += speed;
                    if (bossIntersectWithMapAndBird(mapManager, bird)) {
                        y -= speed;
                        getNewOrient();
                    } else if (isIntersectWithPlayer(tankPlayer)) {
                        y -= speed;
                        this.orient = Const.UP_ORIENT;
                    } else if (isIntersectWithBoss(bossManager)) {
                        y -= speed;
                        getNewOrient();
                    }
                }
                break;

            case Const.LEFT_ORIENT:
                if (x > 0) {
                    x -= speed;
                    if (bossIntersectWithMapAndBird(mapManager, bird)) {
                        x += speed;
                    } else if (isIntersectWithPlayer(tankPlayer)) {
                        x += speed;
                        this.orient = Const.RIGHT_ORIENT;
                    } else if (isIntersectWithBoss(bossManager)) {
                        x += speed;
                        getNewOrient();
                    }
                }
                break;

            case Const.RIGHT_ORIENT:
                if (x < Const.MAP_SIZE - Const.TANK_SIZE) {
                    x += speed;
                    if (bossIntersectWithMapAndBird(mapManager, bird)) {
                        x -= speed;
                    } else if (isIntersectWithPlayer(tankPlayer)) {
                        x -= speed;
                        this.orient = Const.LEFT_ORIENT;
                    } else if (isIntersectWithBoss(bossManager)) {
                        x -= speed;
                        getNewOrient();
                    }
                }
                break;
        }
    }

    private boolean isIntersectWithPlayer(TankPlayer tankPlayer) {
        return false;
    }

    private boolean isIntersectWithBoss(BossManager bossManager) {
        return false;
    }


    private void getNewOrient() {
        int newOrient;
        do {
            newOrient = new Random().nextInt(4);
        } while (this.orient == newOrient);
        this.orient = newOrient;

        if (level == 1) {
            this.icon = ImageMgr.arrBoss1Images.get(orient);
        } else if (level == 2) {
            if (hp == 2) {
                this.icon = ImageMgr.arrBoss22Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss21Images.get(orient);
            }
        } else {
            if (hp == 3) {
                this.icon = ImageMgr.arrBoss33Images.get(orient);
            } else if (hp == 2) {
                this.icon = ImageMgr.arrBoss32Images.get(orient);
            } else {
                this.icon = ImageMgr.arrBoss31Images.get(orient);
            }
        }

        setOrient(orient);
    }

    private boolean bossIntersectWithMapAndBird(MapManager mapManager, Bird bird) {
        for (int i = 0; i < mapManager.getArrMaps().size(); i++) {
            MapItem mapItem = mapManager.getArrMaps().get(i);
            if (getRect().intersects(mapItem.getRect()) && !(mapItem.isAllowTankPass())) {
                return true;
            }
            if (getRect().intersects(bird.getRect())) {
                return true;
            }
        }
        return false;
    }

    public Bullet fireBullet() {
        return new Bullet(Const.BOSS_ID,
                x + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2,
                y + (Const.TANK_SIZE - Const.BULLET_SIZE) / 2,
                this.orient);
    }

    public boolean beHit() {
        hp--;
        updateIcon();
        return hp == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getOrient() {
        return orient;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
