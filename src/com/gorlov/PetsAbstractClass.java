package com.gorlov;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class PetsAbstractClass implements PetsInterface {

    protected final Integer LEFT = 0;
    protected final Integer RIGHT = 1;
    protected final Integer FRONT = 2;
    private String name;
    private Integer satiety = 100;
    private Integer drowsiness = 100;
    private Integer bore = 0;
    protected Graphics g;
    protected Integer x = 10;
    protected Integer y = 430;
    protected static Image room;
    protected static Integer clickX;
    protected static Integer clickY;
    private static boolean mousePress = false;
    protected static Image fallImageRight;
    protected static Image fallImageLeft;
    protected static Image fallImageFront;
    protected static Image carryingImageRight;
    protected static Image carryingImageLeft;
    protected static Image carryingImageFront;
    protected Integer direction = RIGHT;


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


    public void click(Integer x, Integer y) {
        clickX = x;
        clickY = y;
        if (!mousePress && x > this.x && y > this.y && x < this.x + 100 && y < this.y + 100) {
            mousePress = !mousePress;
        } else {
            mousePress = false;
        }

    }

    public static boolean isMousePress() {
        return mousePress;
    }

    @Override
    public void fall() {
        int oldY = this.y;
        while (this.y < 430) {
            RoomClass.drawRoom(g, room);
            y += 5;
            if (direction.equals(LEFT)) {
                g.drawImage(fallImageLeft, x, y, 100, 100, null);
            } else if (direction.equals(RIGHT)) {
                g.drawImage(fallImageRight, x, y, 100, 100, null);
            } else if (direction.equals(FRONT)) {
                g.drawImage(fallImageFront, x, y, 100, 100, null);
            }
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (oldY < 330) {
            InterfaceClass.hp -= 20;
        }
    }

    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void move(Integer x, Integer y) {
        if (mousePress) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void carryOver() {
        RoomClass.drawRoom(g, room);
        if (direction.equals(LEFT)) {
            g.drawImage(carryingImageLeft, x, y, 100, 100, null);
        } else if (direction.equals(RIGHT)) {
            g.drawImage(carryingImageRight, x, y, 100, 100, null);
        } else if (direction.equals(FRONT)) {
            g.drawImage(carryingImageFront, x, y, 100, 100, null);
        }
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void walking(Graphics g, Image[] image, Integer offset) {
        for (Object o : image) {
            x += offset;
            RoomClass.drawRoom(g, room);
            g.drawImage((Image) o, x, y, 100, 100, null);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
