import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Superclass for all player character classes
 * 
 * @author Evan Ma
 * @version V0
 */
public abstract class Player extends Entity
{
    private int experience;
    private int xpToLevel;
    private int xpIncreaseRate;
    private int level;
    protected int attackRange;
    /**
    * Main Constructor for Player Class
    *
    * @param hp entity's health
    * @param spd entity's speed
    * @param delay delay between actions
    * @param dmg damage that entity does
    * @param moveable can the entity move around
    * @param xpIncreaseRate how much the required XP to level increases after each Level
    * @param attackRange Range of attacking enemies
    */
    public Player(int hp, int spd, int delay, int dmg, boolean movable, int xpIncreaseRate, int attackRange)
    {
        super(hp, spd, delay, dmg, false);
        experience = 0;
        xpToLevel = 1;
        level = 0;
        this.xpIncreaseRate = xpIncreaseRate;
        this.attackRange = attackRange;
    }
    
    public void act()
    {
        if (actionCounter <= 0)
        {
            actionCounter = actionDelay;
            // do action
        }
        else
        {
            actionCounter--;
        }
    }
    /**
    * Method for giving XP to player class. Levels up the player if xp requirement is met
    *
    * @param xp The amount of xp given to player;
    */
    public void giveXP(int xp)
    {
        if (experience + xp >= xpToLevel)
        {
            experience = experience + xp - xpToLevel;
            level ++;
            xpToLevel += xpIncreaseRate;
        }
        else
        {
            experience += xp;
        }
    }
    /**
    * Method for finding the closest enemy in attack range.
    *
    * @return Enemy 
    */
    private Enemy detectEnemy()
    {
        ArrayList<Enemy> targetList = (ArrayList<Enemy>) (getWorld().getObjects(Enemy.class));
        Enemy target = null;
        
        for (Enemy e : targetList)
        {
                double distanceFromE = Math.hypot(getX() - e.getX(), getY() - e.getY());
                if (distanceFromE <= attackRange)
                {
                    if (target == null)
                    {
                        target = e;
                    }
                    else
                    {
                        
                    double distanceFromTarget = Math.hypot(getX() - target.getX(), getY() - target.getY());
                    if (distanceFromE < distanceFromTarget)
                    {
                        target = e;
                    }
                }
            }
        }
    }
}
