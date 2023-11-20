import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class speakWolf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class speakWolf extends SuperSmoothMover
{
    private int animationTracker; // odd = running, even = not running
    private int attackTracker; // 0, 1, 2 to decide which attack animation to use
    //Animation   
    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;
    
    /**
     * Act - do whatever the Wolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        running();
    }
    
    public speakWolf(){
       animationConstructor();
    }

    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;

        //running
        runningPics = new GreenfootImage[9];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("W0" + (i) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;
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

    public boolean animationIsRunning() {
        return animationTracker %2 == 1; //this means that the animation is running, 
    }
}
