import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Creates the background that needs to be scrolled.
 * 
 * @author Arthur Tian 
 * @version V1
 */
public class Background extends Actor
{
    private GreenfootImage forestBackground;
    private GreenfootImage dungeonBackground;
    private GreenfootImage bossRoomBackground;
    private int worldTracker;

    /**
    * Constructor for Background Object
    */
    public Background() {
            
        forestBackground = new GreenfootImage("ForestBackground.png");
        forestBackground.scale(forestBackground.getWidth()*2, forestBackground.getHeight()*2);
        dungeonBackground = new GreenfootImage("CaveBackground.png");
        bossRoomBackground = new GreenfootImage("bossBackground.png");
        setImage(forestBackground);
        
        worldTracker = 0;
    }

    /**
    * Change Background to specified world
    *
    * @param world Integer to represent the world to transition to.
    */
    public void setWorldBackground(int world) {
        if (world == 0)
        {
            setImage(forestBackground);
        }
        else if (world == 1)
        {
            setImage(dungeonBackground);
        }
        else if (world == 2)
        {
            setImage(bossRoomBackground);
        }
    }

    /**
     * Scrolls the background at a specified speed. Should be in an act method
     * @param speed The speed the Background should scroll
     */
    public void scrollBackground(int speed) {
        if (getX() == 0) {
            setLocation(1086, getY());
        }
        if (speed != 0) { //party moving
            setLocation(getX() - speed, getY());
        }
    }
}
