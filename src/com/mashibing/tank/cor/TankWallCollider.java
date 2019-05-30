package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;
import com.mashibing.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject go1, GameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Wall) {
            Tank tank1 = (Tank) go1;
            Wall wall = (Wall) go2;
            if (tank1.rect.intersects(wall.rect)) {
//                tank1.changeDir();
                tank1.back();
            }

        } else if (go2 instanceof Tank && go1 instanceof Wall) {
            collide(go2, go1);
        }
        return true;
    }
}
