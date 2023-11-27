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

    private MainWorld m;
    private Backstory b;
    private Settings s;

    /**
     * Main constructor for TitleScreen
     */
    public TitleScreen()
    {    
        super(1068, 720, 1); 
        titleScreenMusic = new GreenfootSound("TitleMusic.mp3");
        titleScreenMusic.setVolume(40);
        background = new GreenfootImage("titleBackground.png");
        setBackground(background);

        start = new Button(0);
        //buttons
        addObject(start, 534, 400);
        addObject(new Button(1), 534, 520);
        addObject(new Button(2), 534, 640);
        addObject(new TitleWords(), 534, 190);
    }

    /**
     * Plays code when the start button is clicked. 
     */
    public void started() {
        titleScreenMusic.playLoop();
    }
    /**
     * Plays code when the end button is clicked. 
     */
    public void stopped() {
        if (titleScreenMusic != null) {
            titleScreenMusic.stop();
        }
    }
    /**
     * Act method.
     */
    public void act() {
        if (Greenfoot.mouseClicked(start)) {
            stopped();
        }
    }
    /**
     * Transitions to a new world.
     * @param n Integer representing the specific world to change to.
     */
    public void transitionToNewWorld(int n) {
        if (n == 0) {
            m = new MainWorld();
            LoadingScreen load = new LoadingScreen(180, 1, false);
            addObject(load, getWidth()/2, getHeight()/2);
            Greenfoot.setWorld(m);
            if (load.getLoadingFinished()) {
            }

        }
        else if (n == 1) {
            s = new Settings();
            Greenfoot.setWorld(s);
        }
        else if (n == 2) {
            b = new Backstory();
            Greenfoot.setWorld(b);
        }
    }

}
