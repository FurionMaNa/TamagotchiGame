package com.gorlov;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DogClass extends PetsAbstractClass implements PetsInterface{

    private Image[] imagesWalkRight = new Image[4];
    private Image[] imagesWalkLeft = new Image[4];
    private Image front;

    public DogClass(String name, Graphics g) {
        super(name, g);
        try {
            front = ImageIO.read(new FileImageInputStream(new File("resources/DogFront.png")));
            fallImageRight = ImageIO.read(new FileImageInputStream(new File("resources/DogFallRight.png")));
            fallImageLeft = ImageIO.read(new FileImageInputStream(new File("resources/DogFallLeft.png")));
            fallImageFront = ImageIO.read(new FileImageInputStream(new File("resources/DogFallFront.png")));
            carryingImageRight = ImageIO.read(new FileImageInputStream(new File("resources/DogCarryingRight.png")));
            carryingImageLeft = ImageIO.read(new FileImageInputStream(new File("resources/DogCarryingLeft.png")));
            sleepImageRight = ImageIO.read(new FileImageInputStream(new File("resources/SleepRight.png")));
            sleepImageLeft = ImageIO.read(new FileImageInputStream(new File("resources/SleepLeft.png")));
            carryingImageFront = ImageIO.read(new FileImageInputStream(new File("resources/DogCarryingFront.png")));
            imagesWalkRight[0] = ImageIO.read(new FileImageInputStream(new File("resources/DogRight1.png")));
            imagesWalkRight[1] = ImageIO.read(new FileImageInputStream(new File("resources/DogRight2.png")));
            imagesWalkRight[2] = ImageIO.read(new FileImageInputStream(new File("resources/DogRight3.png")));
            imagesWalkRight[3] = ImageIO.read(new FileImageInputStream(new File("resources/DogRight4.png")));
            imagesWalkLeft[0] = ImageIO.read(new FileImageInputStream(new File("resources/DogLeft1.png")));
            imagesWalkLeft[1] = ImageIO.read(new FileImageInputStream(new File("resources/DogLeft2.png")));
            imagesWalkLeft[2] = ImageIO.read(new FileImageInputStream(new File("resources/DogLeft3.png")));
            imagesWalkLeft[3] = ImageIO.read(new FileImageInputStream(new File("resources/DogLeft4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void Draw(int logic) {
        if(logic<100){
            direction = RIGHT;
            walking(g, imagesWalkRight, 10);
        } else if (logic<200) {
            direction = LEFT;
            walking(g, imagesWalkLeft, -10);
        } else {
            RoomClass.drawRoom(g,room);
            g.drawImage((Image) front, this.x,this.y,100,100,null);
            if (this.isGame()) {
                g.drawImage((Image) ball, x + 30, y, 30, 30, null);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            direction = FRONT;
        }
    }

}
