import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Archer extends Enemy
{
    private static final int HP = 2;
    private static final double SPEED = 1;
    private static final int DELAY = 100;
    private static final int DAMAGE = 3;
    private static final int ATTACK_RANGE = 200;
    private static boolean movable = true;
    
    
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
        move();
        action(getTarget());
        
    }
    
    public Archer(){
        super(HP, SPEED, DELAY, DAMAGE, movable, ATTACK_RANGE);
        
    }
    
    int delay = DELAY;
    protected void action(Party targetPlayer){
        if(getTarget() != null){
            if(delay == 0){
                getWorld().addObject(new Arrow(5, -1, 200), getX(), getY());
                delay = DELAY;
            }else{
                delay--;
        }
        
        
        //attack animations
        }
        
        
        
        //attack animations
    }
    
    protected void repelOtherEnemies(){
        List<Archer> archers = getObjectsAtOffset(-attackRange, 0, Archer.class);
        if(archers.size() > 3){
            movable = false;
        }
        movable = true;
    }
    
    public Party getTarget(){
        List<Party> players = getObjectsInRange(attackRange, Party.class);
        
        if(!players.isEmpty()){
            int randomIndex = Greenfoot.getRandomNumber(players.size());
            return players.get(randomIndex);
        }
        return null;
    }
    
    
    
    /*
    public Party targetPastTanks(){
        List<Party> players = getObjectsInRange(attackRange, Party.class);

        if (!players.isEmpty()) {
            if(targetPlayer() instanceof Knight){
                players.remove(0);
            }
            return players.get(0); 
        }

        return null;
        
    }
    */
    
    
    
    
    
}
