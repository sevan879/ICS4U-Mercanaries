import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * BigSkeletonSpear is an enemy class that deals melee damage and has a longer attack range. Has a special AOE attack.
 * 
 * @author Arthur Tian
 * @version V1
 */
public class BigSkeletonSpear extends Enemy
{
    private static final int HP = 40;
    private static final double SPEED = 2;
    private static final int DELAY = 60;
    private static final int DAMAGE = 6;
    private static final int ATTACK_RANGE = 115;

    private int animationTracker; // odd = running, even = not running
    private int attackTracker; // 0, 1, 2 to decide which attack animation to use
    //Animation   
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
    
    private int specialRange;

    /**
     * Act - do whatever the Spear wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        specialRange = 300;
    }
    /**
     * Main constructor for SkeltonSpear
     */
    public BigSkeletonSpear(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        setImage(runningPics[1]);
    }

    protected void action(Party targetPlayer){
        mainAttack();
    }

    public void attackAnimation() {
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
                attackTracker = 0;
            }
        }
    }
    
    private void mainAttack(){
        if(playersUpClose() != null){
            for(Party p: playersUpClose()){
                if (attackTracker == 0) { //attack one
                    attackOne();
                    //stuff about dealing damage, whatever
                    if (!animationIsRunning()) {
                        attackTracker = 1;
                    }
                }
                else if (attackTracker == 1) { //attack two
                    attackTwo();
                    specialAttack();
                    //stuff about dealing damage, whatever
                    if (!animationIsRunning()) {
                        attackTracker = 0;
                    }
                }
                p.takeDamage(DAMAGE);
                MainWorld.increaseDamageTaken(DAMAGE);
            }
        }

        if(playersFurtherAway() != null){
            for(Party p: playersFurtherAway()){
                p.takeDamage(DAMAGE - 1);
                MainWorld.increaseDamageTaken(DAMAGE);
            }
        }
    }
    
    private void specialAttack()
    {
        for (Party p : (ArrayList<Party>)(getObjectsInRange(specialRange, Party.class)))
        {
            p.takeDamage(DAMAGE * 2);
            MainWorld.increaseDamageTaken(DAMAGE);
        }
        getWorld().addObject(new BigSkeletonSlash(0.9), getX()-20, getY());
    }

    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;

        //running
        runningPics = new GreenfootImage[6];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("SSR" + (i+1) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*3, runningPics[i].getHeight()*3);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;

        //death
        deathPics = new GreenfootImage[5];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("SSD" + (i+1) + ".png");
            deathPics[i].scale(deathPics[i].getWidth()*3, deathPics[i].getHeight()*3);
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 10;
        deathAnimationCounter = deathAnimationDelay;

        //attack one
        attackOnePics = new GreenfootImage[4];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("SSA1" + (i+1) + ".png");
            attackOnePics[i].scale(attackOnePics[i].getWidth()*3, attackOnePics[i].getHeight()*3);
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 10;
        attackOneAnimationCounter = attackOneAnimationDelay;

        //attack two
        attackTwoPics = new GreenfootImage[4];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("SSA2" + (i+1) + ".png");
            attackTwoPics[i].scale(attackTwoPics[i].getWidth()*3, attackTwoPics[i].getHeight()*3);
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 10;
        attackTwoAnimationCounter = attackTwoAnimationDelay;
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
    /**
     * Play first attack animation.
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
    /**
     * Play second attack animation.
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
        isDying = true;
        boolean remove = false;
        if (deathAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            deathAnimationCounter = deathAnimationDelay; // reset counter to max 
            deathAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (deathAnimationIndex == deathPics.length){
                remove = true;
                deathAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setLocation(getX(), getY()+5);
            setImage (deathPics[deathAnimationIndex]);
            if (deathAnimationIndex > 2) {
                setLocation(getX(), getY()+25);
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            deathAnimationCounter--;
        }
        if (remove) {
            getWorld().removeObject(this);
        }
    }
    
    

    public boolean animationIsRunning() {
        return animationTracker %2 == 1; //this means that the animation is running, 
    }
}
