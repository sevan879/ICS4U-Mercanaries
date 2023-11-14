import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Projectiles
{
    private GreenfootSound[] a;// sound effect for arrow release and whoosh noise
    
    
    public Arrow(){
        
        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        a = new GreenfootSound[3];
        for(int i = 0; i < a.length; i++){
            a[i] = new GreenfootSound("Arrow.wav");
        }
    }
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
