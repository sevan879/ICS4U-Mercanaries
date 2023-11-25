import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.*;

/**
 * Enemy class, superclass for all enemie classes
 * 
 * @author Kenneth Jin
 * @version V0
 */
public abstract class Enemy extends Entity
{
    protected int attackRange;
    protected boolean rangedEnemy;
    
    protected boolean attacking;
    protected boolean canAttack;
    
    private int reloadTimer;
    
    private SuperStatBar hpBar;

    /**
     * Main constructor for Enemy class
     * 
     * @param hp enemie's health
     * @param spd enemie's speed
     * @param delay delay between actions
     * @param dmg enemie's damage
     * @param movable can the enemy move around
     * @param attackRange enemie's attack range
     */

    public Enemy(int hp, double spd, int delay, int dmg, boolean movable, int attackRange)
    {
        super(hp, spd, delay, true);
        this.attackRange = attackRange;
        attacking = false;
        canAttack = true;
        reloadTimer = (2*actionDelay)/3;
    }

    protected List<Party> playersUpClose(){
        List<Party> fullDamage = getObjectsInRange(attackRange, Party.class);
        return fullDamage;
    }

    protected List<Party> playersFurtherAway(){
        List<Party> halfDamage = getObjectsInRange(attackRange + 25, Party.class);
        return halfDamage;
    }

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (health <= 0) {
            death();
            if (hpBarExists) {
                getWorld().removeObject(hpBar);
                hpBarExists = false;
            }
        }
        else {
            hpBar.update(health);
            if (rangedEnemy)
            {
                if (canAttack)
                {
                    rangedAttack();
                }
                else // if cant attack, reload
                {
                    if(reloadTimer == 0){
                        reloadTimer = (actionDelay*2)/3;
                        canAttack = true;
                    } else{
                        reloadTimer--;
                    }
                }
                
                if (!attacking)
                {
                    //System.out.println("moving");
                    move(false);
                }
            }
            else
            {
                move(true);
                attack();
            }
        }
    }
    // if boolean is set to true, dont move if player is in range
    public void move(boolean compareRange){
        //if no player is detected within attack range, move 
        if (compareRange)
        {
            if(targetPlayer() == null){
                setLocation(getX() - speed, getY());
                //moving animation
                running();
            }
        }
        else
        {
            ArrayList<Party> partyList = (ArrayList<Party>) (getWorld().getObjects(Party.class));
            for (Party p : partyList)
            {
                double distanceFromP = Math.hypot(getX() - p.getX(), getY() - p.getY());
                if (distanceFromP <= 20)
                {
                    return;
                }
            }
            setLocation(getX() - speed, getY());
            running();
        }
    }

    protected abstract void action(Party targetPlayer);

    protected abstract void running();

    protected abstract void attackAnimation();

    protected void attack(){
        if(targetPlayer() != null){
            Party target = targetPlayer();
            if(actionCounter == 0){

                action(target);

                actionCounter = actionDelay;
            } else{
                actionCounter--;
            }
            attackAnimation();
        }
    }
    
    protected void rangedAttack()
    {
        Party target = targetRandomPlayer();
        if (target != null)
        {
            attacking = true;
            if(actionCounter == 0){
                
                action(target);

                actionCounter = actionDelay;
                attacking = false;
                canAttack = false;
            } else{
                actionCounter--;
            }
            attackAnimation();
        }
        else
        {
            attacking = false;
        }
    }

    public void addedToWorld(World w)
    {
        hpBar = new SuperStatBar(maxHealth, maxHealth, this, 40, 5, 60, Color.YELLOW, Color.RED, true);
        getWorld().addObject(hpBar, 0, 0);
    }

    protected Party targetPlayer() {
        List<Party> players = getObjectsInRange(attackRange, Party.class);
        
        Party target = null;
        
        for (Party p : players) {
            double distanceFromP = Math.hypot(getX() - p.getX(), getY() - p.getY());
            if (!players.isEmpty() && !p.isDying) {
                if (target == null)
                {
                    target = p;
                }
                else
                {
                    double distanceFromTarget = Math.hypot(getX() - target.getX(), getY() - target.getY());
                    if (distanceFromP < distanceFromTarget && !p.checkDying())
                    {
                        target = p;
                    }
                }
            }
        }

        return target;
    }
    
    protected Party targetRandomPlayer() {
        List<Party> players = getObjectsInRange(attackRange, Party.class);
        if (!players.isEmpty())
        {
            return (players.get(Greenfoot.getRandomNumber(players.size())));
        }

        return null;
    }
    
    protected abstract void repelOtherEnemies();
}
