import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CQC/Tank class party member, blocks attacks with shield effect, can attack with sword 
 * 
 * @author Evan Ma, Arthur Tian
 * @version V1
 */
public class Knight extends Party
{

    //Animation and Images
    private GreenfootImage[] idlePics;
    private int idleAnimationIndex;
    private int idleAnimationDelay;
    private int idleAnimationCounter;

    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    private GreenfootImage[] attackOnePics;
    private int attackOneAnimationIndex;
    private int attackOneAnimationDelay;
    private int attackOneAnimationCounter;

    private GreenfootImage[] attackTwoPics;
    private int attackTwoAnimationIndex;
    private int attackTwoAnimationDelay;
    private int attackTwoAnimationCounter;

    private GreenfootImage[] attackThreePics;
    private int attackThreeAnimationIndex;
    private int attackThreeAnimationDelay;
    private int attackThreeAnimationCounter;

    private GreenfootSound[] knightTaunt; //taunting sound effect 
    private GreenfootSound[] knightSlash; //sword slashing sound effect 

    //BASE STATS AT LEVEL ONE
    private static final int SET_HP = 100;
    private static final double SET_SPEED = 3;
    private static final int ACTION_DELAY = 40; // amount of acts
    private static final int XP_INCREASE_PER_LEVEL = 1;
    private static final int ATTACK_RANGE = 125;
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 3;
    private static final boolean MANA_CLASS = false;

    //stats that are increased on each level up
    private static final int DAMAGE_INCREASE = 1; 
    private static final int HEALTH_INCREASE = 2; 
    private static final int RANGE_INCREASE = 0;
    private static final int MANA_INCREASE = 0;

    private int spellLevel = 0;
    private int damage = 2;
    
    
    /**
    * Main Constructor for Knight Class
    */
    public Knight() {
        super(SET_HP, SET_SPEED, ACTION_DELAY, false, XP_INCREASE_PER_LEVEL, ATTACK_RANGE, MAX_MANA, MAX_LEVEL, MANA_CLASS);

        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        knightTaunt = new GreenfootSound[3];
        for(int i = 0; i < knightTaunt.length; i++){
            knightTaunt[i] = new GreenfootSound("KnightTaunt.mp3");
        }
        knightSlash = new GreenfootSound[3];
        for(int i = 0; i < knightSlash.length; i++){
            knightSlash[i] = new GreenfootSound("KnightSlash.mp3");
        }

        //visuals/animation
        animationConstructor();
        setImage(idlePics[1]);
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
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 1;
            }
        }
        else if (attackTracker == 1) { //attack two
            attackTwo();
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 2;
            }
        }
        else if (attackTracker == 2) { //attack three
            attackThree();
            //stuff about dealing damage, whatever, this one should deal the most since its the last attack
            if (!animationIsRunning()) {
                attackTracker = 0;
            }
        }
    }

    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        //if damage dealt to knight but no enemies in range, block attacks. 
    }

    /**
    * Plays the idle animation for Knight class
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
    /**
    * Plays first attack animation.
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
            // Apply new image to this Actor
            setImage (attackTwoPics[attackTwoAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackTwoAnimationCounter--;
        }
    }
    /**
    * Plays third attack animation.
    */
    private void attackThree() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackThreeAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackThreeAnimationCounter = attackThreeAnimationDelay; // reset counter to max 
            attackThreeAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackThreeAnimationIndex == attackThreePics.length){
                attackThreeAnimationIndex = 0;
                animationTracker++;
            }
            // Apply new image to this Actor
            setImage (attackThreePics[attackThreeAnimationIndex]);
            if (attackThreeAnimationIndex == 2) {
                setLocation(getX()+60, getY());
                attackThreeAnimationCounter = 10;
            }
            if (attackThreeAnimationIndex == 3) {
                setLocation(getX()-60, getY());
            }

        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackThreeAnimationCounter--;
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

    public void animationConstructor() {

        animationTracker = 0;
        attackTracker = 0;

        //Animation

        //idle
        idlePics = new GreenfootImage[4];
        for (int i = 0; i < idlePics.length; i++) {
            idlePics[i] = new GreenfootImage("KI" + (i+1) + ".png");
            idlePics[i].scale(idlePics[i].getWidth()*2, idlePics[i].getHeight()*2);
        }
        idleAnimationIndex = 0;
        idleAnimationDelay = 10;
        idleAnimationCounter = idleAnimationDelay;

        //death
        deathPics = new GreenfootImage[6];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("KD" + (i+1) + ".png");
            deathPics[i].scale(deathPics[i].getWidth()*2, deathPics[i].getHeight()*2);
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 10;
        deathAnimationCounter = deathAnimationDelay;

        //running
        runningPics = new GreenfootImage[7];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("KR" + (i+1) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 8;
        runningAnimationCounter = runningAnimationDelay;

        attackOnePics = new GreenfootImage[5];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("KA1" + (i+1) + ".png");
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

        attackThreePics = new GreenfootImage[4];
        for (int i = 0; i < attackThreePics.length; i++) {
            attackThreePics[i] = new GreenfootImage("KA3" + (i+1) + ".png");
            attackThreePics[i].scale(attackThreePics[i].getWidth()*2, attackThreePics[i].getHeight()*2);
        }
        attackThreeAnimationIndex = 0;
        attackThreeAnimationDelay = 8;
        attackThreeAnimationCounter = attackThreeAnimationDelay;
    }
}
