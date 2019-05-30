package com.mashibing.tank.observer;

import com.mashibing.tank.Tank;

import java.io.Serializable;

public class TankFireEvent implements Serializable {
    private Tank tank;

    public Tank getSource(){
        return  tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
