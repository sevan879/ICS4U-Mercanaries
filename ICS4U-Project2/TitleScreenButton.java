import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreenButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreenButton extends Button
{

    int buttonChooser;
    private GreenfootImage[] titleScreenButtons;
    
    //constructor
    public TitleScreenButton(int buttonChooser) {
        titleScreenButtons = new GreenfootImage[3];
        titleScreenButtons[0] = new GreenfootImage("startButton.png");
        titleScreenButtons[1] = new GreenfootImage("settingsButton.png");
        titleScreenButtons[2] = new GreenfootImage("backstoryButton.png");
        setImage(titleScreenButtons[buttonChooser]);
    }
    
    //act method
    public void act()
    {
        
        // Add your action code here.
        if (Greenfoot.isKeyDown("s")) {
            startButtonAction();
        }
    }
    
    //methods that react to the buttons being pressed and set a world
    public void startButtonAction() {
        MainWorld m = new MainWorld();
        Greenfoot.setWorld(m);
    }
    
    public void settingsButtonAction() {
        Settings s = new Settings();
        Greenfoot.setWorld(s);
    }
    
    public void backstoryButtonAction() {
        Backstory b = new Backstory();
        Greenfoot.setWorld(b);
    }
}
