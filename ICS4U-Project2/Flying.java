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

    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private static boolean movable = true;
    
    private boolean pickedUpEnemy = false;
    public Flying(){
        super(HP, SPEED, DELAY, DAMAGE, movable, ATTACK_RANGE);
    }

    /**
     * Act - do whatever the Flying wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.getRandomNumber(10) == 0){
            //pickUpSwordEnemy();
            //dropSwordEnemy();
        }else{
            super.act();
        }
        
        
    }
    
    protected void action(Party targetPlayer){
        targetPlayer.takeDamage(DAMAGE + Greenfoot.getRandomNumber(1));
        //attack animations
    }
    
    protected void repelOtherEnemies(){
        List<Flying> flyings = getObjectsAtOffset(-attackRange, 0, Flying.class);
        if(flyings.size() > 3){
            movable = false;
        }
        movable = true;
    }
    /*
    public void pickUpSwordEnemy(){
        List<Sword> swords = getWorld().getObjects(Sword.class);
        if(!swords.isEmpty()){
            int randomIndex = Greenfoot.getRandomNumber(swords.size());
            Sword randomSword = swords.get(randomIndex);
            turnTowards(randomSword.getX(), randomSword.getY());
            while(getY() - randomSword.getY() > 15){
                move(speed);
            }
            randomWarrior.pickUp();
            //pickup animations
            pickedUpEnemy = true;
            //return randomSword;
        }
        
        
    }
    
    public void dropSwordEnemy(){
        if(pickedUpEnemy){
            List<Party> party = getWorld().getObjects(Party.class);
            if(!party.isEmpty()){
                int randomIndex = Greenfoot.getRandomNumber(party.size());
                Party randomParty = party.get(randomIndex);
                while(getX() != randomParty.getX()){
                    move();
                }
                Sword carrying = (Sword) getOneIntersectingObject(Sword.class);
                carrying.fall();
                pickedUpEnemy = false;
                flyBack();
            }else{
                Sword carrying = (Sword) getOneIntersectingObject(Sword.class);
                if(carrying != null){
                    carrying.fall();
                }
                
                pickedUpEnemy = false;
            }
            
        }

    }
    */
    public void running() { //or flying, i guess...

    }

    public void attackAnimation() {

    } 

    public void death() {
        isDying = true;
        if (deathAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            deathAnimationCounter = deathAnimationDelay; // reset counter to max 
            deathAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (deathAnimationIndex == deathPics.length){
                deathAnimationIndex = 0;
                getWorld().removeObject(this);
            }
            // Apply new image to this Actor
            setImage (deathPics[deathAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            deathAnimationCounter--;
        }
    }
    
    public void animationConstructor() {
                animationTracker = 0;
        attackTracker = 0;
    }
    public void flyBack(){
        //flip images
        List<Enemy> enemy = getWorld().getObjects(Enemy.class);
        if(!enemy.isEmpty()){
            int randomIndex = Greenfoot.getRandomNumber(enemy.size());
            Enemy randomEnemy = enemy.get(randomIndex);
            while(getX() != randomEnemy.getX()){
                setLocation(getX() + speed, getY() + Greenfoot.getRandomNumber(2) - 1);
            }
            //flip images
        }else{
            while(getX() != 400){
                setLocation(getX() + speed, getY() + Greenfoot.getRandomNumber(2) - 1);
            }
            //flip images
        }
    }
}
