import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Flying here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flying extends Enemy
{
    private static final int HP = 20;
    private static final double SPEED = 5;
    private static final int DELAY = 10;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 25;
    
    private boolean pickedUpEnemy = false;
    public Flying(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
    }
    
    /**
     * Act - do whatever the Flying wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        move();
    }
    
    protected void action(Party targetPlayer){
        
    }
    
    public Enemy pickUpSwordEnemy(){
        List<SkeletonWarrior> warriors = getWorld().getObjects(SkeletonWarrior.class);
        if(!warriors.isEmpty()){
            int randomIndex = Greenfoot.getRandomNumber(warriors.size());
            SkeletonWarrior randomWarrior = warriors.get(randomIndex);
            turnTowards(randomWarrior.getX(), randomWarrior.getY());
            while(getY() - randomWarrior.getY() > 15){
                move(speed);
            }
            randomWarrior.pickUp();
            //pickup animations
            pickedUpEnemy = true;
            return randomWarrior;
        }
        return null;
        
    }
    
    public void dropEnemy(){
        
        
    }
    
    
    
    public void move(){
        setLocation(getX() - speed, getY() + Greenfoot.getRandomNumber(2) - 1);
        //flying animations
    }
    
    
    
}
