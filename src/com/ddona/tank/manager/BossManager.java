package com.ddona.tank.manager;

import com.ddona.tank.model.*;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossManager {
    private List<Boss> mBosses;
    private int bossCount;
    private MapManager mapManager;
    private Bird bird;
    private TankPlayer tankPlayer;
    private Random random;

    public BossManager(MapManager mapManager, TankPlayer tankPlayer, Bird bird) {
        this.mapManager = mapManager;
        this.tankPlayer = tankPlayer;
        this.bird = bird;
        mBosses = new ArrayList<>();
        bossCount = 24;
        this.random = new Random();
    }

    public boolean addMoreBosses() {
        if (mBosses.size() < 2 && bossCount > 0) {
            int level = bossCount > 12 ? 1 : bossCount > 3 ? 2 : 3; // if else
            for (int i = 0; i < 3; i++) {
                mBosses.add(new Boss(i * (Const.MAP_SIZE / 2 - Const.TANK_SIZE / 2),
                        0,
                        level));
            }
            bossCount -= 3;
            return true;
        }
        return false;
    }

    public void drawAllBosses(Graphics2D g2d) {
        for (int i = 0; i < mBosses.size(); i++) {
            mBosses.get(i).draw(g2d);
        }
    }

    public void moveAllBosses() {
        for (int i = 0; i < mBosses.size(); i++) {
            mBosses.get(i).moveBoss(mapManager, tankPlayer, bird,
                    this,
                    random.nextInt(100) > 96 );
        }
    }

    public void autoFireBullets(BulletManager bulletManager) {
        for (Boss mBoss : mBosses) {
            bulletManager.addBullet(mBoss.fireBullet());
        }
    }

    public List<Boss> getmBosses() {
        return mBosses;
    }

    public void setmBosses(List<Boss> mBosses) {
        this.mBosses = mBosses;
    }

    public int getBossCount() {
        return bossCount;
    }

    public void setBossCount(int bossCount) {
        this.bossCount = bossCount;
    }
}
