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
    private GreenfootImage dungeonBackground;
    private GreenfootImage bossRoomBackground;
    private int worldTracker;
    private boolean changeBackground;

    //constructor
    public Background() {
            
        forestBackground = new GreenfootImage("ForestBackground.png");
        forestBackground.scale(forestBackground.getWidth()*2, forestBackground.getHeight()*2);
        setImage(forestBackground);
        
        worldTracker = 0;
        changeBackground = true;
    }

    //act method
    public void act()
    {
        setBackground();
    }

    //change background based on the area we are in
    public void setBackground() {
        if (changeBackground) {
            if (worldTracker == 0) { // forestworld
                setImage(forestBackground);
            }
        }
        changeBackground = false;
    }

    //make the background have a scrolling effect
    public void scrollBackground(int speed) {
        if (getX() == 0) {
            setLocation(1086, getY());
        }
        if (speed != 0) { //party moving
            setLocation(getX() - speed, getY());
        }
    }
}
