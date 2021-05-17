package com.ddona.tank.gui;


import com.ddona.tank.util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingsPanel extends JPanel implements KeyListener {
    private GameFrame mGameFrame;

    public SettingsPanel(GameFrame frame) {
        this.mGameFrame = frame;
        setLayout(null);
//        setBackground(Color.BLUE);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//        Font font = new Font("Arial", Font.BOLD, 100);
//        g2d.setFont(font);
//        RenderingHints hints = g2d.getRenderingHints();
//        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setRenderingHints(hints);
//        g2d.drawString("Hello world", 100, 100);
//
//        g2d.drawRect(200, 200, 100, 100);
//        g2d.fillRect(400, 400, 100, 100);
        Image image = new ImageIcon(getClass().getResource("/IMAGES/game_start.png")).getImage();
        g2d.drawImage(image, 0,0, Const.WIDTH_FRAME, Const.HEIGHT_FRAME,null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            this.mGameFrame.showGame();
        } else if (e.getKeyChar() == KeyEvent.VK_P) {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
