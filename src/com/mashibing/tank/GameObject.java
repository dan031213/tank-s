package com.mashibing.tank;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    public int x, y;

    public int ObjWidth, ObjHeight;

    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);
}
