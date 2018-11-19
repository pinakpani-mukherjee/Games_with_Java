package com.pinakpani;

import com.pinakpani.entity.mob.Player;
import com.pinakpani.graphics.Screen;
import com.pinakpani.input.Keyboard;
import com.pinakpani.level.RandomLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import com.pinakpani.level.Level;
/*
Game class is a daughter of the Canvas class.
It is also implemeting runnable, with the run function created below
 */
public class Game extends Canvas implements Runnable
{
    /*
    Setting up my frames
     */
    public static final long serialVersionUID = 1L;
    public static int width = 300;
    public static int height = width/16*9;
    public static int scale = 3;
    public static String title = "Rain";
    private Thread gamethread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;

    private com.pinakpani.graphics.Screen screen;

    private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();


    public Game()
    {
        /*
        Constructor for the Game class
         */
        Dimension size =new Dimension(width*scale,height*scale);
        setPreferredSize(size);

        screen = new Screen(width,height);
        frame = new JFrame();
        key = new Keyboard();
        //important to add key listner, dont forget to add this, or else your program wont accept key input
        //also add KeyListner after making new Keyboard;
        frame.addKeyListener(key);
        level = new RandomLevel(64,64);
        player = new Player(key);

    }
    /*
    Crating the private variables for the main Game class
     */

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
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0/60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        //adds game in foreground, not required always
        //requestFocus();
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
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println(updates+" ups, "+frames + " fps");
                frame.setTitle(Game.title + "  |  " + updates+" ups, "+frames + " fps" );
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }
        public void update()
    {
        //dont forget to update your keys, or else it wont show up
        key.update();;
        player.update();

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
        int xScroll = player.x - screen.width/2;
        int yScroll = player.y - screen.height/2;
        level.render(xScroll,yScroll,screen);
        player.render(screen);
        for (int i=0; i<pixels.length;i++)
        {
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLUE);
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
        game.frame.setTitle(Game.title);
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
