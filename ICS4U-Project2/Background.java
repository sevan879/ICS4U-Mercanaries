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
    private GreenfootImage caveBackground;
    private GreenfootImage dungeonBackground;
    private GreenfootImage bossRoomBackground;
    private int worldTracker;

    /**
    * Constructor for Background Object
    */
    public Background() {

        forestBackground = new GreenfootImage("ForestBackground.png");
        forestBackground.scale(forestBackground.getWidth()*2, forestBackground.getHeight()*2);
        caveBackground = new GreenfootImage("CaveBackground.png");
        bossRoomBackground = new GreenfootImage("bossBackground.png");
        dungeonBackground = new GreenfootImage("dungeonBackground.png");
        setImage(forestBackground);
    
        worldTracker = 0;
    }

    public GreenfootImage getWorldBackground(int world) {
        if (world == 1)
        {
            return forestBackground;
        }
        else if (world == 2)
        {
            return caveBackground;
        }
        else if (world == 3) {
            return dungeonBackground;
        }
        else {
            return bossRoomBackground;
        }
    }

    /**
     * Change Background to specified world
     *
     * @param world Integer to represent the world to transition to.
     */
    public void setWorldBackground(int world) {
        if (world == 1)
        {
            setImage(forestBackground);
        }
        else if (world == 2)
        {
            setImage(caveBackground);
        }
        else if (world == 3)
        {
            setImage(dungeonBackground);
        }
        else {
            setImage(bossRoomBackground);
        }
    }

    /**
     * Scrolls the background at a specified speed. Should be in an act method
     * @param speed The speed the Background should scroll
     */
    public void scrollBackground(int speed) {
        if (this!=null) {
            if (getX() == 0) {
                setLocation(1086, getY());
            }
            if (speed != 0) { //party moving
                setLocation(getX() - speed, getY());
            }
        }
    }
}
