import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
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
    public Enemy(int hp, int spd, int delay, int dmg, boolean movable, int xpIncreaseRate, int attackRange)
    {
        super(hp, spd, delay, dmg, false);
        experience = 0;
        xpToLevel = 1;
        level = 0;
        this.xpIncreaseRate = xpIncreaseRate;
        this.attackRange = attackRange;
    }
}
