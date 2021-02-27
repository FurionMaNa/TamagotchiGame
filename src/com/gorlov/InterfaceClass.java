package com.gorlov;

import java.awt.*;

public class InterfaceClass {

    public static Integer hp = 100;
    public static Integer hunger = 100;
    public static Integer sleep = 100;

    public static void Draw(Graphics g){
        /*HP BAR*/
        g.setColor(Color.BLACK);
        g.drawString("HP",50,35);
        g.setColor(Color.RED);
        g.fillRect(70,20,hp, 20);
        g.setColor(Color.black);
        g.drawRect(70,20,100, 20);
        /*HUNGER BAR*/
        g.setColor(Color.BLACK);
        g.drawString("HUNGER",200,35);
        g.setColor(Color.GREEN);
        g.fillRect(260,20,hunger, 20);
        g.setColor(Color.black);
        g.drawRect(260,20,100, 20);
        /*SLEEP BAR*/
        g.setColor(Color.BLACK);
        g.drawString("SLEEP",400,35);
        g.setColor(Color.BLUE);
        g.fillRect(450,20,sleep, 20);
        g.setColor(Color.black);
        g.drawRect(450,20,100, 20);
        /*ITEM TABLE*/
        g.setColor(Color.WHITE);
        int x = 150;
        int sizeItem = 40;
        for (int i = 1; i <= 5; i++) {
            g.drawRect(x + i * sizeItem, 520, sizeItem, sizeItem);
            g.drawRect(x + i * sizeItem + 3, 520 + 3, sizeItem - 6, sizeItem - 6);
        }
    }

}
