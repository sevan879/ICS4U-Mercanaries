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
    private int actionChooser;
    private int attackCount;
    protected boolean attacking;
    protected int numOfActionsSoFar;
    protected boolean audioPlaying;
    private SuperStatBar hpBar;

    public Boss(int hp, int delay, int attackCount)
    {
        super(hp, 0, delay, false);
        this.attackCount = attackCount;
        actionChooser = -1;
        attacking = false;
        audioPlaying = false;
    }

    protected abstract void action(int attackNum);
    protected abstract void playAudio();
    protected abstract void idle();

    public void addedToWorld(World w)
    {
        hpBar = new SuperStatBar(maxHealth, maxHealth, null, 400, 25, 60, Color.YELLOW, Color.RED, false);
        getWorld().addObject(hpBar, getWorld().getWidth()/2, 50);
    }
    
    public void act()
    {
        hpBar.update(health);
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
                playAudio();
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
