import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fader extends Effects
{
    
    private int fadeCounter;
    private int fadeTime;
    
    private boolean fadeStyle; // false = fadeIn, true = fadeout
    private int levelToChange;
    private Background background;
    /**
     * Main constructor for Fader class
     * @param totalFadeTime duration for fadeIn or fadeOut
     * @param level The level to transition to once fading begins, set as -1 if no change is needed
     */
    public Fader(int totalFadeTime, int level, Background setBackground)
    {
        image = new GreenfootImage(1400, 1000);
        image.setColor(Color.BLACK);
        image.fill();
        image.setTransparency(0);
        setImage(image);
        fadeTime = totalFadeTime;
        fadeCounter = 0;
        fadeStyle = false;
        levelToChange = level;
        background = setBackground;
    }
    /**
     * Act - do whatever the Fader wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (!fadeStyle)
        {
            if (fadeCounter >= fadeTime)
            {
                if (levelToChange >= 0 && background != null)
                {
                    background.setWorldBackground(levelToChange);
                }
                fadeStyle = true;
                fadeCounter = 0;
            }
            else
            {
                fadeCounter++;
                fadeIn(fadeCounter, fadeTime);
                setImage(image);
            }
        }
        else
        {
            if (fadeCounter >= fadeTime)
            {
                getWorld().removeObject(this);
            }
            else
            {
                fadeCounter++;
                fadeOut(fadeCounter, fadeTime);
                setImage(image);
            }
        }
        
    }
}
