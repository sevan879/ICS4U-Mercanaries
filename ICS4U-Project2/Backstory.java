import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Backstory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Backstory extends World
{
    private GreenfootImage background;
    
    //constructor
    public Backstory()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("BackstoryBackground.png");
        setBackground(background);
    }
}
