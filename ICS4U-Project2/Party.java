import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Superclass for all player character classes
 * 
 * @author Evan Ma
 * @version V0
 */
public abstract class Party extends Entity
{
    private int experience;
    private int xpToLevel;
    private int xpIncreaseRate;
    private int level;
    private int maxLevel;
    protected int attackRange;
    protected int mana;
    protected int maxMana;
    protected boolean usesMana;

    protected int runningSpeed;
    protected boolean inCombat;
    protected boolean idle;
    protected int cohenAttackXPos;
    protected boolean cohenExists;

    private int manaRegenCounter;
    private static final int MANA_REGEN_DELAY = 30;

    private SuperStatBar hpBar;
    private SuperStatBar manaBar;

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
     * @param plrMana Max mana for player character
     */
    public Party(int hp, double spd, int delay, boolean movable, int xpIncreaseRate, int attackRange, int maxMana, int maxLevel, boolean manaClass)
    {
        super(hp, spd, delay, false);
        experience = 0;
        xpToLevel = 1;
        level = 0;
        this.xpIncreaseRate = xpIncreaseRate;
        this.attackRange = attackRange;
        this.maxMana = maxMana;
        mana = maxMana;
        this.maxLevel = maxLevel;
        manaRegenCounter = MANA_REGEN_DELAY;

        runningSpeed = 1;
        inCombat = false;
        idle = false;
        cohenExists = false;
        usesMana = manaClass;
    }

    protected abstract void mainAction(Enemy target);

    protected abstract void levelUpStats();

    protected abstract void mainAnimation();

    public void act()
    {
        super.act();
        if (health <= 0) { //remove hp bar if dying (dying animation)
            death();
            if(hpBarExists) {
                //getWorld().removeObject(hpBar);
                hpBarExists = false;
            }
        }
        else {
            hpBar.update(health);
            if (usesMana)
            {
                manaBar.update(mana);
            }
            passiveManaRegen();
            if (cohenExists) {
                runningSpeed = 0;
                inCombat = true;
                actionCounter = actionDelay;
                mainAnimation();    
            }
            else {
                Enemy targetEnemy = detectEnemy();
                if (targetEnemy != null) {
                    mainAnimation();
                }
                else
                {
                    inCombat = false;
                    setLocation(ogX, ogY);
                }
                if (!inCombat) {
                    for (Party member : partyMembersInWorld()) {
                        if (member.getInCombat() == true) {
                            idle = true;
                            setLocation(ogX, ogY);
                        }
                    }
                    if (!idle) { //party members not idle, and not in combat, so run
                        runningSpeed = (int) speed;
                        running();
                        setLocation(ogX, ogY);
                    }
                    else { //party member is idle and not in combat. run idle animation
                        if (enemiesInWorld().size() == 0) {
                            idle = false;
                        }
                        idle();
                    }
                }
                if (actionCounter <= 0) 
                {

                    if (targetEnemy != null) //enemy detected, pause background scrolling and enter combat
                    {
                        runningSpeed = 0;
                        inCombat = true;
                        actionCounter = actionDelay;
                        mainAction(targetEnemy);
                    }
                    else { // no enemy detected by this party member
                        inCombat = false;
                        setLocation(ogX, ogY);
                    }
                }
                else
                {
                    actionCounter--;
                }
            }
        }
    }

    public void setCohenExists(boolean b) {
        cohenExists = b;
    }

    public void setIdle() {
        if (!inCombat) {
            idle = true;
        }
        else {
            idle = false;
        }
    }

    public boolean isIdle() {
        return idle;
    }

    public void addedToWorld(World w)
    {
        hpBar = new SuperStatBar(maxHealth, maxHealth, this, 50, 7, 60, Color.GREEN, Color.RED, false);
        getWorld().addObject(hpBar, 0, 0);

        if (usesMana)
        {
            manaBar = new SuperStatBar(maxMana, maxMana, this, 50, 7, 70, Color.BLUE, Color.BLACK, false);
            getWorld().addObject(manaBar, 0, 0);
        }
    }

    protected abstract void running();

    protected abstract void idle();

    protected boolean getInCombat() {
        return inCombat;
    }

    protected void setInCombat(boolean b) {
        inCombat = b;
    }

    //speed getter
    public int getRunningSpeed() {
        return runningSpeed;
    }

    //speed setter  
    public void setRunningSpeed(int speed) {
        runningSpeed = speed;
    }

    /**
     * Method for giving XP to player class. Levels up the player if xp requirement is met
     *
     * @param xp The amount of xp given to player;
     */
    public void giveXP(int xp)
    {
        if (level == maxLevel)
        {
            return;
        }
        if (experience + xp >= xpToLevel)
        {
            levelUP(xp);
        }
        else
        {
            experience += xp;
        }
    }

    /**
     * Method for finding the closest enemy in attack range.
     *
     * @return Enemy 
     */
    private Enemy detectEnemy()
    {
        ArrayList<Enemy> targetList = (ArrayList<Enemy>) (getWorld().getObjects(Enemy.class));
        Enemy target = null;

        for (Enemy e : targetList)
        {
            double distanceFromE = Math.hypot(getX() - e.getX(), getY() - e.getY());
            if (distanceFromE <= attackRange)
            {
                if (target == null)
                {
                    target = e;
                }
                else
                {
                    double distanceFromTarget = Math.hypot(getX() - target.getX(), getY() - target.getY());
                    if (distanceFromE < distanceFromTarget && !isDying && !target.checkDying())
                    {
                        target = e;
                    }
                }
            }
        }

        return target;
    }

    public int getCohenAttackXPos() {
        return cohenAttackXPos;
    }

    /**
     * Spend mana for entity. Returns false if mana cost is too high.
     *
     * @param how much mana is taken away
     * @return boolean
     */
    public boolean spendMana(int cost)
    {
        if (mana - cost < 0)
        {
            return false;
        }
        else
        {
            mana -= cost;
            return true;
        }
    }

    private void passiveManaRegen()
    {
        if (manaRegenCounter <= 0 && mana < maxMana)
        {
            manaRegenCounter = MANA_REGEN_DELAY;
            mana ++;
        }
        else
        {
            manaRegenCounter--;
        }
    }

    //When an enemy appears behind the player
    private void changeDirection()
    {

    }
    // increase stats when level up
    private void levelUP(int xp)
    {
        experience = experience + xp - xpToLevel;
        level ++;
        xpToLevel += xpIncreaseRate;

        levelUpStats();
    }
}
