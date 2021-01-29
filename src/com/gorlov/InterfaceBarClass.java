package com.gorlov;

import java.awt.*;

public class InterfaceBarClass {

    public static Integer hp = 100;

    public static void Draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString("HP",50,35);
        g.setColor(Color.RED);
        g.fillRect(70,20,hp, 20);
        g.setColor(Color.black);
        g.drawRect(70,20,100, 20);
    }
}
