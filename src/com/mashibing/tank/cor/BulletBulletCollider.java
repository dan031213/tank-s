package com.mashibing.tank.cor;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

public class BulletBulletCollider implements Collider {
    @Override
    public boolean collide(GameObject go1, GameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Bullet) {
            Bullet bullet1 = (Bullet) go1;
            Bullet bullet2 = (Bullet) go2;
            if (bullet1.rect.intersects(bullet2.rect) && !bullet1.group.equals(bullet2.group)) {
                bullet1.die();
                bullet2.die();
                return false;
            }
        }
        return true;
    }
}
