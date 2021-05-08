package com.gorlov;

import java.awt.*;

public class ObjectClass {

    private String itemHint;
    private Image itemFromPanel;
    private Graphics g;
    private Integer paramHP = 0;


    public ObjectClass(String itemHint, Image itemFromPanel, Graphics g, Integer paramHP) {
        this.itemHint = itemHint;
        this.itemFromPanel = itemFromPanel;
        this.g = g;
        this.paramHP = paramHP;
    }

    public String getItemHint() {
        return itemHint;
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
