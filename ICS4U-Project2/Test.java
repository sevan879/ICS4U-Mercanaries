import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test extends World
{

    /**
     * Constructor for objects of class Test.
     * 
     */
    public Test()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        addObject(new SkeletonWarrior(),530, 200);
        addObject(new Knight(), 110, 200);
    }
}
