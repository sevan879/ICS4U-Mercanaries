import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract superclass for all visual effects. Contains methods for changing visuals.
 * 
 * @author Evan Ma
 * @version V1
 */
public abstract class Effects extends SuperSmoothMover
{
    protected GreenfootImage image;
    
    /**
     * Call in act method to fade in an actor
     * @param timeLeft Amount of time remaining
     * @param totalFadeTime Total time of fade
     */
    protected void fadeIn (int timeLeft, int totalFadeTime){
        double percent = timeLeft / (double) totalFadeTime;
        int newTransparency = (int)(percent * 255);
        image.setTransparency (newTransparency);
    }
    /**
     * Call in act method to fade out an actor
     * @param timeLeft Amount of time remaining
     * @param totalFadeTime Total time of fade
     */
    protected void fadeOut (int timeLeft, int totalFadeTime){
        double percent = timeLeft / (double) totalFadeTime;
        int newTransparency = (int)(percent * 255);
        image.setTransparency (255 - newTransparency);
    }
}
