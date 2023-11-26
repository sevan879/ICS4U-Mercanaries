import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Healer class. Descendant of party class. Healer uses mana to heal their teammates. Healer has two heal spells, one that is single target, and one that affects all teammates.
 * 
 * @author Evan Ma, Arthur Tian
 * @version V1
 */
public class Healer extends Party
{
    //BASE STATS AT LEVEL ONE
    private static final int SET_HP = 80;
    private static final double SET_SPEED = 3;
    private static final int ACTION_DELAY = 120; // amount of acts
    private static final int XP_INCREASE_PER_LEVEL = 1;
    private static final int ATTACK_RANGE = 580;
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 4;
    private static final boolean MANA_CLASS = true;

    //stats that are increased on each level up
    private static final int DAMAGE_INCREASE = 1; 
    private static final int HEALTH_INCREASE = 2; 
    private static final int RANGE_INCREASE = 0;
    private static final int MANA_INCREASE = 0;

    private int spellLevel = 0;

    private int damage = 25; // damage to heal

    private int smallSpellMana; // mana for small heal
    private int bigSpellMana; // mana for big heal

    private GreenfootSound [] healing;//sounds for healing 

    private GreenfootImage[] attackOnePics;
    private int attackOneAnimationIndex;
    private int attackOneAnimationDelay;
    private int attackOneAnimationCounter;

    private GreenfootImage[] attackTwoPics;
    private int attackTwoAnimationIndex;
    private int attackTwoAnimationDelay;
    private int attackTwoAnimationCounter;

    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;
    
    /**
    * Main Constructor for Knight Class
    */
    public Healer()
    {
        super(SET_HP, SET_SPEED, ACTION_DELAY, false, XP_INCREASE_PER_LEVEL, ATTACK_RANGE, MAX_MANA, MAX_LEVEL, MANA_CLASS);

        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        healing = new GreenfootSound[3];
        for(int i = 0; i < healing.length; i++){
            healing[i] = new GreenfootSound("Heal.mp3");
        }

        smallSpellMana = 15; // 15
        bigSpellMana = 40; // 30
        setImage(runningPics[0]);
        animationConstructor();
    }

    public void act()
    {
        super.act();
        //inCombat = false;
    }
    /**
    * The main action for a Party member. Either an attack or spell;
    * @param target The target of the Party member's main action. Use null if no target is given
    */
    protected void mainAction(Enemy target)
    {
        Party healTarget = findHealTarget();
        //System.out.println(healTarget);
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
    /**
    * Play all the action animations for a class.
    */
    protected void mainAnimation() {
        attackOne();
    }
    /**
    * Level up all stats for a party class
    */    
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

        if (spendMana(smallSpellMana))
        {
            healTarget.healDmg(damage);
        }
    }

    private void bigHeal()
    {
        if (spendMana(bigSpellMana))
        {
            ArrayList<Party> pList= (ArrayList<Party>)(getWorld().getObjects(Party.class));
            for (Party p : pList)
            {
                p.healDmg(damage);
            }
        }
    }

    private Party findHealTarget()
    {
        ArrayList<Party> pList= (ArrayList<Party>)(getWorld().getObjects(Party.class));

        Party target = null;
        boolean partyIsFullHP = true;
        for (Party p : pList)
        {
            if (!p.checkFullHP())
            {
                partyIsFullHP = false;
            }
        }
        if (partyIsFullHP)
        {
            return target;
        }

        for (Party p : pList)
        {
            if (target == null)
            {
                target = p;
            }
            else
            {
                if (p.getHP()/p.getMaxHP() < target.getHP()/target.getMaxHP())
                {
                    target = p;
                }
            }
        }

        return target;
    }
    /**
    * Plays idle animation.
    */
    public void idle() {
    }
    /**
    * Plays first attack animation.
    */
    public void attackOne() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackOneAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackOneAnimationCounter = attackOneAnimationDelay; // reset counter to max 
            attackOneAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackOneAnimationIndex == attackOnePics.length){
                attackOneAnimationIndex = 0;
                animationTracker++;
            }
            // Apply new image to this Actor
            setImage (attackOnePics[attackOneAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }
    /**
    * Plays second attack animation.
    */
    public void attackTwo() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackTwoAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackTwoAnimationCounter = attackTwoAnimationDelay; // reset counter to max 
            attackTwoAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackTwoAnimationIndex == attackTwoPics.length){
                attackTwoAnimationIndex = 0;
                animationTracker++;
            }
            // Apply new image to this Actor
            setImage (attackTwoPics[attackTwoAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackTwoAnimationCounter--;
        }
    }
    /**
    * Plays death animation and remove object from world.
    */
    public void death() {
        boolean remove = false;
        isDying = true;
        if (deathAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            deathAnimationCounter = deathAnimationDelay; // reset counter to max 
            deathAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (deathAnimationIndex == deathPics.length){
                deathAnimationIndex = 0;
                remove = true;
            }
            // Apply new image to this Actor
            setLocation(getX(), getY()+5);
            setImage (deathPics[deathAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            deathAnimationCounter--;
        }
        if (remove) {
            getWorld().removeObject(this);
        }
    }
    /**
    * Plays running animation
    */
    public void running() {
        if (runningAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            runningAnimationCounter = runningAnimationDelay; // reset counter to max 
            runningAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (runningAnimationIndex == runningPics.length){
                runningAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (runningPics[runningAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            runningAnimationCounter--;
        }
    }
    
    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;

        //Animation
        attackOnePics = new GreenfootImage[7];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("HA1" + (i+1) + ".png");
            attackOnePics[i].scale(attackOnePics[i].getWidth()*2, attackOnePics[i].getHeight()*2);
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 8;
        attackOneAnimationCounter = attackOneAnimationDelay;

        attackTwoPics = new GreenfootImage[4];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("KA2" + (i+1) + ".png");
            attackTwoPics[i].scale(attackTwoPics[i].getWidth()*2, attackTwoPics[i].getHeight()*2);
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 8;
        attackTwoAnimationCounter = attackTwoAnimationDelay;

        //death
        deathPics = new GreenfootImage[5];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("HD" + (i+1) + ".png");
            deathPics[i].scale(deathPics[i].getWidth()*2, deathPics[i].getHeight()*2);
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 10;
        deathAnimationCounter = deathAnimationDelay;

        //running
        runningPics = new GreenfootImage[8];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("HR" + (i+1) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 8;
        runningAnimationCounter = runningAnimationDelay;
    }
}
