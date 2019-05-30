package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.PropertyMgr;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {

    List<Collider> colliderChainList = new LinkedList();

    void initColliders() {
        String[] collidersstr = PropertyMgr.getInstance().get("colliders").toString().split(",");
        for (String str : collidersstr
        ) {
            try {
                add((Collider) Class.forName(str).getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ColliderChain() {
        initColliders();
    }

    void add(Collider collider) {
        colliderChainList.add(collider);
    }

    void remove(Collider collider) {
        colliderChainList.remove(collider);
    }

    @Override
    public boolean collide(GameObject go1, GameObject go2) {
        boolean b = true;
        for (int i = 0; i < colliderChainList.size(); i++) {
            if (b) {
                b = colliderChainList.get(i).collide(go1, go2);
            }
        }
        return b;
    }
}
