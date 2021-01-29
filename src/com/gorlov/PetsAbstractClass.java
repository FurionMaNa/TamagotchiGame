package com.gorlov;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class PetsAbstractClass implements PetsInterface{

    private String name;
    private Integer satiety = 100;
    private Integer drowsiness = 100;
    private Integer bore = 0;
    protected Graphics g;
    protected Integer x=10;
    protected Integer y=430;
    protected static Image room;
    protected static Integer clickX;
    protected static Integer clickY;
    private static boolean mousePress = false;


    public PetsAbstractClass(String name, Graphics g) {
        this.name = name;
        this.g = g;
        try {
            room = ImageIO.read(new FileImageInputStream(new File("resources/room.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSatiety() {
        return satiety;
    }

    public void setSatiety(Integer satiety) {
        this.satiety = satiety;
    }

    public Integer getDrowsiness() {
        return drowsiness;
    }

    public void setDrowsiness(Integer drowsiness) {
        this.drowsiness = drowsiness;
    }

    public Integer getBore() {
        return bore;
    }

    public void setBore(Integer bore) {
        this.bore = bore;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public abstract void Draw(int logic);


    public void click(Integer x, Integer y){
        this.clickX = x;
        this.clickY = y;
        this.mousePress = true;
    }

    public static boolean isMousePress() {
        return mousePress;
    }

    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void move(Integer x, Integer y) {
        //if(mousePress ){

            System.out.println(x + " " + y);
            this.x = x;
            this.y = y;

       // }
    }

    @Override
    public void walking(Graphics g, Image[] image, Integer offset){
        for (Object o : image) {
            RoomClass.drawRoom(g,room);
            x += offset;
            g.drawImage((Image) o, x, y, 100, 100, null);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
