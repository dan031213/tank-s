package com.mashibing.tank.decorator;


import com.mashibing.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GoDecorator {

    public RectDecorator(GameObject go) {
        super(go);
    }


    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(go.x, go.y, go.rect.width,go.rect.height);
        g.setColor(c);
        go.paint(g);
    }
}
