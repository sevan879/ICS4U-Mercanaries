import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * Enemy class, superclass for all enemie classes
 * 
 * @author Kenneth Jin
 * @version November 2023
 */
public abstract class Enemy extends Entity
{
    protected int attackRange;

    private SuperStatBar hpBar;

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

    protected List<Party> playersUpClose(){
        List<Party> fullDamage = getObjectsInRange(attackRange, Party.class);
        return fullDamage;
    }

    protected List<Party> playersFurtherAway(){
        List<Party> halfDamage = getObjectsInRange(attackRange + 25, Party.class);
        return halfDamage;
    }

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (health <= 0) {
            death();
            if (hpBarExists) {
                getWorld().removeObject(hpBar);
                hpBarExists = false;
            }
        }
        else {
            hpBar.update(health);
            move();
            attack();
            // Add your action code here.
        }
    }

    /**
     * move if there are no player in the attack range
     */
    public void move(){
        //if no player is detected within attack range, move 
        if(targetPlayer() == null){
            setLocation(getX() - speed, getY());
            //moving animation
            running();
        }
        
    }

    protected abstract void action(Party targetPlayer);

    protected abstract void running();

    protected abstract void attackAnimation();

    /**
     * attacks party member if they are in the attack range
     */
    public void attack(){
        if(targetPlayer() != null){
            Party targetPlayer = targetPlayer();
            if(actionCounter == 0){

                action(targetPlayer);

                actionCounter = actionDelay;
            } else{
                actionCounter--;
            }
            attackAnimation();
        }
    }

    
    public void addedToWorld(World w)
    {
        hpBar = new SuperStatBar(maxHealth, maxHealth, this, 40, 5, 60, Color.YELLOW, Color.RED, true);
        getWorld().addObject(hpBar, 0, 0);
    }

    /**
     * check if there are any player in the attack range
     * @return Party  null if no player detected, the player detected if it's detected
     */
    public Party targetPlayer() {
        List<Party> players = getObjectsInRange(attackRange, Party.class);
        for (Party p : players) {
            if (!players.isEmpty() && !p.isDying) {
                return p; 
            }
        }

        return null;
    }
    
    protected abstract void repelOtherEnemies();
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
