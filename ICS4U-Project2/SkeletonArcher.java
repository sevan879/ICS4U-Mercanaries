import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkeletonArcher extends Enemy
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
    
    public SkeletonArcher(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        
    }
    
    protected void action(Party targetPlayer){
        targetPlayer.takeDamage(DAMAGE + Greenfoot.getRandomNumber(1));
        //attack animations
    }
    
    public Party targetPastTanks(){
        List<Party> players = getObjectsInRange(attackRange, Party.class);

        if (!players.isEmpty()) {
            if(targetPlayer() instanceof Knight){
                players.remove(0);
            }
            return players.get(0); 
        }

        return null;
        
    }
    
    public void running() {
        
    }
    
    public void attackAnimation() {
        
    }
    
}
