package com.gorlov;

import java.awt.*;

public class RoomClass {

    public static synchronized void drawRoom(Graphics g,Image room){
        g.drawImage(room,0,0,600, 600,null);
        InterfaceClass.Draw(g);
    }
}
