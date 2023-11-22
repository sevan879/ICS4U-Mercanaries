import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SkeletonKnight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkeletonWarrior extends Enemy
{
    private static final int HP = 10;
    private static final double SPEED = 4;
    private static final int DELAY = 10;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 25;
    private static boolean movable = true;

    private int animationTracker; // odd = running, even = not running
    private int attackTracker; // 0, 1, 2 to decide which attack animation to use

    //Animation   
    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    //constructor
    public SkeletonWarrior() {
        super(10, 10, 10, 10, false, 10);
        //running
        runningPics = new GreenfootImage[7];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("SWR" + (i+1) + ".png");
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;
    }

    //act method
    public void act()
    {
        // Add your action code here.
    }

    //ANIMATION
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

    protected void action(Party targetPlayer){
        // does nothing for now
    }
    
    public void attackAnimation() {
        
    }
    
    protected void pickUp() {
        movable = false;
    }
}
