package com.gorlov;

import java.awt.*;
import java.util.ArrayList;

public interface PetsInterface {

    public void eat();
    public void say();
    public void sleep();
    public void move(Integer x, Integer y);
    public void walking(Graphics g, Image[] image, Integer offset) throws InterruptedException;
    public void fall();
    public void carryOver();

}
