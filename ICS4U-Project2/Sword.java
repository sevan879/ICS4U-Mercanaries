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
    /**
     * Act - do whatever the Sword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Sword(){
        
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        
    }
    
    protected void action(Player targetPlayer){
        targetPlayer.takeDamage(damage + Greenfoot.getRandomNumber(1));
        //attack animations
    }
}
