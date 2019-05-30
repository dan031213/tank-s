package com.mashibing.tank;

import com.mashibing.tank.cor.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable {
    private static GameModel INSTANCE = new GameModel();

    public static GameModel getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE.init();
    }

    Tank myTank;

    private void init() {
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);
        System.out.println(myTank);
        int initTankCount = Integer.parseInt((String) PropertyMgr.getInstance().get("initTankCount"));
        int initWallCount = Integer.parseInt((String) PropertyMgr.getInstance().get("initWallCount"));
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(TankUtil.getRandomXPos(), TankUtil.getRandomYPos(), TankUtil.getRandomDir(), Group.BAD);
        }
        //初始化墙
        for (int i = 0; i < initWallCount; i++) {
            new Wall(TankUtil.getRandomXPos(), TankUtil.getRandomYPos());
        }
    }

    List<GameObject> gameObjects = new ArrayList<>();

    ColliderChain colliderChain = new ColliderChain();

    private GameModel() {
    }


    public void add(GameObject go) {
        gameObjects.add(go);
    }

    public void remove(GameObject go) {
        gameObjects.remove(go);
    }


    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
//        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
//        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);

//        System.out.println(myTank);

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        //TODO bug的产生
//
//        for (GameObject go : gameObjects
//        ) {
//            go.paint(g);
//
//        }
        //collision detect

//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++)
//                bullets.get(i).collideWith(tanks.get(j));
//        }

        //碰撞检测
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject go1 = gameObjects.get(i);
                GameObject go2 = gameObjects.get(j);
                colliderChain.collide(go1, go2);
            }
        }
    }

    public void save() {
        System.out.println("save begin");
        File file = new File("tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        System.out.println("load begin");
        File file = new File("tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            GameModel.INSTANCE= (GameModel) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
