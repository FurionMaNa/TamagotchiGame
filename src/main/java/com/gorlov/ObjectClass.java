package com.gorlov;

import java.awt.*;

public class ObjectClass {

    private String intemHint;
    private Image itemFromPanel;
    private Graphics g;
    private static boolean mousePress = false;
    private Integer paramHP = 0;


    public ObjectClass(String intemHint, Image itemFromPanel, Graphics g, Integer paramHP) {
        this.intemHint = intemHint;
        this.itemFromPanel = itemFromPanel;
        this.g = g;
        this.paramHP = paramHP;
    }

    public String getIntemHint() {
        return intemHint;
    }

    public void Draw(int x, int y, int size) {
        g.drawImage(this.itemFromPanel, x, y, size, size, null);
    }

    public void act() {
        if (paramHP!=null) {
            InterfaceClass.setHp(paramHP);
        }
    }

}
