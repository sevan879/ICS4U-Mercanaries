import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Projectiles
{
    private GreenfootSound [] small; // sounds for small fireball
    private GreenfootSound [] big; //sounds for big fireball
    
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Fireball(){
        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        small = new GreenfootSound[3];
        for(int i = 0; i < small.length; i++){
            small[i] = new GreenfootSound("SmallFireBall.mp3");
        }
        big = new GreenfootSound[3];
        for(int i = 0; i < big.length; i++){
            big[i] = new GreenfootSound("BigFireBall.mp3");
        }
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
