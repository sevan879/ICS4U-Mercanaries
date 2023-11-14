import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;

/**
 * Write a description of class Sword here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sword extends Enemy
{
    private static final int HP = 10;
    private static final double SPEED = 4;
    private static final int DELAY = 10;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 25;
    private static boolean movable = true;
    
    public boolean isPickedUp = false;
    /**
     * Act - do whatever the Sword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(movable){
            super.act();
        }
        // Add your action code here.
    }
    
    public Sword(){
        
        super(HP, SPEED, DELAY, DAMAGE, movable, ATTACK_RANGE);
        
    }
    
    protected void action(Party targetPlayer){
        targetPlayer.takeDamage(DAMAGE + Greenfoot.getRandomNumber(1));
        //attack animations
    }
    
    public void pickUp(){
        movable = false;
        
    }
}
