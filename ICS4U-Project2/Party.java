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
    protected boolean inCombat;
    
    //constructor
    public Party() {
        runningSpeed = 1;
        inCombat = false;
    }
    
    //act method
    public void act() {
        if (Greenfoot.isKeyDown("w")) {
            runningSpeed = 0;
            inCombat = true;
        }
        if (Greenfoot.isKeyDown("s")) {
            runningSpeed = 1;
            inCombat = false;
        }
        //code that determines whether or not the enemies have been slain, makes in combat false
        if (!inCombat) {
            runningSpeed = 1;
            running(); 
        }
    }
    
    //abstract methods "promises"
    protected abstract void running();
    
    public void setInCombat() {
        inCombat = true;
    }
    //speed getter
    public int getRunningSpeed() {
        return runningSpeed;
    }
}
