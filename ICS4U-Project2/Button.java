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
    private TitleScreen t;

    //constructor
    public Button(int buttonChooser) {
        buttons = new GreenfootImage[4];
        buttons[0] = new GreenfootImage("startButton.png");
        buttons[1] = new GreenfootImage("settingsButton.png");
        buttons[2] = new GreenfootImage("backstoryButton.png");
        buttons[3] = new GreenfootImage("spaceToContinue.png");
        setImage(buttons[buttonChooser]);
        this.buttonChooser = buttonChooser;
    }

    //act method
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            m = new MainWorld();
            b = new Backstory();
            s = new Settings();
            t = new TitleScreen();
            if (buttonChooser == 0) {
                Greenfoot.setWorld(m);
            }
            if (buttonChooser == 1) {
                Greenfoot.setWorld(s);
            }
            if (buttonChooser == 2) {
                Greenfoot.setWorld(b);
            }    
            if(buttonChooser == 3){
                Greenfoot.setWorld(t);
            }
        }
    }
}
