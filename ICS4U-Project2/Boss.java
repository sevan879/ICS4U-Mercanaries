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
    private int attackCount;
    protected boolean attacking;
    protected int numOfActionsSoFar;

    public Boss(int hp, int delay, int attackCount)
    {
        super(hp, 0, delay, false);
        this.attackCount = attackCount;
        actionChooser = -1;
        attacking = false;
    }

    protected abstract void action(int attackNum);

    protected abstract void idle();

    public void act()
    {
        if (health > 0) {
        attackLoop();
        }
        else {
            death();
        }
    }

    public void attackLoop()
    {
        if (actionCounter <= 0)
        {
            if (actionChooser == -1)
            {

                actionChooser = Greenfoot.getRandomNumber(attackCount);
                action(actionChooser);
            }
            if (attacking)
            {
                action(actionChooser);
            }
            else
            {
                actionCounter = actionDelay;
                actionChooser = -1;
            }
        }
        else
        {
            idle();
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
