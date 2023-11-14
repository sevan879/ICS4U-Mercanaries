import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archer extends Enemy
{
    private static final int HP = 2;
    private static final double SPEED = 3;
    private static final int DELAY = 25;
    private static final int DAMAGE = 3;
    private static final int ATTACK_RANGE = 200;
    
    private static final int RELOAD_TIME = 120; 
    private int reloadTimer = 0;
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
        move();
        action(targetPastTanks());
        
    }
    
    public Archer(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        
    }
    
    protected void action(Player targetPlayer){
        targetPlayer.takeDamage(damage + Greenfoot.getRandomNumber(1));
        //attack animations
    }
    
    public Player targetPastTanks(){
        List<Player> players = getObjectsInRange(attackRange, Player.class);

        if (!players.isEmpty()) {
            if(targetPlayer() instanceof tank){
                players.remove(0);
            }
            return players.get(0); 
        }

        return null;
        
    }
    
    
    
    
    
}
