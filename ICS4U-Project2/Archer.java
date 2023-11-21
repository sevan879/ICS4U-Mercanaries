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
    private static boolean movable = true;
    
    
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
        super(HP, SPEED, DELAY, DAMAGE, movable, ATTACK_RANGE);
        
    }
    
    protected void action(Party targetPlayer){
        targetPlayer.takeDamage(DAMAGE + Greenfoot.getRandomNumber(1));
        getWorld().addObject(new Arrow(), getX(), getY());
        
        //attack animations
    }
    
    protected void repelOtherEnemies(){
        List<Archer> archers = getObjectsAtOffset(-attackRange, 0, Archer.class);
        if(archers.size() > 3){
            movable = false;
        }
        movable = true;
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
    
    
    
    
    
}
