import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author Justin Wu 
 * @version V0
 */
public class Settings extends World
{
    private GreenfootImage background;
    private int knightsSlideBar;
    private int numKnights;
    private int knightLevel;
    
    private int numHealers;
    private int healerLevel;
    
    private int numMages;
    private int magesLevel;
    
    private int difficulty;

    
    //constructor
    public Settings()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("SettingsBackground.png");
        setBackground(background);
    }
    
    //add a method making the slidebar for the settings
    
}
