import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * Enemy class, superclass for all enemie classes
 * 
 * @author Kenneth Jin
 * @version V0
 */
public abstract class Enemy extends Entity
{
    protected int attackRange;
    /**
     * Main constructor for Enemy class
     * 
     * @param hp enemie's health
     * @param spd enemie's speed
     * @param delay delay between actions
     * @param dmg enemie's damage
     * @param movable can the enemy move around
     * @param attackRange enemie's attack range
     */
    
    public Enemy(int hp, double spd, int delay, int dmg, boolean movable, int attackRange)
    {
        super(hp, spd, delay, true);
        this.attackRange = attackRange;
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
        attack();
        // Add your action code here.
    }
    

    public void move(){
        //if no player is detected within attack range, move 
        
        if(targetPlayer() == null){
            setLocation(getX() - speed, getY());
            //moving animation
        }
    }
    

    protected abstract void action(Party targetPlayer);
    
    public void attack(){
        
        if(targetPlayer() != null){
            Party targetPlayer = targetPlayer();
            if(actionCounter == 0){
                
                action(targetPlayer);
                
                actionCounter = actionDelay;
            } else{
                actionCounter--;
            }
 
        }

    }
    

    public Party targetPlayer() {
        List<Party> players = getObjectsInRange(attackRange, Party.class);

        if (!players.isEmpty()) {
            return players.get(0); 
        }

        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
