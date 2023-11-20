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
    
    //construction
    public TitleScreen()
    {    
        super(1068, 720, 1); 
        titleScreen = new GreenfootSound("TitleScreen.mp3");
        titleScreen.setVolume(40);
        background = new GreenfootImage("TitleBackground.png");
        setBackground(background);

        //buttons
        addObject(new Button(0), 534, 400);
        addObject(new Button(1), 534, 520);
        addObject(new Button(2), 534, 640);
        addObject(new TitleWords(), 534, 190);
    }

    //to start playing the music when pressed run
    public void started() {
        titleScreen.playLoop();
    }
    //to stop playing the music when pressed pause or reset
    public void stopped() {
        if (titleScreen != null) {
            titleScreen.stop();
        }
    }

}
