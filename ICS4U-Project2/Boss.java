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
    private int actionChooser;
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
        actionChooser = 0;
    }

    protected abstract void action(int attackNum);

    protected abstract void idle();

    public void act()
    {
        attackLoop();
    }

    public void attackLoop()
    {
        /**
         *         if (actionCounter <= 0)
        {
            actionChooser = Greenfoot.getRandomNumber(8);
            if (!animationIsRunning()) { //animation not running, no action currently taking place
                //Select an action
                //random number to choose which action to perform
                action(actionChooser); //do the action, start animation

            }
            else { //animation running, continue doing the frames bruhhhh

                action(actionChooser);
                actionCounter = actionDelay;
            }
        }
        else
        {
            if (!animationIsRunning()) {
                idle();
            }
            actionCounter--;
        }
         */
    }

    public Party targetPlayer() {
        List<Party> players = (ArrayList<Party>) (getWorld().getObjects(Party.class));

        if (!players.isEmpty()) {
            return players.get(Greenfoot.getRandomNumber(players.size())); 
        }

        return null;
    }

}
