import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides the Villager head picture for the Backstory world
 * 
 * @author Justin
 * @version V1
 */
public class speakVillager extends SuperSmoothMover
{
    private GreenfootImage w;
    
    /**
     * Main Constructor for speakVillager
     */
    public speakVillager(){
        w = new GreenfootImage("villager.png");
        int percent = 550; 
        w.scale(w.getWidth()*percent/100, w.getHeight()*percent/100);
        setImage(w);
    }
}
