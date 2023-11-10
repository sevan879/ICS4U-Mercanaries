import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * Enemy class, superclass for all enemies
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    protected int attackRange;
    
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
    
    public Enemy(int hp, int spd, int delay, int dmg, boolean movable)
    {
        super(hp, spd, delay, dmg, movable);
        
    }
    
    public void move(){
        //if no player is detected within attack range, move 
        if(targetPlayer() = null){
            setLocation(getX() - speed, getY());
            //moving animation
        }
    }

    
    public void attack(){
        
        if(targetPlayer() != null){
            
            if(actionCounter == 0){
                Player player = targetPlayer();
                player.takeDamage(damage);
                //attack animation
                actionCounter = actionDelay;
            } else{
                actionCounter--;
            }
 
        }
        
        
    }
    
    
    
    private Player targetPlayer() {
        List<Player> players = getObjectsInRange(attackRange, Player.class);

        if (!players.isEmpty()) {
            return players.get(0); 
        }

        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
