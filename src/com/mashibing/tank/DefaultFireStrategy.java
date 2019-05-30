package com.mashibing.tank;

import com.mashibing.tank.decorator.RectDecorator;

public class DefaultFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

//      有bug，为了用decorator装饰模式
//		GameModel.getInstance().add(new RectDecorator(new Bullet(bX, bY, t.dir, t.group)));
		new Bullet(bX, bY, t.dir, t.group);

//		if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}

}
