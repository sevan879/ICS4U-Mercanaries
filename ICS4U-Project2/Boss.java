import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Boss here.
 * 
 * @author Arthur Tian, Evan Ma
 * @version V1
 */
public abstract class Boss extends Entity
{
    private int actionChooser;
    private int attackCount;
    protected boolean attacking;
    protected int numOfActionsSoFar;
    
    private SuperStatBar hpBar;
    /**
     * Main constructor for Boss Class
     * @param hp Health of boss
     * @param delay Frames between attacks for each boss attack.
     * @param attackCount The amount of attacks the boss has.
     */
    public Boss(int hp, int delay, int attackCount)
    {
        super(hp, 0, delay, false);
        this.attackCount = attackCount;
        actionChooser = -1;
        attacking = false;
    }
    /**
     * Do the main actions for the boss.
     * @param attackNum the specific attack to do.
     */
    protected abstract void action(int attackNum);
    /**
     * Play the idle animation for boss.
     */
    protected abstract void idle();
    /**
     * Runs when added to world. Create HP bar.
     */
    public void addedToWorld(World w)
    {
        hpBar = new SuperStatBar(maxHealth, maxHealth, null, 400, 25, 60, Color.YELLOW, Color.RED, false);
        getWorld().addObject(hpBar, getWorld().getWidth()/2, getWorld().getHeight()*2/7);
    }
    /**
     * act method for Boss.
     */
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

    private void attackLoop()
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
    /**
     * Targets a random Party class in the world
     */
    protected Party targetPlayer() {
        List<Party> players = (ArrayList<Party>) (getWorld().getObjects(Party.class));

        if (!players.isEmpty()) {
            return players.get(Greenfoot.getRandomNumber(players.size())); 
        }

        return null;
    }

}
