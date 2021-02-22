package com.gorlov;

import java.awt.*;

public class ObjectClass {

    private String intemHint;
    private Image itemFromPanel;
    private Image itemFromRoom;
    private Graphics g;

    public ObjectClass(String intemHint, Image itemFromPanel, Image itemFromRoom, Graphics g) {
        this.intemHint = intemHint;
        this.itemFromPanel = itemFromPanel;
        this.itemFromRoom = itemFromRoom;
        this.g = g;
    }

    public String getIntemHint() {
        return intemHint;
    }

    private void Draw(){

    }
}
