package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;


public class PropertyMgr {
    private static PropertyMgr INSTANCE = new PropertyMgr();

    public static PropertyMgr getInstance() {
        return INSTANCE;
    }


    private PropertyMgr() {
    }

    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public Object get(String key) {
        if (props == null) return null;
        return props.get(key);
    }

    //int getInt(key)
    //getString(key)

    public static void main(String[] args) {
        System.out.println(PropertyMgr.getInstance().get("initTankCount"));

    }
}
