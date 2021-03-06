package com.gorlov;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public abstract class PetsAbstractClass implements PetsInterface {

    protected final Integer LEFT = 0;
    protected final Integer RIGHT = 1;
    protected final Integer FRONT = 2;
    private String name;
    protected Graphics g;
    protected Integer x = 10;
    protected Integer y = 430;
    protected Image room;
    protected Integer clickX;
    protected Integer clickY;
    private static boolean mousePress = false;
    protected Image fallImageRight;
    protected Image fallImageLeft;
    protected Image fallImageFront;
    protected Image carryingImageRight;
    protected Image carryingImageLeft;
    protected Image carryingImageFront;
    protected Image sleepImageRight;
    protected Image sleepImageLeft;
    public static Image ball;
    protected Integer direction = RIGHT;
    private boolean game = false;
    private boolean sleep = false;

    public PetsAbstractClass(String name, Graphics g) {
        this.name = name;
        this.g = g;
        try {
            room = ImageIO.read(new FileImageInputStream(new File("resources/room.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public abstract void Draw(int logic);

    public abstract void playVoice();

    public void click(Integer x, Integer y) {
        if (y > 500) return;
        clickX = x;
        clickY = y;
        if (!mousePress && x > this.x-50 && y > this.y-50 && x < this.x + 100 && y < this.y + 100) {
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
            playVoice();
            InterfaceClass.setHp(-20);
        }
    }

    @Override
    public void sleep() {
        game = false;
        RoomClass.drawRoom(g, room);
        if (direction.equals(LEFT)) {
            g.drawImage(sleepImageLeft, x, y, 100, 100, null);
        } else {
            g.drawImage(sleepImageRight, x, y, 100, 100, null);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            g.drawImage(carryingImageLeft, x - 50, y - 50, 100, 100, null);
        } else if (direction.equals(RIGHT)) {
            g.drawImage(carryingImageRight, x - 50, y - 50, 100, 100, null);
        } else if (direction.equals(FRONT)) {
            g.drawImage(carryingImageFront, x - 50, y - 50, 100, 100, null);
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
            if (sleep) {
                break;
            }
            x += offset;
            RoomClass.drawRoom(g, room);
            g.drawImage((Image) o, x, y, 100, 100, null);
            if (game) {
                if (direction.equals(RIGHT)) {
                    g.drawImage((Image) ball, x + 90, y + 50, 30, 30, null);
                } else {
                    g.drawImage((Image) ball, x - 10, y + 50, 30, 30, null);
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (new Random().nextInt(100) < 30) playVoice();
    }
}
