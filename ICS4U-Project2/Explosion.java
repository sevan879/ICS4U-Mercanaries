import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Effects
{
    //Animation Variables
    private GreenfootImage[] images;
    private int animationIndex;
    private int animationDelay;
    private int animationCounter;
    
    private int explosionTime = 100; //Life Time
    private int timeLeft;
    private boolean firstBomb = true; 
    
    private double imageScale;
    
    private GreenfootSound effectSound;
    /**
     * Constructor for Explosion, takes a double to determine how large the explosion should be
     */
    public Explosion(double imageScale)
    {
        // Initialize basic variables
        animationDelay = 2;
        animationIndex = 0;
        timeLeft = 0;
        this.imageScale = imageScale;
        // play sound effect
        effectSound = new GreenfootSound("ExplosionSound.wav");
        effectSound.setVolume(40);
        effectSound.play();
        images = new GreenfootImage[28];
        for (int i = 0; i < 28; i++)
        {
            GreenfootImage frame = new GreenfootImage("1_" + (1 + i) + ".png");
            frame.scale((int)(frame.getWidth()*imageScale), (int)(frame.getHeight()*imageScale));;
            images[i] = frame;
        }
    }
    
    
    public void act()
    {   
        // animate object
        animationIndex ++;
        if (animationIndex == images.length)
        {
            getWorld().removeObject(this);
        }
        else
        {
            setImage(images[animationIndex]);
    
        }
    }
}
