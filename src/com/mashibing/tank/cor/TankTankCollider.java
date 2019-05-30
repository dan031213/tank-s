package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject go1, GameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Tank) {
            Tank tank1 = (Tank) go1;
            Tank tank2 = (Tank) go2;
            if (tank1.rect.intersects(tank2.rect)) {
//                tank1.changeDir();
//                tank2.changeDir();
                tank1.back();
                tank2.back();
            }
        }
        return true;
    }
}
