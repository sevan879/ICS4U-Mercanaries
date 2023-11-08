import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Party
{
    //Animation
    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    private GreenfootImage[] attackOnePics;
    private int attackOneAnimationIndex;
    private int attackOneAnimationDelay;
    private int attackOneAnimationCounter;

    public Knight() {
        super();

        //Animation
        runningPics = new GreenfootImage[7];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("KR" + (i+1) + ".png");
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;

        attackOnePics = new GreenfootImage[5];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("KA1" + (i+1) + ".png");
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 10;
        attackOneAnimationCounter = attackOneAnimationDelay;
    }

    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("w")) {
            attackOne();
            runningSpeed = 0;
        }
        else {
            super.act();
        }
    }

    //ANIMATION

    public void running() {
        if (runningAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            runningAnimationCounter = runningAnimationDelay; // reset counter to max 
            runningAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to zero
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
        if (attackOneAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackOneAnimationCounter = attackOneAnimationDelay; // reset counter to max 
            attackOneAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to zero
            if (attackOneAnimationIndex == attackOnePics.length){
                attackOneAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (attackOnePics[attackOneAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }
}
