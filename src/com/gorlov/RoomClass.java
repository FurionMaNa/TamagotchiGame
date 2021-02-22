package com.gorlov;

import java.awt.*;

public class RoomClass {

    private final static Integer WIDTH_WINDOW = 600;
    private final static Integer HIGHT_WINDOW = 600;

    public static void drawRoom(Graphics g,Image room){
        g.drawImage(room,0,0,WIDTH_WINDOW, HIGHT_WINDOW,null);
        InterfaceClass.Draw(g);
    }
}
