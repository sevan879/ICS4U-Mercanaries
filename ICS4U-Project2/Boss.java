import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Boss extends Entity
{
    private SuperStatBar hpBar;
    protected int attackIDs[];
    
    /**
     * Main constructor for Enemy class
     * 
     * @param hp enemie's health
     * @param delay delay between actions
     * @param movable can the enemy move around
     */
    
    public Boss(int hp, int delay, int[] attackList)
    {
        super(hp, 0, delay, false);
        attackIDs = attackList;
    }
    
    protected abstract void action(int attackNum);
    
    public void act()
    {
        attackLoop();
    }
    
    public void attackLoop()
    {
        if (targetPlayer() != null)
        {
            if (actionCounter <= 0)
            {
                actionCounter = actionDelay;
                //Select an attack
                int selectedAttack = Greenfoot.getRandomNumber(attackIDs.length-1);
                action(selectedAttack);
            }
            else
            {
                actionCounter--;
            }
        }
    }
    
    public Party targetPlayer() {
        List<Party> players = (ArrayList<Party>) (getWorld().getObjects(Party.class));

        if (!players.isEmpty()) {
            return players.get(Greenfoot.getRandomNumber(players.size() - 1)); 
        }

        return null;
    }
}
