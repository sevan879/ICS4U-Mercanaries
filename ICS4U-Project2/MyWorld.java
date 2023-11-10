import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{    
    private Background b;
    private Knight k;
    private SwordEffect s;
    
    //constructor
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(543, 360, 1); 
        b = new Background();
        k = new Knight();
        s = new SwordEffect();
        
        addObject(b, 543, 180); //add background first, so its behind everything
        addObject(k, 100, 305);
        addObject(s, 100, 305);
    }
    
    //act method
    public void act() {
    }
}
