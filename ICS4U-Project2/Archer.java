import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archer extends Enemy
{
    private static final int RELOAD_TIME = 120; 
    private int reloadTimer = 0;
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        shoot();
        move();
        
    }
    
    public Archer(){
        super(3 + (int)Math.random() * 2, 1 + (int)Math.random() * 2, 25, 3 + (int)Math.random() * 2, true);
        
        attackRange = 200;
        
        
    }
    
    public void shoot(){
        if(reloadTimer > 0){
            reloadTimer--;
        }else{
            attack();
            reloadTimer = RELOAD_TIME;
            
        }
    }
    
    
    
}
