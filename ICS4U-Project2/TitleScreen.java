import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    private GreenfootSound titleScreen; // title screen music asset
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        titleScreen = new GreenfootSound("TitleScreen.mp3");
        
    }
    //to start playing the music when pressed run
    public void started(){
        titleScreen.playLoop();
    }
    //to stop playing the music when pressed pause or reset
    public void stopped(){
        titleScreen.stop();
    }
    
}
