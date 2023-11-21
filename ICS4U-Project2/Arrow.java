import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Projectiles
{
    private static final int speed = 2;
    private static final int distance = 200;
    private static final int direction = -1;
    private static int distanceMoved = 0;
    private static int DELAY = 10;
    public Arrow(){
        super(speed, direction);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        move();
    }
    
    public void move(){
        
        
        
    }
}
