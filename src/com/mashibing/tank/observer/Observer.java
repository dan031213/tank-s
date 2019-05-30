package com.mashibing.tank.observer;

import java.io.Serializable;

public interface Observer extends Serializable {
    void handle(TankFireEvent tankFireEvent);
}
