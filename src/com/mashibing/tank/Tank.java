package com.mashibing.tank;

import com.mashibing.tank.observer.Observer;
import com.mashibing.tank.observer.TankFireEvent;
import com.mashibing.tank.observer.TankFireHandle;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.peer.ScrollbarPeer;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Tank extends GameObject {


    private static final int SPEED = 2;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Rectangle rect = new Rectangle();

    private Random random = new Random();

    Dir dir = Dir.DOWN;

    private boolean moving = true;


    private boolean living = true;
    public Group group = Group.BAD;

    FireStrategy fs;
    private int oldX, oldY;


    @Override
    public String toString() {
        return "Tank{" +
                "rect=" + rect +
                ", random=" + random +
                ", dir=" + dir +
                ", moving=" + moving +
                ", living=" + living +
                ", group=" + group +
                ", fs=" + fs +
                ", oldX=" + oldX +
                ", oldY=" + oldY +
                ", fireObservers=" + fireObservers +
                '}';
    }

    public Tank(int x, int y, Dir dir, Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if (group == Group.GOOD) {
            String goodFSName = (String) PropertyMgr.getInstance().get("goodFS");

            try {
                fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            fs = new DefaultFireStrategy();
        }

        if (group == Group.BAD) {
            GameModel.getInstance().add(this);
        }
    }


    public void fire() {
        fs.fire(this);
    }

    public Dir getDir() {
        return dir;
    }


    public boolean isMoving() {
        return moving;
    }

    public void move() {

        oldX = x;
        oldY = y;

        if (group.equals(Group.GOOD)) {
            System.out.println(this);
        }
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();

        boundsCheck();
        //update rect
        rect.x = this.x;
        rect.y = this.y;

    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }


    @Override
    public void paint(Graphics g) {
        if (!living) GameModel.getInstance().remove(this);


        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

        move();

    }


    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void die() {
        this.living = false;
    }


    public void changeDir() {
        int changeStep = 20;
        switch (dir) {
            case UP:
                dir = Dir.DOWN;
                y += changeStep;
                break;
            case DOWN:
                dir = Dir.UP;
                y -= changeStep;
                break;
            case LEFT:
                dir = Dir.RIGHT;
                x += changeStep;
                break;
            case RIGHT:
                dir = Dir.LEFT;
                x -= changeStep;
                break;
        }
        move();
    }

    public void back() {
        x = oldX;
        y = oldY;
    }


//    private List<Observer> fireObservers=new ArrayList<>();

    private List<Observer> fireObservers = Arrays.asList(new TankFireHandle());


    public void handleFireKey() {
        TankFireEvent tankFireEvent = new TankFireEvent(this);
        for (Observer fireObserver : fireObservers
        ) {
            fireObserver.handle(tankFireEvent);
        }
    }


}
