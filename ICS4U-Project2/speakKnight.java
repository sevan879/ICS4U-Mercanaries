import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class speakKnight here.
 * 
 * @author Justin Wu
 * @version (a version number or a date)
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
    public speakKnight(){
        k = new GreenfootImage("KA14.png");
        int percent = 300; 
        k.scale(k.getWidth()*percent/100, k.getHeight()*percent/100);
        setImage(k);
        
    }
}
