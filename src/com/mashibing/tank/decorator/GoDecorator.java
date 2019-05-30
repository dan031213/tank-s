package com.mashibing.tank.decorator;


import com.mashibing.tank.GameObject;

import java.awt.*;

/**
 * 装饰模式
 */
public abstract class GoDecorator extends GameObject {

    public GameObject go;

    public GoDecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public abstract void paint(Graphics g) ;
}
