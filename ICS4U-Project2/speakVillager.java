import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides the Villager head picture for the Backstory world
 * 
 * @author Justin
 * @version (a version number or a date)
 */
public class speakVillager extends SuperSmoothMover
{
    private GreenfootImage w;
    
    public speakVillager(){
        w = new GreenfootImage("villager.png");
        int percent = 550; 
        w.scale(w.getWidth()*percent/100, w.getHeight()*percent/100);
        setImage(w);
    }
}
