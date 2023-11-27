import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen here.
 * 
 * @author Evan Ma, Arthur Tian 
 * @version V1
 */
public class LoadingScreen extends Effects
{

    private int fadeCounter;
    private int fadeTime;
    private int levelToChange;
    private boolean fadeStyle; // false = fadeIn, true = fadeout
    private GreenfootImage[] images;
    private GreenfootImage[] loading;
    private int loadingAnimationCounter;
    private int loadingAnimationDelay;
    private int loadingAnimationIndex;
    private int loadingPresentDelay;
    private int loadingPresentCounter;
    private boolean changeWorldOrLevel;//true means change world, false means change level
    private Background b;
    private MainWorld w;
    /**
     * Main constructor for LoadingScreen
     * @param fadeStyle Determines fade in or fade out. True = fade out, False = fade in.
     * @param totalFadeTime Total time for fading.
     * @param changeWorldOrLevel Determines if world or background should be changed
     * @param b Background to change to.
     * @param w The current MainWorld
     * @param levelToChange Integer represent the integer to change to.
     */
    public LoadingScreen(boolean fadeStyle, int totalFadeTime, boolean changeWorldOrLevel, Background b, MainWorld w, int levelToChange) {
        images = new GreenfootImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("LS" + (i+1) + ".png");
        }
        loading = new GreenfootImage[4];
        for (int i = 0; i < loading.length; i++) {
            loading[i] = new GreenfootImage("loading" + i + ".png");
        }
        image = loading[0];
        fadeTime = totalFadeTime;
        fadeCounter = 0;

        loadingAnimationIndex = 0;
        loadingAnimationDelay = 5;
        loadingAnimationCounter = loadingAnimationDelay;

        loadingPresentDelay = 60;
        loadingPresentCounter = 0;
        this.b = b;
        this.changeWorldOrLevel = changeWorldOrLevel;
        this.w = w;
        this.levelToChange = levelToChange;
        this.fadeStyle = fadeStyle;
    }
    
    public void act()
    {
        setImage(image);
        if (!fadeStyle) //fade in to world
        {
            w.started();
            if (!(fadeCounter > fadeTime-8)) //continue fading in
            {
                fadeCounter++; 
                fadeIn(fadeCounter, fadeTime);
                image = actualLoadAnimation();
            }
            else { //finished fading in
                fadeCounter = fadeTime;
                fadeStyle = true;
                image.setTransparency(255);
            }
        }
        else 
        {
            b.setWorldBackground(levelToChange);
            if (fadeCounter == fadeTime) { //keep opacity at max, running animation for a bit, then fade out
                // have a delay and keep full opacity
                image.setTransparency(255);
                if (loadingPresentCounter > loadingPresentDelay) { //counting is finished 
                    fadeCounter = 0;
                    loadingPresentCounter = 0;
                }
                else { //count... keep changing frames at intervals
                    loadingPresentCounter++;
                    image = actualLoadAnimation();
                }
            }
            else {// fade out of world
                getWorld().setBackground(b.getWorldBackground(levelToChange));
                if (fadeCounter > fadeTime-2)
                {
                    if (changeWorldOrLevel) { //true means change world, false means change level
                        Greenfoot.setWorld(w);
                    }
                    else {
                        b.setWorldBackground(levelToChange);
                    }
                    getWorld().removeObject(this);
                }
                else
                {
                    fadeCounter++;
                    fadeOut(fadeCounter, fadeTime);
                    image = actualLoadAnimation();
                }
            } 
        }
    }

    private GreenfootImage actualLoadAnimation() {
        if (loadingAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            loadingAnimationCounter = loadingAnimationDelay; // reset counter to max 
            loadingAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (loadingAnimationIndex == loading.length){
                loadingAnimationIndex = 0;
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            loadingAnimationCounter--;
        }
        return (loading[loadingAnimationIndex]);        
    }
}
