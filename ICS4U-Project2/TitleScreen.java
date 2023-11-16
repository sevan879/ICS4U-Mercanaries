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
    private GreenfootImage background;
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        titleScreen = new GreenfootSound("TitleScreen.mp3");
        titleScreen.setVolume(40);
        background = new GreenfootImage("titleBackground.png");
        setBackground(background);
        
        //buttons
        addObject(new TitleScreenButton(0), 534, 400);
        addObject(new TitleScreenButton(1), 534, 520);
        addObject(new TitleScreenButton(2), 534, 640);
    }
    //to start playing the music when pressed run
    public void started(){
        titleScreen.playLoop();
    }
    //to stop playing the music when pressed pause or reset
    public void stopped(){
        titleScreen.stop();
    }
    //create main world
    public void startSim() {
        MainWorld m = new MainWorld();
        Greenfoot.setWorld(m);
    }
    
}
