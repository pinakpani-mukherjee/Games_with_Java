package com.pinakpani;

import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable
{
    public static int width = 300;
    public static int hight = width/16*9;
    public static int scale = 3;

    public Game()
    {
        Dimension size =new Dimension(width*scale,hight*scale);
        setPreferredSize(size);
        frame = new JFrame();
    }

    private Thread gamethread;
    private JFrame frame;
    private boolean running = false;

    public synchronized void start()
    {
        running = true;
        gamethread = new Thread(this,"Display");
        gamethread.start();

    }

    public synchronized void stop()
    {
        running = false;
        try {
            gamethread.join();
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        while(running)
        {
            System.out.println("Running.....");

        }
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("My Game");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();

    }



}
