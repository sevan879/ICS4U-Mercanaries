import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Provides the Knight picture for the Backstory world
 * 
 * @author Justin Wu
 * @version V0
 */
public class speakKnight extends SuperSmoothMover
{
    private GreenfootImage k;
    /**
     * Act - do whatever the speakKnight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    /**
     * Main Constructor for speakKnight
     */
    public speakKnight(){
        k = new GreenfootImage("KA14.png");
        int percent = 300; 
        k.scale(k.getWidth()*percent/100, k.getHeight()*percent/100);
        setImage(k);
        
    }
}
