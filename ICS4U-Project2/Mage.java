import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mage extends Party
{
    //BASE STATS AT LEVEL ONE
    private static final int SET_HP = 4;
    private static final double SET_SPEED = 3;
    private static final int ACTION_DELAY = 150; // amount of acts
    private static final int XP_INCREASE_PER_LEVEL = 1;
    private static final int ATTACK_RANGE = 160;
    private static final int MAX_MANA = 100;
    private static final int MAX_LEVEL = 4;
    private static final boolean MANA_CLASS = true;
    
    //stats that are increased on each level up
    private static final int DAMAGE_INCREASE = 1; 
    private static final int HEALTH_INCREASE = 2; 
    private static final int RANGE_INCREASE = 0;
    private static final int MANA_INCREASE = 0;

    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private int spellLevel = 0;

    private int damage = 5;

    private int smallSpellMana;
    private int bigSpellMana;

    public Mage()
    {
        super(SET_HP, SET_SPEED, ACTION_DELAY, false, XP_INCREASE_PER_LEVEL, ATTACK_RANGE, MAX_MANA, MAX_LEVEL, MANA_CLASS);
        
    }

    public void act()
    {
        super.act();
    }

    protected void mainAction(Enemy target)
    {
        int dealtDamage = damage + Greenfoot.getRandomNumber(2);
        target.takeDamage(dealtDamage);
        //Make Enemy take damage
    }

    protected void mainAnimation() {

    }

    protected void levelUpStats()
    {
        maxHealth += HEALTH_INCREASE;
        health = maxHealth;

        maxMana += MANA_INCREASE;
        mana = maxMana;

        damage += DAMAGE_INCREASE;
        attackRange += RANGE_INCREASE;
    }

    private void smallSpell()
    {
        spendMana(smallSpellMana);
        // CREATE PROJECTILE OBJECT
    }

    private void bigSpell()
    {
        spendMana(bigSpellMana);
        // CREATE PROJECTILE OBJECT
    }

    public void running()
    {

    }

    public void idle() {

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
        
    }
}
