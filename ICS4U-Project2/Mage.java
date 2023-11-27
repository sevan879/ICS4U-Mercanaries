import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ranged spell caster class. Uses mana and has two seperate spells. A single target fireball and an AOE large fireball.
 * 
 * @author Evan Ma, Arthur Tian
 * @version V1
 */
public class Mage extends Party
{
    //BASE STATS AT LEVEL ONE
    private static final int SET_HP = 100;
    private static final double SET_SPEED = 3;
    private static final int ACTION_DELAY = 50; // amount of acts
    private static final int XP_INCREASE_PER_LEVEL = 1;
    private static final int ATTACK_RANGE = 550;
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 4;
    private static final boolean MANA_CLASS = true;

    //stats that are increased on each level up
    private static final int DAMAGE_INCREASE = 1; 
    private static final int HEALTH_INCREASE = 2; 
    private static final int RANGE_INCREASE = 0;
    private static final int MANA_INCREASE = 0;

    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private GreenfootImage[] attackOnePics;
    private int attackOneAnimationIndex;
    private int attackOneAnimationDelay;
    private int attackOneAnimationCounter;

    private GreenfootImage[] attackTwoPics;
    private int attackTwoAnimationIndex;
    private int attackTwoAnimationDelay;
    private int attackTwoAnimationCounter;

    private GreenfootImage[] idlePics;
    private int idleAnimationIndex;
    private int idleAnimationDelay;
    private int idleAnimationCounter;

    private int spellLevel = 0;

    private int smallDamage = 10;
    private int bigDamage = 20;
    private int smallRange = 100;
    private int bigRange = 250;

    private int smallSpellMana;
    private int bigSpellMana;

    private boolean canAttack;
    private boolean manaSpent;
    
    private int spellDelay;
    private int spellCDCounter;
    /**
    * Main Constructor for Mage Class
    */
    public Mage()
    {
        super(SET_HP, SET_SPEED, ACTION_DELAY, false, XP_INCREASE_PER_LEVEL, ATTACK_RANGE, MAX_MANA, MAX_LEVEL, MANA_CLASS);
        smallSpellMana = 15;
        bigSpellMana = 25;
        canAttack = true;
        manaSpent = false;
        spellDelay = ACTION_DELAY;
        spellCDCounter = ACTION_DELAY;
    }

    public void act()
    {
        super.act();
    }

    protected void mainAction(Enemy target)
    {
        /*
        if (Greenfoot.getRandomNumber(3) == 0)
        {
            bigSpell();
        }
        else
        {
            smallSpell();
        }
        */
    }

    protected void mainAnimation() {
        if (attackTracker == 0 && !manaSpent)
        {
            if (spendMana(smallSpellMana))
            {
                manaSpent = true;
                //System.out.println("spent Mana on small");
            }
        }
        else if (attackTracker == 1 && !manaSpent)
        {
            if (spendMana(bigSpellMana))
            {
                manaSpent = true;
                //System.out.println("spent Mana on big");
            }
        }
        if (canAttack && manaSpent)
        {
            if (attackTracker == 0) { //attack one
                attackOne();
                if (!animationIsRunning() && Greenfoot.getRandomNumber(2) == 0) {
                    attackTracker = 1;
                }
            }
            else if (attackTracker == 1) { //attack two
                attackTwo();
                if (!animationIsRunning()) {
                    attackTracker = 0;
                }
            }
            
        }
        else
        {
            if (spellCDCounter == 0)
            {
                spellCDCounter = spellDelay;
                canAttack = true;
            }
            else
            {
                spellCDCounter --;
            }
        }
        
    }


    protected void levelUpStats()
    {
        maxHealth += HEALTH_INCREASE;
        health = maxHealth;

        maxMana += MANA_INCREASE;
        mana = maxMana;

        smallDamage += DAMAGE_INCREASE;
        bigDamage += DAMAGE_INCREASE;
        attackRange += RANGE_INCREASE;
    }

    private void smallSpell()
    {
        getWorld().addObject(new SmallFireball(smallDamage), getX(), getY());
    }

    private void bigSpell()
    {
        getWorld().addObject(new Fireball(bigRange, bigDamage), getX(), getY());
    }
    /**
    * Plays idle animation
    */
    public void idle() {
        if (idleAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            idleAnimationCounter = idleAnimationDelay; // reset counter to max 
            idleAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (idleAnimationIndex == idlePics.length){
                idleAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (idlePics[idleAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            idleAnimationCounter--;
        }
    }
    /**
    * Plays first attack animation. Also launches fireball.
    */
    private void attackOne() {
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
                smallSpell();
                canAttack = false;
                manaSpent = false;
            }
            if (attackOneAnimationIndex == 2) {
                setLocation(getX()-5, getY());
            }
            if (attackOneAnimationIndex == 3) {
                setLocation(getX()+5, getY());
            }
            // Apply new image to this Actor
            setImage (attackOnePics[attackOneAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }
    /**
    * Plays second attack animation. Also launches fireball.
    */
    private void attackTwo() {
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
                canAttack = false;
                manaSpent = false;
            }
            if (attackTwoAnimationIndex == 3) {
                setLocation(getX()+0, getY());
            }
            if (attackTwoAnimationIndex == 8)
            {
                bigSpell();
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
    * Plays animation for running.
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
        //running
        runningPics = new GreenfootImage[8];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("MR" + (i+1) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 8;
        runningAnimationCounter = runningAnimationDelay;

        //death
        deathPics = new GreenfootImage[6];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("MD" + (i+1) + ".png");
            deathPics[i].scale(deathPics[i].getWidth()*2, deathPics[i].getHeight()*2);
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 8;
        deathAnimationCounter = deathAnimationDelay;

        //attack one
        attackOnePics = new GreenfootImage[8];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("MA1" + (i+1) + ".png");
            attackOnePics[i].scale(attackOnePics[i].getWidth()*2, attackOnePics[i].getHeight()*2);
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 8;
        attackOneAnimationCounter = attackOneAnimationDelay;

        //attack two
        attackTwoPics = new GreenfootImage[14];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("MA2" + (i+1) + ".png");
            attackTwoPics[i].scale(attackTwoPics[i].getWidth()*2, attackTwoPics[i].getHeight()*2);
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 8;
        attackTwoAnimationCounter = attackTwoAnimationDelay;

        //idle
        idlePics = new GreenfootImage[7];
        for (int i = 0; i < idlePics.length; i++) {
            idlePics[i] = new GreenfootImage("MI" + (i+1) + ".png");
            idlePics[i].scale(idlePics[i].getWidth()*2, idlePics[i].getHeight()*2);
        }
        idleAnimationIndex = 0;
        idleAnimationDelay = 8;
        idleAnimationCounter = idleAnimationDelay;
        setImage(idlePics[0]);
    }
}
