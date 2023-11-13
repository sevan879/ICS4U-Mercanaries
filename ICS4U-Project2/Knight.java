import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CQC/Tank class party member, blocks attacks with shield effect, can attack with sword 
 * 
 * @Arthur
 * @version (a version number or a date)
 */
public class Knight extends Party
{
    private int animationTracker; // odd = running, even = not running
    private int attackTracker; // 0, 1, 2 to decide which attack animation to use

    //Animation and Images
    private GreenfootImage[] idlePics;
    private int idleAnimationIndex;
    private int idleAnimationDelay;
    private int idleAnimationCounter;

    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    private GreenfootImage[] runningAttackPics;
    private int runningAttackAnimationIndex;
    private int runningAttackAnimationDelay;
    private int runningAttackAnimationCounter;

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

    public Knight() {
        super(SET_HP, SET_SPEED, ACTION_DELAY, false, XP_INCREASE_PER_LEVEL, ATTACK_RANGE, MAX_MANA, MAX_LEVEL);
        setImage(new GreenfootImage("KI1.png"));

        animationTracker = 0;
        attackTracker = 0;

        //Animation

        //idle
        idlePics = new GreenfootImage[4];
        for (int i = 0; i < idlePics.length; i++) {
            idlePics[i] = new GreenfootImage("KI" + (i+1) + ".png");
        }
        idleAnimationIndex = 0;
        idleAnimationDelay = 10;
        idleAnimationCounter = idleAnimationDelay;

        //running
        runningPics = new GreenfootImage[7];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("KR" + (i+1) + ".png");
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;

        //running attack
        runningAttackPics = new GreenfootImage[6];
        for (int i = 0; i < runningAttackPics.length; i++) {
            runningAttackPics[i] = new GreenfootImage("KRA" + (i+1) + ".png");
        }
        runningAttackAnimationIndex = 0;
        runningAttackAnimationDelay = 10;
        runningAttackAnimationCounter = runningAttackAnimationDelay;

        attackOnePics = new GreenfootImage[5];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("KA1" + (i+1) + ".png");
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 10;
        attackOneAnimationCounter = attackOneAnimationDelay;

        attackTwoPics = new GreenfootImage[4];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("KA2" + (i+1) + ".png");
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 10;
        attackTwoAnimationCounter = attackTwoAnimationDelay;

        attackThreePics = new GreenfootImage[4];
        for (int i = 0; i < attackThreePics.length; i++) {
            attackThreePics[i] = new GreenfootImage("KA3" + (i+1) + ".png");
        }
        attackThreeAnimationIndex = 0;
        attackThreeAnimationDelay = 10;
        attackThreeAnimationCounter = attackThreeAnimationDelay;
    }

    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if (inCombat) {
            //put a boolean here about whether to attack or defend or whatever the fuck
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
    }

    //ANIMATION

    //ADD SOMETHING ABOUT HOW DAMAGE SHOULD BE DEALT WITH OFFSET AT A CERTAIN FRAME OF THE ATTACK ANIMATION WHEN SLASH IS ON SCREEN
    public boolean animationIsRunning() {
        return animationTracker %2 == 1; //this means that the animation is running, 
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

    public void runningAttack() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (runningAttackAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            runningAttackAnimationCounter = runningAttackAnimationDelay; // reset counter to max 
            runningAttackAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (runningAttackAnimationIndex == runningAttackPics.length){
                runningAttackAnimationIndex = 0;
                animationTracker++;
            }
            // Apply new image to this Actor
            setImage (runningAttackPics[attackOneAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            runningAttackAnimationCounter--;
        }
    }

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

    public void attackThree() {
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
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackThreeAnimationCounter--;
        }
    }

    //BASE STATS AT LEVEL ONE
    private static final int SET_HP = 5;
    private static final double SET_SPEED = 3;
    private static final int ACTION_DELAY = 40; // amount of acts
    private static final int XP_INCREASE_PER_LEVEL = 1;
    private static final int ATTACK_RANGE = 25;
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 3;
    
    //stats that are increased on each level up
    private static final int DAMAGE_INCREASE = 1; 
    private static final int HEALTH_INCREASE = 2; 
    private static final int RANGE_INCREASE = 0;
    private static final int MANA_INCREASE = 0;
    
    private int spellLevel = 0;
    private int damage = 5;
    
    protected void mainAction(Enemy target)
    {
        int dealtDamage = damage + Greenfoot.getRandomNumber(2);
        target.takeDamage(dealtDamage);
        //Make Enemy take damage
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
}
