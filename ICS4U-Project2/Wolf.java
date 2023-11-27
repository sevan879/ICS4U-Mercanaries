import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Wolf here.
 * 
 * @author Justin Wu  
 * @version (a version number or a date)
 */
public class Wolf extends Enemy
{
    private static final int HP = 5;
    private static final double SPEED = 4;
    private static final int DELAY = 10;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 95;
    private static boolean movable = true;

    //Animation   
    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    private GreenfootImage[] attackOnePics;
    private int attackOneAnimationIndex;
    private int attackOneAnimationDelay;
    private int attackOneAnimationCounter;

    private GreenfootImage[] attackTwoPics;
    private int attackTwoAnimationIndex;
    private int attackTwoAnimationDelay;
    private int attackTwoAnimationCounter;

    private GreenfootImage[] attackThreePics;
    private int attackThreeAnimationIndex;
    private int attackThreeAnimationDelay;
    private int attackThreeAnimationCounter;
    
    private GreenfootSound c;

    //constructor
    public Wolf() {
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        setImage(runningPics[1]);
        animationConstructor();
        c = new GreenfootSound("claw.mp3");
        c.setVolume(40);
    }

    //act method
    public void act()
    {
        super.act();
    }

    protected void action(Party targetPlayer){
        if(playersUpClose() != null){
            for(Party p: playersUpClose()){
                p.takeDamage(DAMAGE);
                c.play();
            }
        }

        if(playersFurtherAway() != null){
            for(Party p: playersFurtherAway()){
                p.takeDamage(DAMAGE - 1);
                c.play();
            }
        }
    }

    public void attackAnimation() {
        if (attackTracker == 0) { //attack one
            System.out.println("1");
            attackOne();
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 1;
            }
        }
        else if (attackTracker == 1) { //attack two
            attackTwo();
            System.out.println("2");
            //stuff about dealing damage, whatever
            if (!animationIsRunning()) {
                attackTracker = 2;
            }
        }
        else if (attackTracker == 2) { //attack three
            attackThree();
            System.out.println("3");

            //stuff about dealing damage, whatever, this one should deal the most since its the last attack
            if (!animationIsRunning()) {
                attackTracker = 0;
            }
        }
    }

    //ANIMATION
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

    public void attackOne() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackOneAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackOneAnimationCounter = attackOneAnimationDelay; // reset counter to max 
            attackOneAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackOneAnimationIndex == attackOnePics.length){
                animationTracker++;
                attackOneAnimationIndex = 0;
            }
            if (attackOneAnimationIndex == 3) {
                setLocation(getX() - 20, getY()-9);
            }
            if (attackOneAnimationIndex == 4) {
                setLocation(getX() + 20, getY()+9);
            }
            // Apply new image to this Actor
            setImage (attackOnePics[attackOneAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }

    public void attackTwo() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackTwoAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackTwoAnimationCounter = attackTwoAnimationDelay; // reset counter to max 
            attackTwoAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackTwoAnimationIndex == attackTwoPics.length){
                animationTracker++;
                attackTwoAnimationIndex = 0;
            }
            // Apply new image to this Actor
            if (attackTwoAnimationIndex == 3) {
                setLocation(getX()-20, getY()-25);
            }
            if (attackTwoAnimationIndex == 5) {
                setLocation(getX()+20, getY()+25);
            }
            setImage (attackTwoPics[attackTwoAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackTwoAnimationCounter--;
        }
    }

    public void attackThree() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackThreeAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackThreeAnimationCounter = attackThreeAnimationDelay; // reset counter to max 
            attackThreeAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackThreeAnimationIndex == attackThreePics.length){
                animationTracker++;
                attackThreeAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (attackThreePics[attackThreeAnimationIndex]);

        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackThreeAnimationCounter--;
        }
    }

    public void death() {
        isDying = true;
        boolean remove = false;
        if (deathAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            deathAnimationCounter = deathAnimationDelay; // reset counter to max 
            deathAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (deathAnimationIndex == deathPics.length){
                remove = true;
                deathAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setLocation(getX(), getY()+5);
            setImage (deathPics[deathAnimationIndex]);
            if (deathAnimationIndex > 2) {
                setLocation(getX(), getY()+25);
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            deathAnimationCounter--;
        }
        if (remove) {
            getWorld().removeObject(this);
        }
    }

    protected void pickUp() {
        movable = false;
    }

    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;
        //death
        deathPics = new GreenfootImage[2];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("W1" + (i+1) + ".png");
            deathPics[i].scale(deathPics[i].getWidth()*2, deathPics[i].getHeight()*2);
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 10;
        deathAnimationCounter = deathAnimationDelay;

        //running
        runningPics = new GreenfootImage[9];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("W0" + (i) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 8;
        runningAnimationCounter = runningAnimationDelay;

        attackOnePics = new GreenfootImage[6];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("WA" + (i+1) + ".png");
            attackOnePics[i].scale(attackOnePics[i].getWidth()*2, attackOnePics[i].getHeight()*2);
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 8;
        attackOneAnimationCounter = attackOneAnimationDelay;

        attackTwoPics = new GreenfootImage[4];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("WAA" + (i+1) + ".png");
            attackTwoPics[i].scale(attackTwoPics[i].getWidth()*2, attackTwoPics[i].getHeight()*2);
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 8;
        attackTwoAnimationCounter = attackTwoAnimationDelay;

        attackThreePics = new GreenfootImage[5];
        for (int i = 0; i < attackThreePics.length; i++) {
            attackThreePics[i] = new GreenfootImage("WAAA" + (i+1) + ".png");
            attackThreePics[i].scale(attackThreePics[i].getWidth()*2, attackThreePics[i].getHeight()*2);
        }
        attackThreeAnimationIndex = 0;
        attackThreeAnimationDelay = 8;
        attackThreeAnimationCounter = attackThreeAnimationDelay;
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
