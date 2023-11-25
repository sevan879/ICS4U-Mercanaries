import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingsButton here.
 * 
 * @author Justin Wu
 * @version (a version number or a date)
 */
public class SettingsButton extends Actor
{
    private GreenfootImage[] buttons;
    private boolean up;
    
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
