import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Brief slash effect, purely visual.
 * 
 * @author Evan Ma
 * @version V1
 */
public class BigSkeletonSlash extends Effects
{
    //Animation Variables
    private GreenfootImage[] images;
    private int animationIndex;
    private int animationDelay;
    private int animationCounter;
    
    private double imageScale;
    
    private GreenfootSound effectSound;
    /**
     * Constructor for BigSkeletonSlash, takes a double to determine how large the explosion should be
     * @param imageScale Size of explosion
     */
    public BigSkeletonSlash(double imageScale)
    {
        // Initialize basic variables
        animationDelay = 5;
        animationIndex = 0;
        this.imageScale = imageScale;
        
        images = new GreenfootImage[6];
        for (int i = 0; i < 6; i++)
        {
            GreenfootImage frame = new GreenfootImage("Rslash" + (1 + i) + ".png");
            frame.scale((int)(frame.getWidth()*imageScale), (int)(frame.getHeight()*imageScale));;
            images[i] = frame;
        }
    }
    
    
    public void act()
    {   
        // animate object
        if (animationCounter == 0){ // counter reaches 0 means ready for next frame
            animationCounter = animationDelay; // reset counter to max 
            animationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (animationIndex == images.length){
                animationIndex = 0;
                getWorld().removeObject(this);
            }
            // Apply new image to this Actor
            setImage (images[animationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            animationCounter--;
        }
    }
}
