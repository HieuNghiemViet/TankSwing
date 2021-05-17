package com.ddona.tank.manager;

import com.ddona.tank.model.MapItem;
import com.ddona.tank.util.Const;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class MapManager {
    private List<MapItem> arrMaps;
    private int level;

    public MapManager(int level) {
        this.level = level;
        arrMaps = new ArrayList<>();
        loadMap();
    }

    public void drawMap(Graphics2D g2d) {
        for (int i = 0; i < arrMaps.size(); i++) {
            arrMaps.get(i).draw(g2d);
        }
    }

    public void loadMap() {
        String path = "src/MAPS/map" + level;
        File file = new File(path);
        try {
            RandomAccessFile rd = new RandomAccessFile(file,"r");
            int row = 0;
            String line = rd.readLine();
            while (line != null) {
                for (int col = 0; col < line.length(); col++) {
                    int type = line.charAt(col) - '0';
                    arrMaps.add(new MapItem(col * Const.ITEM_SIZE,
                            row * Const.ITEM_SIZE,
                            Const.ITEM_SIZE,
                            Const.ITEM_SIZE,
                            type));
                }
                row++;
                line = rd.readLine();
            }
            rd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MapItem> getArrMaps() {
        return arrMaps;
    }

    public void setArrMaps(List<MapItem> arrMaps) {
        this.arrMaps = arrMaps;
    }


}
