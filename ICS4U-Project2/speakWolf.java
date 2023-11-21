import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class speakWolf here.
 * 
 * @author Justin
 * @version (a version number or a date)
 */
public class speakWolf extends SuperSmoothMover
{
    private GreenfootImage w;
    /**
     * Act - do whatever the Wolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    
    }
    
    public speakWolf(){
        w = new GreenfootImage("W10.png");
        int percent = 300; // for 50% larger image
        w.scale(w.getWidth()*percent/100, w.getHeight()*percent/100);
        setImage(w);
    }
}
