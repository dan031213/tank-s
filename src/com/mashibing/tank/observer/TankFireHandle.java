package com.mashibing.tank.observer;

public class TankFireHandle implements Observer {
    @Override
    public void handle(TankFireEvent tankFireEvent) {
        tankFireEvent.getSource().fire();
    }
}
