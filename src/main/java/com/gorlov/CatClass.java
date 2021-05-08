package com.gorlov;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CatClass extends PetsAbstractClass implements PetsInterface {

    private Image[] imagesWalkRight = new Image[4];
    private Image[] imagesWalkLeft = new Image[4];
    private Image front;

    public CatClass(String name, Graphics g) {
        super(name, g);
        try {
            front = ImageIO.read(new FileImageInputStream(new File("resources/CatFront.png")));
            fallImageRight = ImageIO.read(new FileImageInputStream(new File("resources/CatFallRight.png")));
            fallImageLeft = ImageIO.read(new FileImageInputStream(new File("resources/CatFallLeft.png")));
            fallImageFront = ImageIO.read(new FileImageInputStream(new File("resources/CatFallFront.png")));
            carryingImageRight = ImageIO.read(new FileImageInputStream(new File("resources/CatCarryingRight.png")));
            carryingImageLeft = ImageIO.read(new FileImageInputStream(new File("resources/CatCarryingLeft.png")));
            sleepImageRight = ImageIO.read(new FileImageInputStream(new File("resources/CatSleepRight.png")));
            sleepImageLeft = ImageIO.read(new FileImageInputStream(new File("resources/CatSleepLeft.png")));
            carryingImageFront = ImageIO.read(new FileImageInputStream(new File("resources/CatCarryingFront.png")));
            imagesWalkRight[0] = ImageIO.read(new FileImageInputStream(new File("resources/CatRight1.png")));
            imagesWalkRight[1] = ImageIO.read(new FileImageInputStream(new File("resources/CatRight2.png")));
            imagesWalkRight[2] = ImageIO.read(new FileImageInputStream(new File("resources/CatRight3.png")));
            imagesWalkRight[3] = ImageIO.read(new FileImageInputStream(new File("resources/CatRight4.png")));
            imagesWalkLeft[0] = ImageIO.read(new FileImageInputStream(new File("resources/CatLeft1.png")));
            imagesWalkLeft[1] = ImageIO.read(new FileImageInputStream(new File("resources/CatLeft2.png")));
            imagesWalkLeft[2] = ImageIO.read(new FileImageInputStream(new File("resources/CatLeft3.png")));
            imagesWalkLeft[3] = ImageIO.read(new FileImageInputStream(new File("resources/CatLeft4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void playSound()  {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("cat.wav"));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }

    @Override
    public void Draw(int logic) {
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
