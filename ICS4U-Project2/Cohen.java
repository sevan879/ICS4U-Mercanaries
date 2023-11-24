import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cohen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cohen extends Boss
{
    private static final int SET_HP = 5;
    private static final int ACTION_DELAY = 120;
    private static final int[] ATTACK_SET = {1, 2, 3};
    
    public Cohen()
    {
        super(SET_HP, ACTION_DELAY, ATTACK_SET);
    }
    
    public void act()
    {
        super.act();
    }
    
    public void action(int attackNum)
    {
        if (attackNum == 1)
        {
            // First action
            singleTarget();
        }
        else if (attackNum == 2)
        {
            // Second action
        }
        else if (attackNum == 3)
        {
            // Third action
        }
    }
    
    private void singleTarget()
    {
        
    }
    
    public void death() {
        
    }
    
    public void animationConstructor() {
        
    }
}
