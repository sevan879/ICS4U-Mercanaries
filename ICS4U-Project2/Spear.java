import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Spear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spear extends Enemy
{
    private static final int HP = 5;
    private static final double SPEED = 3;
    private static final int DELAY = 20;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 25;
    
    /**
     * Act - do whatever the Spear wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        
    }
    
    public Spear(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        

    }
    
    protected void action(Player targetPlayer){
        attack();
    }
    
    public void attack(){
        if(playersUpClose() != null){
            for(Player p: playersUpClose()){
                p.takeDamage(damage);
            }
        }
        
        if(playersFurtherAway() != null){
            for(Player p: playersFurtherAway()){
                p.takeDamage(damage - 1);
            }
        }
    }
    
    public List<Player> playersUpClose(){
        List<Player> fullDamage = getObjectsInRange(attackRange, Player.class);
        return fullDamage;
    }
    
    public List<Player> playersFurtherAway(){
        List<Player> halfDamage = getObjectsInRange(attackRange + 25, Player.class);
        return halfDamage;
    }
    
    
    
    
    
    
    
    
}
