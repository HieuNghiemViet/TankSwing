package com.ddona.tank.manager;

import com.ddona.tank.model.Effect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EffectMgr {
    private static List<Effect> arrEffects;

    public EffectMgr() {
        arrEffects = new ArrayList<>();
    }

    public static void addEffect(Effect effect) {
        arrEffects.add(effect);
    }

    public void drawEffect(Graphics2D g2d) {
        for (int i = arrEffects.size() - 1; i >= 0; i--) {
            if (!arrEffects.get(i).drawEffect(g2d))
                arrEffects.remove(i);
        }
    }
}
