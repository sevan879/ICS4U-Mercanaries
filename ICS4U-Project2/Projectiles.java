import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Projectiles extends Actor
{
    protected int speed;
    protected int direction; //-1 = left, 1 = right
    
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Projectiles(int spd, int direction){
        speed = spd;
        this.direction = direction;
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void move(){
        
    }
}
