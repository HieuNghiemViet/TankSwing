package com.ddona.tank.gui;

import com.ddona.tank.manager.SoundMgr;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private SettingsPanel mSettingsPanel;
    private MainPanel mMainPanel;

    public GameFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full size man
        setUndecorated(true);
        setLayout(new CardLayout());
        initComponents();
        setVisible(true);
        SoundMgr.play(SoundMgr.startGame);
    }

    private void initComponents() {
        mMainPanel = new MainPanel(this);
        mSettingsPanel = new SettingsPanel(this);
        add(mSettingsPanel);
        add(mMainPanel);
    }

    public void showGame() {
        mMainPanel.startGame();
        mMainPanel.setVisible(true);
        mSettingsPanel.setVisible(false);
    }

    public void showSettings() {
        mMainPanel.setVisible(false);
        mSettingsPanel.setVisible(true);
    }
}
