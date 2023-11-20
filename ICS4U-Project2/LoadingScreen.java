import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingScreen extends Actor
{
    /**
     * Act - do whatever the LoadingScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private GreenfootImage[] images;
    private int AnimationIndex;
    private int AnimationDelay;
    private int AnimationCounter;

    public LoadingScreen() {
        images = new GreenfootImage[3];
        AnimationIndex = 0 ;
        AnimationDelay = 45;
        AnimationCounter = AnimationIndex;
        for (int i = 0; i < 3; i++) {
            images[i] = new GreenfootImage("loading"+i+".png");
        }
    }

    public void act()
    {
        Animation();
    }
    
    public void bringDown() {
        
    }
    
    public void bringUp() {
        
    }

    public void Animation() {
        if (AnimationCounter == 0){ // counter reaches 0 means ready for next frame
            AnimationCounter = AnimationDelay; // reset counter to max 
            AnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (AnimationIndex == images.length){
                AnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (images[AnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            AnimationCounter--;
        }
    }
}
