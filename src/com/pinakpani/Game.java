package com.pinakpani;

import javax.swing.*;
import java.awt.*;
/*
Game class is a daughter of the Canvas class.
It is also implemeting runnable, with the run function created below
 */
public class Game extends Canvas implements Runnable
{
    /*
    Setting up my frames
     */
    public static int width = 300;
    public static int hight = width/16*9;
    public static int scale = 3;

    public Game()
    {
        /*
        Constructor for the Game class
         */
        Dimension size =new Dimension(width*scale,hight*scale);
        setPreferredSize(size);
        frame = new JFrame();
    }
    /*
    Crating the private variables for the main Game class
     */
    private Thread gamethread;
    private JFrame frame;
    private boolean running = false;

    public synchronized void start()
    {
        /*
        Function for game start
         */
        running = true;
        gamethread = new Thread(this,"Display");
        gamethread.start();

    }

    public synchronized void stop()
    {
        /*
        Function for game stop
         */
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
        /*
        this is the main program where we will insert graphics and other stuff
         */
        while(running)
        {
            System.out.println("Running.....");

        }
    }

    public static void main(String[] args)
    {
        /*
        Creating my main function
        remember always put setResizable to false
        remember always put setDefaultCloseOperation to EXIT_ON_CLOSE
         */
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("My Game");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        /*
        starts the game
         */
        game.start();

    }



}
