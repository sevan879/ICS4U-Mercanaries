import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LocalEffects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LocalEffects extends Actor
{
    protected GreenfootImage image;
    /**
     * Act - do whatever the LocalEffects wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected void fade(int timeLeft, int totalFadeTime){
        double percent = timeLeft/(double)totalFadeTime;
        if(percent > 1.00) return;
        int newTransparency = (int)(percent * 255);
        image.setTransparency (newTransparency);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
