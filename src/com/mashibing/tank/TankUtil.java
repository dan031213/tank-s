package com.mashibing.tank;

import java.util.Random;

public class TankUtil {
     public static int getRandomXPos(){
        return new Random().nextInt(TankFrame.GAME_WIDTH);
    }

    public static int getRandomYPos(){
        return new Random().nextInt(TankFrame.GAME_HEIGHT);
    }

    public static Dir getRandomDir() {
        return Dir.values()[new Random().nextInt(Dir.values().length)];
    }
}
