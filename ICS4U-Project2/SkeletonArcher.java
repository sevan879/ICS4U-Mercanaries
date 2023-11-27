import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * SkeletonArcher is a ranged enemy class that fires arrows at players. Low HP, high damage, high range.
 * 
 * @author Evan Ma, Kenneth Jin
 * @version V1
 */
public class SkeletonArcher extends Enemy
{
    private static final int HP = 2;
    private static final double SPEED = 2.2;
    private static final int DELAY = 90;
    private static final int DAMAGE = 12;
    private static final int ATTACK_RANGE = 580;

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
    
    private GreenfootSound a;// sound effect for arrow release and whoosh noise

    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();    
    }
    /**
     * Main constructor for SkeletonArcher
     */
    public SkeletonArcher(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        rangedEnemy = true;
        setImage(runningPics[1]);
        a = new GreenfootSound("Arrow.wav");
        a.setVolume(60);
    }
    
    protected void action(Party targetPlayer){
        int distance = Math.abs(targetPlayer.getX() - getX());
        
        
        getWorld().addObject(new Arrow(distance, DAMAGE), getX(), getY()-20);
        a.play();
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
     * Play animation for first attack.
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
     * Play animation for second attack.
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
            MainWorld.increaseScore();
            getWorld().removeObject(this);
            
        
        }
        
    }

    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;

        runningPics = new GreenfootImage[8];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("SASR" + (i+1) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;

        //death
        deathPics = new GreenfootImage[5];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("SAD" + (i+1) + ".png");
            deathPics[i].scale(deathPics[i].getWidth()*2, deathPics[i].getHeight()*2);
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 10;
        deathAnimationCounter = deathAnimationDelay;

        //attack one
        attackOnePics = new GreenfootImage[15];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("SAS1" + (i+1) + ".png");
            attackOnePics[i].scale(attackOnePics[i].getWidth()*2, attackOnePics[i].getHeight()*2);
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 5;
        attackOneAnimationCounter = attackOneAnimationDelay;

        //attack two
        attackTwoPics = new GreenfootImage[15];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("SAS2" + (i+1) + ".png");
            attackTwoPics[i].scale(attackTwoPics[i].getWidth()*2, attackTwoPics[i].getHeight()*2);
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 5;
        attackTwoAnimationCounter = attackTwoAnimationDelay;
    }
}
