import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wolf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wolf extends Enemy
{
    private static final int HP = 10;
    private static final double SPEED = 5;
    private static final int DELAY = 25;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 20;
    private static boolean movable = true;
    public Wolf(){
        super(HP, SPEED, DELAY, DAMAGE, movable, ATTACK_RANGE);
    }
    
    /**
     * Act - do whatever the Wolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    protected void repelOtherEnemies(){
        
    }
    
    protected void action(Party targetPlayer){
        
    }
}
