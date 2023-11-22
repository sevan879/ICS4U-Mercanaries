import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Entity
{
    private SuperStatBar hpBar;
    
    /**
     * Main constructor for Enemy class
     * 
     * @param hp enemie's health
     * @param delay delay between actions
     * @param movable can the enemy move around
     */
    
    public Boss(int hp, int delay)
    {
        super(hp, 0, delay, false);
    }
}
