import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class representing all button assets in the game
 * 
 * @author Arthur 
 * @version V1
 */
public class Button extends Actor
{
    private int buttonChooser;
    private GreenfootImage[] buttons;
    private TitleScreen t;
    private GreenfootImage image;

    /**
     * Main Constructor for Button.
     * @param buttonChooser The specific button variation to create.
     */
    public Button(int buttonChooser) {
        buttons = new GreenfootImage[21];
        buttons[0] = new GreenfootImage("startButton.png");
        buttons[1] = new GreenfootImage("settingsButton.png");
        buttons[2] = new GreenfootImage("backstoryButton.png");
        buttons[3] = new GreenfootImage("spaceToContinue.png");
        buttons[4] = new GreenfootImage("k.png");
        buttons[5] = new GreenfootImage("h.png");
        buttons[6] = new GreenfootImage("m.png");
        for(int i = 0; i < 9; i++){
             buttons[i+7] = new GreenfootImage(i + ".png");
             buttons[i+7].scale(buttons[i+7].getHeight()/30, buttons[i+7].getWidth()/30);
        }
        buttons[16] = new GreenfootImage("back.png");
        buttons[17] = new GreenfootImage("Wave1.png");
        buttons[18] = new GreenfootImage("Wave2.png");
        buttons[19] = new GreenfootImage("Wave3.png");
        buttons[20] = new GreenfootImage("d.png");
    
        setImage(buttons[buttonChooser]);
        image = buttons[buttonChooser];
        setImage(image);
        this.buttonChooser = buttonChooser;
    }

    /**
     * Act method.
     */
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
                t.transitionToNewWorld(1);
            }
            if (buttonChooser == 2) {
                t.transitionToNewWorld(2);
            }    
            if(buttonChooser == 3 || buttonChooser == 16){
                t = new TitleScreen();
                Greenfoot.setWorld(t);
            }
        }
    }
}
