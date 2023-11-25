import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @Arthur 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private int buttonChooser;
    private GreenfootImage[] buttons;
    private TitleScreen t;
    private GreenfootImage image;

    //constructor
    public Button(int buttonChooser) {
        buttons = new GreenfootImage[4];
        buttons[0] = new GreenfootImage("startButton.png");
        buttons[1] = new GreenfootImage("settingsButton.png");
        buttons[2] = new GreenfootImage("backstoryButton.png");
        buttons[3] = new GreenfootImage("spaceToContinue.png");
        setImage(buttons[buttonChooser]);
        image = buttons[buttonChooser];
        setImage(image);
        this.buttonChooser = buttonChooser;
    }

    //act method
    public void act()
    {
        if (Greenfoot.mouseMoved(this)) { //hover effect
            
        }
        if (Greenfoot.mouseClicked(this)) {
            t = new TitleScreen();
            if (buttonChooser == 0) {
                t.transitionToNewWorld(0);
            }
            if (buttonChooser == 1) {
                
            }
            if (buttonChooser == 2) {
                
            }    
            if(buttonChooser == 3){
                
            }
        }
    }
}
