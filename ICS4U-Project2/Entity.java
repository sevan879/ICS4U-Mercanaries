import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Entity class, superclass for all enemy and player classes.
 * 
 * @author Evan Ma
 * @version V1
 */
public abstract class Entity extends SuperSmoothMover
{
    //Base stats
    protected int health;
    protected int maxHealth;
    protected double speed;
    protected double maxSpeed;
    // Action delay
    protected int actionDelay;
    protected int actionCounter;
    private boolean canMove;
    //arthurs convenience
    protected boolean animationRunning;
    protected boolean isDying;
    protected boolean hpBarExists;
    protected int animationTracker; // odd = running, even = not running
    protected int attackTracker; // 0, 1, 2 to decide which attack animation to use
    /**
     * Main Constructor for Entity Class
     *
     * @param hp entity's health
     * @param spd entity's speed
     * @param delay delay between actions
     * @param dmg damage that entity does
     * @param moveable can the entity move around
     */
    public Entity(int hp, double spd, int delay, boolean movable)
    {
        maxHealth = hp;
        health = maxHealth;
        speed = spd;
        maxSpeed = speed;
        actionDelay = delay;
        actionCounter = actionDelay;
        canMove = movable;
        isDying = false;
        hpBarExists = true;
        animationConstructor();
    }
    /**
    * Plays death animation and remove object from world.
    */
    protected abstract void death();
    /**
    * Initializes all animations for class
    */
    protected abstract void animationConstructor();

    /**
     * Take damage for entity
     *
     * @param dmg Amount of damage entity takes.
     */
    public void takeDamage(int dmg)
    {
        getWorld().addObject(new DamageNumbers(dmg, true), getX() - 25 + Greenfoot.getRandomNumber(50), getY() - 50);
        health -= dmg;
    }
    /**
    * Checks if Entity is running
    * return @boolean
    */
    protected boolean animationIsRunning() {
        return animationTracker %2 == 1; //this means that the animation is running, 
    }
    /**
    * Returns an ArrayList<Party> of all party members in the world
    * @return ArrayList<Party>
    */
    protected ArrayList<Party> partyMembersInWorld() {
        ArrayList<Party> partyList = (ArrayList<Party>) (getWorld().getObjects(Party.class));
        return partyList;
    }
    /**
    * Returns an ArrayList<Enemy> of all enemies in the world
    * @return ArrayList<Enemy>
    */
    protected ArrayList<Enemy> enemiesInWorld() {
        ArrayList<Enemy> enemyList = (ArrayList<Enemy>) (getWorld().getObjects(Enemy.class));
        return enemyList;
    }

    /**
     * Heal damage for entity
     *
     * @param heal Amount of damage entity heals.
     */
    public void healDmg(int heal)
    {
        
        getWorld().addObject(new DamageNumbers(heal, false), getX() - 10 + Greenfoot.getRandomNumber(20), getY() - 50);
        if (health + heal > maxHealth)
        {
            health = maxHealth;
        }
        else
        {
            health += heal;
        }
    }

    /**
     * Check HP for Entity
     *
     * @return int
     */
    public int getHP()
    {
        return health;
    }
    
    /**
    * Check Max HP for Entity
    *
    * @return int
    */
    public int getMaxHP()
    {
        return maxHealth;
    }
    
    
    /**
    * Check if Entity is at Max HP
    *
    * @return boolean
    */
    public boolean checkFullHP()
    {
        if (health - maxHealth != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
    * Returns if the Entity is dying
    *
    * @return boolean
    */
    public boolean checkDying()
    {
        return isDying;
    }
}
