import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestWorld extends World
{

    /**
     * Constructor for objects of class TestWorld.
     * 
     */
    
    private Background b;
    public TestWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        b = new Background();
        addObject(b, 1068, 360);
        b.setWorldBackground(2);
        addObject(new Cohen(), 463, 311);
        addObject(new Karel(), 700, 650);
        //addObject(new MiniCohen(), 900, 650);
        addObject(new Healer(), 100, 650);
        addObject(new Knight(), 300, 650);
        addObject(new Knight(), 200, 650);
    }
    
    public void act() {
        b.scrollBackground(1);
    }
    
}
