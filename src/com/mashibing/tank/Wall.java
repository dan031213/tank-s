package com.mashibing.tank;

import java.awt.*;

public class Wall extends GameObject {

    private int step = 0;


    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        this.rect.x = x;
        this.rect.y = y;
        this.ObjWidth = ResourceMgr.walls[0].getWidth();
        this.ObjHeight = ResourceMgr.walls[0].getHeight();
        this.rect.width = ObjWidth;
        this.rect.height = ObjHeight;
        GameModel.getInstance().add(this);

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.walls[step++], x, y, null);
        if (step == 7) {
            step = 0;
        }
    }
}
