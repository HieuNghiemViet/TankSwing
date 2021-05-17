package com.ddona.main;

import com.ddona.tank.gui.GameFrame;
import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.manager.SoundMgr;
import com.ddona.tank.util.Const;

public class Main {
    public static void main(String[] args) {
        new Const();
        new SoundMgr();
        new ImageMgr();
        new GameFrame();
    }
}
