import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flying here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flying extends Enemy
{
    private static final int HP = 20;
    private static final double SPEED = 5;
    private static final int DELAY = 10;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 25;
    
    public Flying(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
    }
    
    /**
     * Act - do whatever the Flying wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        move();
    }
    
    protected void action(Player targetPlayer){
        
    }
    
    public void pickUpEnemy(Enemy targetEnemy){
        
    }
    
    public void dropEnemy(){
        
    }
    
    
    
    public void move(){
        setLocation(getX() - speed, getY() + Greenfoot.getRandomNumber(2) - 1);
        //flying animations
    }
    
    
    
}
