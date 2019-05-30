package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;

import java.io.Serializable;

public interface Collider extends Serializable{
    boolean collide(GameObject go1, GameObject go2);
}
