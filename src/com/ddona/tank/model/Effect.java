package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;
import com.ddona.tank.util.Const;

import java.awt.*;

public class Effect extends TankObject{
    public int index;

    public Effect(int x, int y) {
        super(x, y);
        this.width = Const.TANK_SIZE;
        this.height = Const.TANK_SIZE;
        index = 0;
    }

    public boolean drawEffect(Graphics2D g2d) {
        if (index < ImageMgr.arrBumImages.size()) {
            this.icon = ImageMgr.arrBumImages.get(index);
            draw(g2d);
            index++;
            return true;
        }
        return false;
    }
}
