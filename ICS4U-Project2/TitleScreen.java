import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title Screen for simulation
 * 
 * @author Arthur
 * @version V1
 */
public class TitleScreen extends World
{
    private GreenfootSound titleScreenMusic; // title screen music asset
    private GreenfootImage background;
    private Button start;
    private Button settings;
    private Button backstory;
    private TitleWords titleWords;
    private int volume;
    //construction
    public TitleScreen()
    {    
        super(1068, 720, 1); 
        titleScreenMusic = new GreenfootSound("TitleMusic.mp3");
        volume = 34;
        titleScreenMusic.setVolume(volume);
        background = new GreenfootImage("titleBackground.png");
        //etBackground(background);

        start = new Button(0);
        settings = new Button(1);
        backstory = new Button(2);
        titleWords = new TitleWords();
        //buttons
        addObject(start, 534, 400);
        addObject(settings, 534, 520);
        addObject(backstory, 534, 640);
        addObject(titleWords, 534, 190);
    }

    /**
     * Plays code when the start button is clicked. 
     */
    public void started() {
        titleScreenMusic.playLoop();
    }
    public void transitionToNewWorld(int n) {
        if (n == 0) {
                titleScreenMusic.stop();
                //remove objects
                removeObject(start);
                removeObject(settings);
                removeObject(backstory);
                removeObject(titleWords);
        }        
        else if (n == 1) {
            Greenfoot.setWorld(new Settings());
        }
        else if (n == 2) {
            Greenfoot.setWorld(new Backstory());
        }
    }

}
