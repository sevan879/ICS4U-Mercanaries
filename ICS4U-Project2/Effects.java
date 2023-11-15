import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effects here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effects extends Actor
{
    protected GreenfootImage image;
    
    protected void fadeOut (int timeLeft, int totalFadeTime){
        double percent = timeLeft / (double) totalFadeTime;
        int newTransparency = (int)(percent * 255);
        image.setTransparency (newTransparency);
    }
    
    protected void fadeIn (int timeLeft, int totalFadeTime){
        double percent = timeLeft / (double) totalFadeTime;
        int newTransparency = (int)(percent * 255);
        image.setTransparency (255 - newTransparency);
    }
}
