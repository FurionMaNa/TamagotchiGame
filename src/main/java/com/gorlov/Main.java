package com.gorlov;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Main extends JFrame implements Runnable {

    private final static Integer WIDTH_WINDOW = 600;
    private final static Integer HIGHT_WINDOW = 600;
    private static PetsAbstractClass pets;
    private static int countAct = 0;
    private static int logic = 0;
    private static Graphics g;
    public static Image room;
    public static Thread t;
    private static Boolean gameAlive = true;
    public static ArrayList<ObjectClass> items = new ArrayList<>();

    public Main() throws IOException {
        room = ImageIO.read(new FileImageInputStream(new File("resources/room.png")));
        JFrame frame = new JFrame("Игра тамагочи");
        frame.setResizable(false);
        JPanel panelDraw = new PanelDraw();
        frame.add(panelDraw);
        frame.setPreferredSize(new Dimension(WIDTH_WINDOW, HIGHT_WINDOW));
        frame.pack();
        frame.setVisible(true);
        JsonData jsonData = ConverterJSON.toJavaObject();
        InterfaceClass.hp = jsonData.hp;
        InterfaceClass.sleep = jsonData.sleep;
        InterfaceClass.hunger = jsonData.hunger;
        int countTick = (int) ((new Date().getTime() - jsonData.lastSave.getTime()) / 100000);
        if (InterfaceClass.sleep - countTick * 20 <= 0 || InterfaceClass.hunger - countTick * 20 <= 0) {
            JOptionPane.showMessageDialog(frame, "Пока вы отсутствовали ваш питомец помер! Начните заново");
            InterfaceClass.hp = 100;
            InterfaceClass.hunger = 100;
            InterfaceClass.sleep = 100;
        }
        PetsAbstractClass.ball = ImageIO.read(new FileImageInputStream(new File("resources/ball.png")));
        pets = new DogClass("Жужа", panelDraw.getGraphics());
        items.add(new ObjectClass("Кость", ImageIO.read(new FileImageInputStream(new File("resources/bone.png"))), panelDraw.getGraphics(), 10));
        items.add(new ObjectClass("Курица", ImageIO.read(new FileImageInputStream(new File("resources/chicken.png"))), panelDraw.getGraphics(), 20));
        items.add(new ObjectClass("Рыба", ImageIO.read(new FileImageInputStream(new File("resources/fish.png"))), panelDraw.getGraphics(), 15));
        items.add(new ObjectClass("Сон", ImageIO.read(new FileImageInputStream(new File("resources/sleep.png"))), panelDraw.getGraphics(), 1));
        items.add(new ObjectClass("Мячь", ImageIO.read(new FileImageInputStream(new File("resources/ball.png"))), panelDraw.getGraphics(), null));
        t = new Thread(this);
        t.start();
        Thread statisticThreead = new Thread(new Runnable() {

            private Integer tickHunger = 0;
            private Integer tickSleep = 0;

            @Override
            public void run() {
                while (true) {
                    if (tickHunger == 50) {
                        InterfaceClass.setHunger(pets.isSleep() ? -5 : -20);
                        tickHunger = 0;
                    }
                    if (tickSleep == 100) {
                        InterfaceClass.setSleep(pets.isGame() ? -40 : -20);
                        tickSleep = 0;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!pets.isGame()) {
                        tickHunger++;
                    }
                    if (!pets.isSleep()) {
                        tickSleep++;
                    }
                    if (InterfaceClass.hunger <= 0 || InterfaceClass.hp <= 0 || InterfaceClass.sleep <= 0) {
                        gameAlive = false;
                        JOptionPane.showMessageDialog(frame, "Ваш питомец помер! В следующий раз будьте внимательнее");
                        break;
                    }
                    JsonData jsonData = new JsonData();
                    jsonData.dog = true;
                    jsonData.hp = InterfaceClass.hp;
                    jsonData.name = pets.getName();
                    jsonData.lastSave = new Date();
                    jsonData.sleep = InterfaceClass.sleep;
                    jsonData.hunger = InterfaceClass.hunger;
                    try {
                        ConverterJSON.toJSON(jsonData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        statisticThreead.start();
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(false);
        try {
            new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int choiceDirecrion() {
        if (countAct >= new Random().nextInt(20) + 10) {
            countAct = 0;
            logic = new Random().nextInt(300);
        } else if (pets.getX() < 10) {
            logic = 0;
        } else if (pets.getX() >= Main.WIDTH_WINDOW - 120) {
            logic = 100;
        }
        return logic;
    }

    @Override
    public void run() {
        while (gameAlive) {
            if (pets.isSleep()) {
                pets.sleep();
                InterfaceClass.setSleep(1);
            } else {
                if (pets.isGame()) {
                    InterfaceClass.setHunger(5);
                }
                if (!PetsAbstractClass.isMousePress() && pets.y < 430) {
                    pets.fall();
                }
                if (!PetsAbstractClass.isMousePress()) {
                    pets.Draw(choiceDirecrion());
                } else {
                    pets.carryOver();
                }
                countAct++;
            }
        }
    }

    private static class PanelDraw extends JPanel {

        public PanelDraw() {
            setPreferredSize(new Dimension(Main.WIDTH_WINDOW, Main.HIGHT_WINDOW));
            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {

                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    if (pets != null) {
                        pets.move(e.getX(), e.getY());
                    }
                }
            });

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pets.click(e.getX(), e.getY());
                    for (int i = 1; i <= 5; i++) {
                        if (e.getX() > 150 + i * 40 && e.getY() > 520 && e.getX() < 190 + i * 40 && e.getY() < 560) {
                            items.get(i - 1).act();
                            switch (i) {
                                case 4:
                                    pets.setSleep(!pets.isSleep());
                                    break;
                                case 5:
                                    pets.setGame(!pets.isGame());
                                    break;
                            }
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Main.g = g;
        }
    }

}
