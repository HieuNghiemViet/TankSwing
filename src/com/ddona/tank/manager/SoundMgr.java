package com.ddona.tank.manager;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundMgr {
    public static AudioClip startGame;
    public static AudioClip move;
    public static AudioClip shoot;
    public static AudioClip killBoss;
    public static AudioClip killBullet;
    public static AudioClip newTank;
    public static AudioClip complete;
    public static boolean canPlay = true;

    public SoundMgr() {
        startGame = Applet.newAudioClip(getClass().getResource("/SOUNDS/enter_game.wav"));
        move = Applet.newAudioClip(getClass().getResource("/SOUNDS/move.wav"));
        shoot = Applet.newAudioClip(getClass().getResource("/SOUNDS/shoot.wav"));
        killBoss = Applet.newAudioClip(getClass().getResource("/SOUNDS/bum_tank.wav"));
        killBullet = Applet.newAudioClip(getClass().getResource("/SOUNDS/bum.wav"));
        newTank = Applet.newAudioClip(getClass().getResource("/SOUNDS/life.wav"));
        complete = Applet.newAudioClip(getClass().getResource("/SOUNDS/level_completed.wav"));
    }

    public static void play(AudioClip audio) {
        if (canPlay) {
            if (audio == move) {
                audio.loop();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                audio.play();
            }
        }
    }

    public static void stopAll() {
        startGame.stop();
        shoot.stop();
        move.stop();
        shoot.stop();
        killBoss.stop();
        killBullet.stop();
        complete.stop();
    }
}
