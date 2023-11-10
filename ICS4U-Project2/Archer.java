import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archer extends Enemy
{
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public Archer(){
        super(3 + (int)Math.random() * 2, 1 + (int)Math.random() * 2, 20, 2 + (int)Math.random() * 2, true);
        
        attackRange = 200;
        
        
    }
    
    
    
}
