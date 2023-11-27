import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen here.
 * 
 * @author Evan Ma, Arthur Tian 
 * @version (a version number or a date)
 */
public class LoadingScreen extends Effects
{

    private int fadeCounter;
    private int fadeTime;

    private boolean fadeStyle; // false = fadeIn, true = fadeout
    private GreenfootImage[] images;
    private GreenfootImage[] loading;
    private int imageChooser;
    private boolean isActuallyLoad;
    private int loadingAnimationCounter;
    private int loadingAnimationDelay;
    private int loadingAnimationIndex;

    private GreenfootImage image1;
    private boolean loadingFinished;
    /**
     * Main constructor for LoadingScreen
     * @param totalFadeTime the duration of the fade
     * @param imageChooser Specific Image array to select from
     * @param l 
     */
    public LoadingScreen(int totalFadeTime, int imageChooser, boolean l) {
        image1 = new GreenfootImage(1400, 1000);
        image1.setColor(Color.BLACK);
        image1.fill();
        image1.setTransparency(0);
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
        isActuallyLoad = l;
        image = images[imageChooser];

        loadingAnimationIndex = 0;
        loadingAnimationDelay = 5;
        loadingAnimationCounter = loadingAnimationDelay;

        loadingFinished = false;
    }

    public void act()
    {
        if (!fadeStyle)
        {
            if (fadeCounter >= fadeTime)
            {
                setImage(images[imageChooser]);
                fadeStyle = true;
                fadeCounter = 0;
            }
            else
            {
                fadeCounter++;
                fadeIn(fadeCounter, fadeTime);
                if (isActuallyLoad) {
                    actualLoadAnimation();
                }
                else {
                    setImage(image);
                }
            }
        }
        else
        {
            if (fadeCounter >= fadeTime)
            {
                getWorld().removeObject(this);
                loadingFinished = true;
            }
            else
            {
                fadeCounter++;
                fadeOut(fadeCounter, fadeTime);
                if (isActuallyLoad) {
                    actualLoadAnimation();
                }
                else {
                    setImage(image);
                }
            }
        }
    }
    /**
     * Returns if the loading has been completed
     * @return boolean
     */
    public boolean getLoadingFinished() {
        return loadingFinished;
    }
    private void actualLoadAnimation() {
        if (loadingAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            loadingAnimationCounter = loadingAnimationDelay; // reset counter to max 
            loadingAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (loadingAnimationIndex == loading.length){
                loadingAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (loading[loadingAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            loadingAnimationCounter--;
        }
    }
}
