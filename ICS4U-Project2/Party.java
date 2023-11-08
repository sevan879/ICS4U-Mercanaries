import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Party here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Party extends SuperSmoothMover
{
    protected int runningSpeed;
    
    //constructor
    public Party() {
        runningSpeed = 1;
    }
    
    //act method
    public void act() {
        running();
    }
    
    //abstract methods "promises"
    protected abstract void running();
    
    //speed getter
    public int getRunningSpeed() {
        return runningSpeed;
    }
}
