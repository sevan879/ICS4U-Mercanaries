import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    protected int damage;
    // Action delay
    protected int actionDelay;
    protected int actionCounter;
    private boolean canMove;
    /**
    * Main Constructor for Entity Class
    *
    * @param hp entity's health
    * @param spd entity's speed
    * @param delay delay between actions
    * @param dmg damage that entity does
    * @param moveable can the entity move around
    */
    public Entity   (int hp, int spd, int delay, int dmg, boolean movable)
    {
        maxHealth = hp;
        health = maxHealth;
        speed = spd;
        maxSpeed = speed;
        actionDelay = delay;
        actionCounter = actionDelay;
        damage = dmg;
        canMove = movable;
    }
    /**
    * Take damage for entity
    *
    * @param dmg Amount of damage entity takes.
    */
    public void takeDamage(int dmg)
    {
        health -= dmg;
        if (health <= 0)
        {
            getWorld().removeObject(this);
            //dying animation
        }
    }
    /**
    * Heal damage for entity
    *
    * @param heal Amount of damage entity heals.
    */
    public void healDmg(int heal)
    {
        if (health + heal > maxHealth)
        {
            health = maxHealth;
        }
        else
        {
            health += heal;
        }
    }
}
