import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Wolf here.
 * 
 * @author Justin Wu 
 * @version V0
 */
public class Wolf extends Enemy
{
    //imported from SkeletonSpear class
    private static final int HP = 5;
    private static final double SPEED = 2;
    private static final int DELAY = 30;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 15;

    private int animationTracker; // odd = running, even = not running
    private int attackTracker; // 0, 1, 2 to decide which attack animation to use
    //Animation   
    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    private GreenfootImage[] deadPics;
    private int deadAnimationIndex;
    private int deadAnimationDelay;
    private int deadAnimationCounter;

    private GreenfootImage[] runningAttackPics;
    private int runningAttackAnimationIndex;
    private int runningAttackAnimationDelay;
    private int runningAttackAnimationCounter;

    private static boolean movable = true;

    public Wolf(){
        super(HP, SPEED, DELAY, DAMAGE, movable, ATTACK_RANGE);
        animationConstructor();
    }
    
    /**
     * Act - do whatever the Wolf wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    protected void action(Party targetPlayer){
        mainAttack();
    }

    private void mainAttack(){
        if(playersUpClose() != null){
            for(Party p: playersUpClose()){
                if (attackTracker == 0) { //attack one
                    runningAttack();
                    //stuff about dealing damage, whatever
                    if (!animationIsRunning()) {
                        attackTracker = 1;
                    }
                }
                p.takeDamage(DAMAGE);
            }
        }

        if(playersFurtherAway() != null){
            for(Party p: playersFurtherAway()){
                p.takeDamage(DAMAGE - 1);
            }
            runningAttack();
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 1;
            }
        }
    }

    public void attackAnimation() {
    }
    
    public void death() {
    }
    
    public List<Party> playersUpClose(){
        List<Party> fullDamage = getObjectsInRange(attackRange, Party.class);
        return fullDamage;
    }

    public List<Party> playersFurtherAway(){
        List<Party> halfDamage = getObjectsInRange(attackRange + 25, Party.class);
        return halfDamage;
    }

    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;

        //running
        runningPics = new GreenfootImage[9];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("W0" + (i) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 10;
        runningAnimationCounter = runningAnimationDelay;

        //dead 
        deadPics = new GreenfootImage[2];
        for (int i = 0; i < deadPics.length; i++) {
            deadPics[i] = new GreenfootImage("W1" + (i) + ".png");
            deadPics[i].scale(deadPics[i].getWidth()*2, deadPics[i].getHeight()*2);
        }
        deadAnimationIndex = 0;
        deadAnimationDelay = 10;
        deadAnimationCounter = deadAnimationDelay;

        //running attack
        runningAttackPics = new GreenfootImage[7];
        for (int i = 0; i < runningAttackPics.length; i++) {
            runningAttackPics[i] = new GreenfootImage("W2" + (i) + ".png");
            runningAttackPics[i].scale(runningAttackPics[i].getWidth()*2, runningAttackPics[i].getHeight()*2);
        }
        runningAttackAnimationIndex = 0;
        runningAttackAnimationDelay = 10;
        runningAttackAnimationCounter = runningAttackAnimationDelay;
    }

    public void running() {
        if (runningAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            runningAnimationCounter = runningAnimationDelay; // reset counter to max 
            runningAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (runningAnimationIndex == runningPics.length){
                runningAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (runningPics[runningAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            runningAnimationCounter--;
        }
    }


    public void dead() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (deadAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            deadAnimationCounter = deadAnimationDelay; // reset counter to max 
            deadAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (deadAnimationIndex == deadPics.length){
                deadAnimationIndex = 0;
                animationTracker++;
            }
            // Apply new image to this Actor
            setImage (deadPics[deadAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            deadAnimationCounter--;
        }
    }

    public void runningAttack() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (runningAttackAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            runningAttackAnimationCounter = runningAttackAnimationDelay; // reset counter to max 
            runningAttackAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (runningAttackAnimationIndex == runningAttackPics.length){
                runningAttackAnimationIndex = 0;
                animationTracker++;
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            runningAttackAnimationCounter--;
        }
    }

    public boolean animationIsRunning() {
        return animationTracker %2 == 1; //this means that the animation is running, 
    }
    protected void repelOtherEnemies(){
        List<Wolf> wolves = getObjectsAtOffset(-attackRange, 0, Wolf.class);
        if(wolves.size() > 3){
            movable = false;
        }
        movable = true;
    }
    /*
    protected void action(Party targetPlayer){
        targetPlayer.takeDamage(DAMAGE + Greenfoot.getRandomNumber(1));
        //attack animations
    }
    */
}
