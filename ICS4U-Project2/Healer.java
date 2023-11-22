import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Healer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healer extends Party
{
    //BASE STATS AT LEVEL ONE
    private static final int SET_HP = 4;
    private static final double SET_SPEED = 3;
    private static final int ACTION_DELAY = 150; // amount of acts
    private static final int XP_INCREASE_PER_LEVEL = 1;
    private static final int ATTACK_RANGE = 160;
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 4;
    
    //stats that are increased on each level up
    private static final int DAMAGE_INCREASE = 1; 
    private static final int HEALTH_INCREASE = 2; 
    private static final int RANGE_INCREASE = 0;
    private static final int MANA_INCREASE = 0;
    
    private int spellLevel = 0;
    
    private int damage = 5;
    
    private int smallSpellMana;
    private int bigSpellMana;
    
    private GreenfootSound [] healing;//sounds for healing 
    
    public Healer()
    {
        super(SET_HP, SET_SPEED, ACTION_DELAY, false, XP_INCREASE_PER_LEVEL, ATTACK_RANGE, MAX_MANA, MAX_LEVEL);
        
        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        healing = new GreenfootSound[3];
        for(int i = 0; i < healing.length; i++){
            healing[i] = new GreenfootSound("Heal.mp3");
        }
        
    }
    
    public void act()
    {
        super.act();
    }
    
    protected void mainAction(Enemy target)
    {
        Party healTarget = findHealTarget();
        if (healTarget != null)
        {
            if (Greenfoot.getRandomNumber(6) == 0)
            {
                bigHeal();
            }
            else
            {
                smallHeal(healTarget);
            }
        }
    }
    
    protected void levelUpStats()
    {
        maxHealth += HEALTH_INCREASE;
        health = maxHealth;
        
        maxMana += MANA_INCREASE;
        mana = maxMana;
        
        damage += DAMAGE_INCREASE;
        attackRange += RANGE_INCREASE;
    }
    
    private void smallHeal(Party healTarget)
    {
        
        spendMana(smallSpellMana);
        healTarget.healDmg(damage);
        
    }
    
    private void bigHeal()
    {
        spendMana(bigSpellMana);
        ArrayList<Party> pList= (ArrayList<Party>)(getWorld().getObjects(Party.class));
        for (Party p : pList)
        {
            p.healDmg(damage);
        }
    }
    
    private Party findHealTarget()
    {
        ArrayList<Party> pList= (ArrayList<Party>)(getWorld().getObjects(Party.class));
        
        Party target = null;
        
        for (Party p : pList)
        {
            if (target == null)
            {
                target = p;
            }
            else
            {
                if (p.getHP() < target.getHP())
                {
                    target = p;
                }
            }
        }
        
        return target;
    }
    
    public void running()
    {
        
    }
    
    public void idle() {
        
    }
}
