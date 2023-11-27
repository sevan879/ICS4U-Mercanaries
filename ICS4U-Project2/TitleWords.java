import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title Label for game
 * 
 * @author Arthur Tian
 * @version V1
 */
public class TitleWords extends Actor
{
    private GreenfootImage[] images;
    private int animationIndex;
    private int animationDelay;
    private int animationCounter;

    /**
     * Main constructor for TitleWords. Creates the title images.
     */
    public TitleWords() {
        images = new GreenfootImage[13];
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("t" + (i+1) + ".png");
        }
        animationIndex = 0;
        animationDelay = 15;
        animationCounter = animationDelay;
        setImage(images[0]);
    }

    //act method
    public void act()
    {
        if (animationCounter == 0){ // counter reaches 0 means ready for next frame
            animationCounter = animationDelay; // reset counter to max 
            animationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (animationIndex == images.length){
                animationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (images[animationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            animationCounter--;
        }
    }
}