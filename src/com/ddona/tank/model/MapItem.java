package com.ddona.tank.model;

import com.ddona.tank.manager.ImageMgr;

public class MapItem extends TankObject {
    private int type;

    public MapItem(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
        this.icon = ImageMgr.arrItemsImages.get(type);
    }

    public boolean isAllowTankPass() { // cho xe tang di qua tuong
        return type == 0 || type == 3 || type == 5;
    }

    public boolean isAllowBulletPass() {
        return type == 0 || type == 3 || type == 5;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
