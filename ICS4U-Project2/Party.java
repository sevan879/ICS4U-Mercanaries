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
        
        usesMana = manaClass;
    }
    
    protected abstract void mainAction(Enemy target);
    
    protected abstract void levelUpStats();
    
    public void act()
    {
        hpBar.update(health);
        if (usesMana)
        {
            System.out.println(mana);
            manaBar.update(mana);
        }
        passiveManaRegen();
        Enemy checkEnemy = detectEnemy();
        
        if (actionCounter <= 0)
        {
            Enemy targetEnemy = detectEnemy();
            if (targetEnemy != null) //enemy detected, pause background scrolling and enter combat
            {
                actionCounter = actionDelay;
                mainAction(targetEnemy);
                runningSpeed = 0;
                inCombat = true;
                
            }
            else {
                runningSpeed = 1;
                inCombat = false;
            }
            if(!inCombat) { //code that determines whether or not the enemies have been slain, makes in combat false
                runningSpeed = 1;
                running();
            }
        }
        else
        {
            actionCounter--;
        }
    }
    
    public void act(boolean isSupport)
    {
        hpBar.update(health);
        if (usesMana)
        {
            System.out.println(mana);
            manaBar.update(mana);
        }
        passiveManaRegen();
        
        if (actionCounter <= 0)
        {
            mainAction(null);
            actionCounter = actionDelay;
            Enemy targetEnemy = detectEnemy();
        }
        else
        {
            actionCounter--;
        }
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
    
    public void setInCombat() {
        inCombat = true;
    }
    //speed getter
    public int getRunningSpeed() {
        return runningSpeed;
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
        ArrayList<Enemy> targetList = (ArrayList<Enemy>) (getWorld  ().getObjects(Enemy.class));
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
                    if (distanceFromE < distanceFromTarget)
                    {
                        target = e;
                    }
                }
            }
        }
        
        return target;
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
