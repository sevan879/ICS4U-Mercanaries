import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The up and down arrows for creating settings.
 * 
 * @author Justin Wu
 * @version V1
 */
public class SettingsButton extends Actor
{
    private GreenfootImage[] buttons;
    private boolean up;
    /**
     * Main Constructor for SettingButton
     * @param up Current state of button. True = button points up. False = button points down.
     */
    public SettingsButton(boolean up){
        this.up = up;
        buttons = new GreenfootImage[2];
        
        buttons[0] = new GreenfootImage("up.png");
        buttons[0].scale(buttons[0].getWidth()/8, buttons[0].getHeight()/8);
        buttons[1] = new GreenfootImage("down.png");
        buttons[1].scale(buttons[1].getWidth()/8, buttons[1].getHeight()/8);
        
        if(up){
            setImage(buttons[0]);
        }
        else{
            setImage(buttons[1]);
        }
    }

}
