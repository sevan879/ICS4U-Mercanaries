import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spear extends Enemy
{
    
    
    /**
     * Act - do whatever the Spear wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public Spear(){
        super(10 + (int)Math.random() * 2, 5 + (int)Math.random() * 2, 20, 2 + (int)Math.random() * 2, true);
        attackRange = 50;

    }
    
    public boolean canAttackPastTanks(){
        
       
    }
    
    
    
    
    
    
}
