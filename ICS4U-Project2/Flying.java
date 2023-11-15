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
        super.act();
        
    }
    
    protected void action(Party targetPlayer){
        
    }
    
    public Enemy pickUpSwordEnemy(){
        List<Sword> swords = getWorld().getObjects(Sword.class);
        if(!swords.isEmpty()){
            int randomIndex = Greenfoot.getRandomNumber(swords.size());
            Sword randomSword = swords.get(randomIndex);
            turnTowards(randomSword.getX(), randomSword.getY());
            while(getY() - randomSword.getY() > 15){
                move(speed);
            }
            randomSword.pickUp();
            //pickup animations
            pickedUpEnemy = true;
            return randomSword;
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
