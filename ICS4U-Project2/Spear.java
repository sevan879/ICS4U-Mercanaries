import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spear extends Enemy
{
    private static final int HP = 5;
    private static final double SPEED = 3;
    private static final int DELAY = 20;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 50;
    
    /**
     * Act - do whatever the Spear wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    public Spear(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        

    }
    
    public boolean canAttackPastTanks(){
        
       
    }
    
    
    
    
    
    
}
