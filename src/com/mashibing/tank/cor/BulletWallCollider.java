package com.mashibing.tank.cor;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject go1, GameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Wall) {
            Bullet bullet = (Bullet) go1;
            Wall wall = (Wall) go2;
            if (bullet.rect.intersects(wall.rect)) {
                bullet.die();
            }
        } else if (go1 instanceof Wall && go2 instanceof Bullet) {
            collide(go2, go1);
        }
        return true;
    }
}
