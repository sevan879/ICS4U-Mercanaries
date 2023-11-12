import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sword here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sword extends Enemy
{
    /**
     * Act - do whatever the Sword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Sword(){
        //health, speed, delay, damage, movable
        super(20+ (int)Math.random() * 2, 4 + (int)Math.random() * 2, 10, 1 + (int)Math.random() * 2, true);
        attackRange = 20;
    }
}
