import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Party class is the superclass for all player character classes.
 * 
 * @author Evan Ma, Arthur Tian
 * @version V1
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
    /**
    * Runs when party is added to world. Adds HP and Mana bars.
    */
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
    /**
    * The main action for a Party member. Either an attack or spell;
    * @param target The target of the Party member's main action. Use null if no target is given
    */
    protected abstract void mainAction(Enemy target);
    /**
    * Level up all stats for a party class
    */
    protected abstract void levelUpStats();
    /**
    * Play all the action animations for a class.
    */
    protected abstract void mainAnimation();
    /**
    * Play running animation for class.
    */    
    protected abstract void running();
    /**
    * Play idle animation for class
    */
    protected abstract void idle();
    
    /**
    * Act Method for Party Class
    */
    public void act()
    {
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
            
            Enemy targetEnemy = detectEnemy();
            if (targetEnemy != null) {
                mainAnimation();
            }
            else
            {
                inCombat = false;
            }
            
            // IF WE RANDOMLY START GETTING BUGS ABOUT PARTY MEMBERS IN COMBAT, FEEL FREE TO DELETE THIS BLOCK OF CODE
            // AND UNCOMMENT THE IDENTICAL VERSION BELOW
            if (!inCombat) {
                for (Party member : partyMembersInWorld()) {
                    if (member.getInCombat() == true) {
                        idle = true;
                    }
                }
                if (!idle) { //party members not idle, and not in combat, so run
                    runningSpeed = (int) speed;
                    running();
                }
                else { //party member is idle and not in combat. run idle animation
                    if (enemiesInWorld().size() == 0) {
                        idle = false;
                    }
                    idle();
                }
            }
            
            if (cohenExists) {
                    runningSpeed = 0;
                    inCombat = true;
                    actionCounter = actionDelay;
                    mainAnimation();    
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
                }
                /*
                if (!inCombat) {
                    for (Party member : partyMembersInWorld()) {
                        if (member.getInCombat() == true) {
                            idle = true;
                        }
                    }
                    if (!idle) { //party members not idle, and not in combat, so run
                        runningSpeed = (int) speed;
                        running();
                    }
                    else { //party member is idle and not in combat. run idle animation
                        if (enemiesInWorld().size() == 0) {
                            idle = false;
                        }
                        idle();
                    }
                }
                */
            }
            else
            {
                actionCounter--;
            }
        }
        
    }
    
    /**
    * Setter method for 'cohenExists' variable
    * 
    * @param b Set to true or false
    */
    public void setCohenExists(boolean b) {
        cohenExists = b;
    }

    /**
    * Checks if Party member is in combat or not. If in combat, don't go idle. If in combat, go idle.
    * 
    * 
    */
    public void setIdle() {
        if (!inCombat) {
            idle = true;
        }
        else {
            idle = false;
        }
    }
    /**
    * Returns the value of idle
    * 
    * @return boolean
    */
    public boolean isIdle() {
        return idle;
    }
    /**
    * Returns if the Party member is in combat.
    * return @boolean
    */
    protected boolean getInCombat() {
        return inCombat;
    }
    /**
    * Set party member to be in combat
    * @param b Value of isCombat
    */
    protected void setInCombat(boolean b) {
        inCombat = b;
    }

    /**
    * Returns Party member's running speed
    * 
    * @return int
    */
   
    public int getRunningSpeed() {
        return runningSpeed;
    }
    
    /**
    * Set Party member's running speed
    * 
    * @param speed The speed that the party member should be given.
    */
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
     * Spend mana for entity. Returns false if mana cost is too high.
     *
     * @param how much mana is taken away
     * @return boolean
     */
    public boolean spendMana(int cost)
    {
        if ((mana - cost) < 0)
        {
            return false;
        }
        else
        {
            mana -= cost;
            return true;
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
