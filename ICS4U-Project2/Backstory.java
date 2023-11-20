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
    private Knight k = new Knight();
    //private Wolf w = new Wolf();
    
    //constructor
    public Backstory()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("BackstoryBackground.png");
        setBackground(background);
        addObject(k, 100,200);
        //addObject(w, 100, 1000);
    }
}
