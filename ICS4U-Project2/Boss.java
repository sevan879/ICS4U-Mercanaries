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
    private int n;
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
        n = 0;
    }

    protected abstract void action(int attackNum);
    
    protected abstract void idle();

    public void act()
    {
        attackLoop();
    }

    public void attackLoop()
    {
        if (actionCounter <= 0)
        {
            int selectedAttack = n;
            if (!animationIsRunning()) { //animation not running, no action currently taking place
                //Select an attack
                if (partyMembersInWorld().size() != 0) { //party members exist in the world
                    n = Greenfoot.getRandomNumber(4); //random number to choose which action to perform
                    actionCounter = actionDelay;
                    action(selectedAttack); //do the action
                }
                else {
                    //crazy OMG YOU LOST ANIMATION
                }
            }
            else {
                action(selectedAttack);
            }
        }
        else
        {
            actionCounter--;
        }
    }

    public Party targetPlayer() {
        List<Party> players = (ArrayList<Party>) (getWorld().getObjects(Party.class));

        if (!players.isEmpty()) {
            return players.get(Greenfoot.getRandomNumber(players.size())); 
        }

        return null;
    }
}
