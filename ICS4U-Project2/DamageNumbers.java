import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Damage Numbers that show up when an entity is damaged/healed.
 * 
 * @author Evan Ma
 * @version V1
 */
public class DamageNumbers extends Effects
{
    private String text;
    private Color color;
    
    private static final int TOTAL_TIME = 85;
    private int currentTime = 0;
    private int fontSize;
    
    
    /**
     * Main Constructor for DamageNumbers
     * @param amount The amount of Damage Specified
     * @param type Determines if it is healing or damage. true = damage, false = healing.
     */
    public DamageNumbers(int amount, boolean type)
    {
        text = Integer.toString(amount);
        if (type)
        {
            color = Color.RED;
            fontSize = 25;
        }
        else
        {
            color = Color.GREEN;
            fontSize = 40;
        }
        image = new GreenfootImage(text, fontSize, color, null);
        setImage(image);
    }
    /**
     * Act - do whatever the DamageNumbers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {   
        if (currentTime >= TOTAL_TIME)
        {
            getWorld().removeObject(this);
        }
        else
        {
            if (currentTime % 3 == 0)
            {
                setLocation(getX(), getY() - 1.5);
            }
            fadeOut(currentTime, TOTAL_TIME);
            currentTime ++;
        }
    }
}
