package com.ddona.tank.gui;

import com.ddona.tank.manager.BossManager;
import com.ddona.tank.manager.BulletManager;
import com.ddona.tank.manager.EffectMgr;
import com.ddona.tank.manager.MapManager;
import com.ddona.tank.model.Bird;
import com.ddona.tank.model.Boss;
import com.ddona.tank.model.Bullet;
import com.ddona.tank.model.TankPlayer;
import com.ddona.tank.util.Const;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.invoke.SwitchPoint;
import java.util.BitSet;

public class MapPanel extends JPanel implements KeyListener, Runnable {
    private MapManager mapManager;
    private Bird bird;
    private TankPlayer tankPlayer;
    private Boss boss;
    private BulletManager mBulletManager;
    private BossManager mBossManager;
    private boolean isRunning = false;
    private final BitSet mBitSet = new BitSet(256);
    private long count;
    private MainPanel mainPanel;
    private EffectMgr mEffectMgr;

    public MapPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setBounds((Const.WIDTH_FRAME - Const.MAP_SIZE) / 2,
                Const.PADDING_TOP,
                Const.MAP_SIZE,
                Const.MAP_SIZE);
        setBackground(Color.black);
        initComponents();
        addKeyListener(this);
        setFocusable(true);
        setLayout(null);

    }

    private void initComponents() {
        mBitSet.clear();
        mapManager = new MapManager(1);
        //isRunning = true;
        bird = new Bird(12 * Const.ITEM_SIZE,
                24 * Const.ITEM_SIZE,
                Const.TANK_SIZE,
                Const.TANK_SIZE);
        tankPlayer = new TankPlayer();
        mEffectMgr = new EffectMgr();
        tankPlayer.setReference(mapManager, bird, mBossManager);
        mBossManager = new BossManager(mapManager, tankPlayer, bird);
        mBulletManager = new BulletManager(mapManager, mBossManager, tankPlayer, bird);
        new Thread(this).start();
    }

    public void startGame() {
        isRunning = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        mapManager.drawMap(g2d);
        bird.draw(g2d);
        mBulletManager.drawAllBullets(g2d);
        tankPlayer.draw(g2d);
        mBossManager.drawAllBosses(g2d);
        mEffectMgr.drawEffect(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            this.isRunning = !isRunning;
        }
        mBitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mBitSet.clear(e.getKeyCode());
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning == true && bird.isAlive()) {
                count++;
                if (count % 80 == 0) {
                    tankPlayer.setCanFire(true);
                }
                //
                if (count % 100 == 0) {
                    mBossManager.autoFireBullets(mBulletManager);
                }
                //
                if (count == 1000000000) {
                    count = 0;
                }
                if (mBossManager.addMoreBosses()) {
                    mainPanel.updateBossCount();
                    repaint();
                }
                mBulletManager.moveAllBullets();
                mBossManager.moveAllBosses();
            }
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catchEvent();
            repaint();
        }
    }

    private void catchEvent() {
        if (isRunning == false) {
            return;
        }

        if (mBitSet.get(KeyEvent.VK_UP)) {
            tankPlayer.moveTank(Const.UP_ORIENT);
        } else if (mBitSet.get(KeyEvent.VK_DOWN)) {
            tankPlayer.moveTank(Const.DOWN_ORIENT);
        } else if (mBitSet.get(KeyEvent.VK_LEFT)) {
            tankPlayer.moveTank(Const.LEFT_ORIENT);
        } else if (mBitSet.get(KeyEvent.VK_RIGHT)) {
            tankPlayer.moveTank(Const.RIGHT_ORIENT);
        }

        if (mBitSet.get(KeyEvent.VK_SPACE)) {
            if (tankPlayer.isCanFire()) {
                mBulletManager.addBullet(tankPlayer.fireBullet());
                tankPlayer.setCanFire(false);
            }
        }
    }
}
