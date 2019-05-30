package com.mashibing.tank.cor;

import com.mashibing.tank.*;

/**
 * 子弹和坦克碰撞
 * 务必是子弹&&坦克
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject go1, GameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Bullet) {
            Tank tank = (Tank) go1;
            Bullet bullet = (Bullet) go2;
            if (bullet.group == tank.group) return true;
            if (bullet.rect.intersects(tank.rect)) {
                if (tank.group.equals(Group.BAD)) {
                    tank.die();
                }
                bullet.die();
                int eX = tank.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = tank.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                new Explode(eX, eY);
                return false;
            }
        } else if (go1 instanceof Bullet && go2 instanceof Tank) {
            collide(go2, go1);
        }
        return true;

    }
}
