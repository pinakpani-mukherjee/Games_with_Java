package com.pinakpani;

import com.pinakpani.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

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
    public static int height = width/16*9;
    public static int scale = 3;

    public Game()
    {
        /*
        Constructor for the Game class
         */
        Dimension size =new Dimension(width*scale,height*scale);
        setPreferredSize(size);

        screen = new Screen(width,height);
        frame = new JFrame();
    }
    /*
    Crating the private variables for the main Game class
     */
    private Thread gamethread;
    private JFrame frame;
    private boolean running = false;

    private com.pinakpani.graphics.Screen screen;

    private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

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
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0/60.0;
        double delta = 0;
        /*
        this is the main program where we will insert graphics and other stuff
         */
        while(running)
        {
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            while (delta >=1)
            {
                update();
                delta--;
            }
            render();

        }
        stop();
    }
    public void update()
    {

    }
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        /*
        Never forget to clear before render
         */
        screen.clear();
        screen.render();
        for (int i=0; i<pixels.length;i++)
        {
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(image,0,0,getWidth(),getHeight(), null);
        /*
        never forget to dispose
        never forget to show buffer as well
         */
        g.dispose();
        bs.show();

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
