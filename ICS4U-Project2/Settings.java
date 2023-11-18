import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Settings extends World
{
    private GreenfootImage background;
    
    //constructor
    public Settings()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("SettingsBackground.png");
        setBackground(background);
    }
}
