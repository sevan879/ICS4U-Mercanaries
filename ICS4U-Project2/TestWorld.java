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
    private MainWorld m;
    private LoadingScreen s;
    private int n;
    public TestWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
         b = new Background();
        addObject(b, 1068, 360);
        b.setWorldBackground(4);
        //addObject(new Cohen(), 463, 311);
        addObject(new Karel(), 700, 650);
        //addObject(new MiniCohen(), 900, 650);
        addObject(new Mage(), 150, 650);
        //addObject(new Healer(), 100, 650);
        //addObject(new Knight(), 300, 650);
        //addObject(new Knight(), 200, 650);
        //addObject(new Knight(), 400, 650);
        //addObject(new Knight(), 500, 650);
        //  b = new Background(); 
        //m = new MainWorld();
        //s = new LoadingScreen(false, 60, true, b, m, 1);
        n = 0;
        //GreenfootSound s = new GreenfootSound("BossMusic.mp3");
        //s.setVolume(55);
        //s.play();
    }

    public void act() {
        b.scrollBackground(1);
        
        //if (n == 0) {
        //    addObject(s, getWidth()/2,getHeight()/2);
        //    n++;
        //}
        
    }

}
