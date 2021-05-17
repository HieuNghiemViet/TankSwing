package com.ddona.tank.manager;

import com.ddona.tank.model.*;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    private List<Bullet> mBullets;
    private MapManager mapManager;
    private BossManager bossManager;
    private TankPlayer tankPlayer;
    private Bird bird;

    public BulletManager(MapManager map, BossManager bossManager, TankPlayer tankPlayer, Bird bird) {
        this.tankPlayer = tankPlayer;
        this.mapManager = map;
        this.bossManager = bossManager;
        this.bird = bird;
        mBullets = new ArrayList<>();
    }

    public void moveAllBullets() {
        try {
            for (int i = 0; i < mBullets.size(); i++) {
                if (!mBullets.get(i).moveBullet()) {
                    mBullets.remove(i);
                    continue;
                }
                if (checkIntersectWithMap(mBullets.get(i))) {
                    continue;
                }
                if (checkIntersectWithTank(mBullets.get(i))) {
                    continue;
                }
                if (checkWithBird(mBullets.get(i))) {
                    continue;
                }
                if (checkWithBullet(mBullets.get(i))) {
                    continue;
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private boolean checkIntersectWithTank(Bullet bullet) {
        if (bullet.getId() == Const.TANK_ID) {
            for (int i = 0; i < bossManager.getmBosses().size(); i++) {
                Boss boss = bossManager.getmBosses().get(i);
                if (bullet.getRect().intersects(boss.getRect())) {
                    mBullets.remove(bullet);
                    if(boss.beHit()) { // bi ban
                        bossManager.getmBosses().remove(boss);
                        SoundMgr.play(SoundMgr.killBoss);
                        EffectMgr.addEffect(new Effect(boss.getX(),
                                boss.getY()));
                    }
                }
            }
        } else {
            if (bullet.getRect().intersects(tankPlayer.getRect())) {
                  tankPlayer.beHit();
                  mBullets.remove(bullet);
                  EffectMgr.addEffect(new Effect(tankPlayer.getX(), tankPlayer.getY()));
            }
        }
        return false;
    }

    private boolean checkIntersectWithMap(Bullet bullet) {
        boolean inIntersect = false;
        for (int i = 0; i< mapManager.getArrMaps().size(); i++) {
            MapItem item = mapManager.getArrMaps().get(i);
            if (item.getRect().intersects(bullet.getRect()) && item.isAllowBulletPass() == false) {
                mapManager.getArrMaps().remove(item);
                mBullets.remove(bullet);
                inIntersect = true;
            }
        }
        return inIntersect;
    }

    private boolean checkWithBird(Bullet bullet) {
        if (bullet.getRect().intersects(bird.getRect())) {
            bird.setAlive(false);
            mBullets.remove(bullet);
            return true;
        }
        return false;
    }

    private boolean checkWithBullet(Bullet bullet) {
        if (bullet.getId() == Const.BOSS_ID) {
            for (int i = 0; i < mBullets.size(); i++) {
                if (mBullets.get(i).getId() == Const.TANK_ID && bullet.getRect().intersects(mBullets.get(i).getRect())) {
                    mBullets.remove(i);
                    mBullets.remove(bullet);
                    return true;
                }
            }
        }
        return false;
    }

    public void drawAllBullets(Graphics2D g2d) {
        for (int i = 0; i < mBullets.size(); i++) {
            mBullets.get(i).draw(g2d);
        }
    }

    // khi ban nhieu dan thi list tang vo tan
    // ki thuat object pooling de dung lai cac vien dan da ban di
    public void addBullet(Bullet bullet) {
        mBullets.add(bullet);
    }

    public List<Bullet> getBullets() {
        return mBullets;
    }


}
