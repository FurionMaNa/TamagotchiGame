package com.gorlov;

import java.awt.*;
import java.util.ArrayList;

public interface PetsInterface {

    void sleep();
    void move(Integer x, Integer y);
    void walking(Graphics g, Image[] image, Integer offset) throws InterruptedException;
    void fall();
    void carryOver();

}
