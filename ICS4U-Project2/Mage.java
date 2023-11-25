import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mage extends Party
{
    //BASE STATS AT LEVEL ONE
    private static final int SET_HP = 4;
    private static final double SET_SPEED = 3;
    private static final int ACTION_DELAY = 150; // amount of acts
    private static final int XP_INCREASE_PER_LEVEL = 1;
    private static final int ATTACK_RANGE = 400;
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

    private int damage = 5;

    private int smallSpellMana;
    private int bigSpellMana;

    public Mage()
    {
        super(SET_HP, SET_SPEED, ACTION_DELAY, false, XP_INCREASE_PER_LEVEL, ATTACK_RANGE, MAX_MANA, MAX_LEVEL, MANA_CLASS);

    }

    public void act()
    {
        super.act();
    }

    protected void mainAction(Enemy target)
    {
        int dealtDamage = damage + Greenfoot.getRandomNumber(2);
        target.takeDamage(dealtDamage);
        //Make Enemy take damage
    }

    protected void mainAnimation() {
        if (attackTracker == 0) { //attack one
            attackOne();
            getWorld().addObject(new Fireball(1, 1), 1, 1);
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 1;
            }
        }
        else if (attackTracker == 1) { //attack two
            attackTwo();
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 0;
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

    private void smallSpell()
    {
        spendMana(smallSpellMana);
        // CREATE PROJECTILE OBJECT
    }

    private void bigSpell()
    {
        spendMana(bigSpellMana);
        // CREATE PROJECTILE OBJECT
    }

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
            }
            if (attackOneAnimationIndex == 2) {
                setLocation(getX()-15, getY());
            }
            // Apply new image to this Actor
            setImage (attackOnePics[attackOneAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }

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
            }
            if (attackTwoAnimationIndex == 3) {
                setLocation(getX()+15, getY());
            }
            // Apply new image to this Actor
            setImage (attackTwoPics[attackTwoAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackTwoAnimationCounter--;
        }
    }

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
