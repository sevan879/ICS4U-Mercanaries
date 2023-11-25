import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class YouDied here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends World
{

    /**
     * Constructor for objects of class YouDied.
     * 
     */
    public EndScreen(boolean cohenWon)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        GreenfootImage img1 = new GreenfootImage("CohenWins.png");
        GreenfootImage img2 = new GreenfootImage("CohenLoses.png");
        img1.scale(1068, 720);
        img2.scale(1068, 720);
        if (cohenWon) {
            setBackground(img1);
        }
        else {
            setBackground(img2);
        }
        addObject(new Button(16), 534, 360);
    }
}