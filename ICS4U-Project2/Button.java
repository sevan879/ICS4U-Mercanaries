import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private int buttonChooser;
    private GreenfootImage[] buttons;
    private MainWorld m;
    private Backstory b;
    private Settings s;
    private GreenfootImage image;

    //constructor
    public Button(int buttonChooser) {
        buttons = new GreenfootImage[3];
        buttons[0] = new GreenfootImage("startButton.png");
        buttons[1] = new GreenfootImage("settingsButton.png");
        buttons[2] = new GreenfootImage("backstoryButton.png");
        image = buttons[buttonChooser];
        setImage(image);
        this.buttonChooser = buttonChooser;
        m = new MainWorld();
        b = new Backstory();
        s = new Settings();
    }

    //act method
    public void act()
    {
        if (Greenfoot.mouseMoved(this)) {
            
        }
        if (Greenfoot.mouseClicked(this)) {
            if (buttonChooser == 0) {
                Greenfoot.setWorld(m);
                m.started();
            }
            if (buttonChooser == 1) {
                Greenfoot.setWorld(s);
            }
            if (buttonChooser == 2) {
                Greenfoot.setWorld(b);
            }        
        }
    }
}
