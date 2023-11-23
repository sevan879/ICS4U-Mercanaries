import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Spear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkeletonSpear extends Enemy
{
    private static final int HP = 5;
    private static final double SPEED = 2;
    private static final int DELAY = 30;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 15;

    private int animationTracker; // odd = running, even = not running
    private int attackTracker; // 0, 1, 2 to decide which attack animation to use
    //Animation   
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

    private GreenfootImage[] runningAttackPics;
    private int runningAttackAnimationIndex;
    private int runningAttackAnimationDelay;
    private int runningAttackAnimationCounter;

    /**
     * Act - do whatever the Spear wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();

    }

    public SkeletonSpear(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        animationConstructor();
    }

    protected void action(Party targetPlayer){
        mainAttack();
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
                    //stuff about dealing damage, whatever
                    if (!animationIsRunning()) {
                        attackTracker = 0;
                    }
                }
                p.takeDamage(DAMAGE);
            }
        }

        if(playersFurtherAway() != null){
            for(Party p: playersFurtherAway()){
                p.takeDamage(DAMAGE - 1);
            }
            runningAttack();
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 1;
            }
        }
    }

    public List<Party> playersUpClose(){
        List<Party> fullDamage = getObjectsInRange(attackRange, Party.class);
        return fullDamage;
    }

    public List<Party> playersFurtherAway(){
        List<Party> halfDamage = getObjectsInRange(attackRange + 25, Party.class);
        return halfDamage;
    }

    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;

        //running
        runningPics = new GreenfootImage[6];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("SSR" + (i+1) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;

        //attack one
        attackOnePics = new GreenfootImage[4];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("SSA1" + (i+1) + ".png");
            attackOnePics[i].scale(attackOnePics[i].getWidth()*2, attackOnePics[i].getHeight()*2);
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 10;
        attackOneAnimationCounter = attackOneAnimationDelay;

        //attack two
        attackTwoPics = new GreenfootImage[4];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("SSA2" + (i+1) + ".png");
            attackTwoPics[i].scale(attackTwoPics[i].getWidth()*2, attackTwoPics[i].getHeight()*2);
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 10;
        attackTwoAnimationCounter = attackTwoAnimationDelay;

        //running attack
        runningAttackPics = new GreenfootImage[5];
        for (int i = 0; i < runningAttackPics.length; i++) {
            runningAttackPics[i] = new GreenfootImage("SSRA" + (i+1) + ".png");
            runningAttackPics[i].scale(runningAttackPics[i].getWidth()*2, runningAttackPics[i].getHeight()*2);
        }
        runningAttackAnimationIndex = 0;
        runningAttackAnimationDelay = 10;
        runningAttackAnimationCounter = runningAttackAnimationDelay;
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

    public boolean animationIsRunning() {
        return animationTracker %2 == 1; //this means that the animation is running, 
    }
}
