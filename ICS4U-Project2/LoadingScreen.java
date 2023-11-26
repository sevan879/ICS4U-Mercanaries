import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingScreen extends Effects
{

    private int fadeCounter;
    private int fadeTime;
    private int levelToChange;

    private boolean fadeStyle; // false = fadeIn, true = fadeout
    private GreenfootImage[] images;
    private GreenfootImage[] loading;
    private int imageChooser;
    private int loadingAnimationCounter;
    private int loadingAnimationDelay;
    private int loadingAnimationIndex;
    private int loadingPresentDelay;
    private int loadingPresentCounter;
    private boolean changeWorldOrLevel;
    private Background b;
    private World w;

    public LoadingScreen(boolean fadeStyle, int totalFadeTime, int imageChooser, boolean changeWorldOrLevel, Background b, World w, int levelToChange) {
        setImage(image);
        images = new GreenfootImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("LS" + (i+1) + ".png");
        }
        loading = new GreenfootImage[4];
        for (int i = 0; i < loading.length; i++) {
            loading[i] = new GreenfootImage("loading" + i + ".png");
        }
        this.imageChooser = imageChooser;
        fadeTime = totalFadeTime;
        fadeCounter = 0;
        image = images[imageChooser];

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
            if (!(fadeCounter >= fadeTime)) //end fade in, start fading out
            //continue fading in 
            {
                fadeCounter++; 
                fadeIn(fadeCounter, fadeTime);
                image = actualLoadAnimation();
            }
            else {
                fadeCounter = fadeTime;
                fadeStyle = true;
            }
        }
        else 
        {
            if (fadeCounter == fadeTime) { //keep opacity at max, running animation for a bit, then fade out
                // have a delay and keep 100 opacity
                if (loadingPresentCounter > loadingPresentDelay) { //counting is finished 
                    fadeCounter = 0;
                    loadingPresentCounter = 0;
                }
                else { //count... keep changing frames at intervals
                    loadingPresentCounter++;
                    image = actualLoadAnimation();
                    image.setTransparency(255);
                }
            }
            else {// fade out of world
                if (fadeCounter >= fadeTime)
                {
                    if (changeWorldOrLevel) { //true means change world, false means change level
                        w.addObject(new LoadingScreen(true, 60, 1, true, b, w, levelToChange), 534, 360);
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

    public GreenfootImage actualLoadAnimation() {
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
