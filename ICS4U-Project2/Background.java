import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends Actor
{
    /**
     * Act - do whatever the Background wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private GreenfootImage forestBackground;
    private GreenfootImage caveBackground;
    private GreenfootImage dungeonBackground;
    private GreenfootImage bossRoomBackground;
    private int worldTracker;

    //constructor
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

    //make the background have a scrolling effect
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
