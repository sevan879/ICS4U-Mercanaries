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
        buttons = new GreenfootImage[17];
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

        setImage(buttons[buttonChooser]);
        image = buttons[buttonChooser];
        setImage(image);
        this.buttonChooser = buttonChooser;
    }

    //act method
    public void act()
    {
        //setImage(image);
        //if (Greenfoot.mouseMoved(this)) { //hover effect
        //    image.scale(600, 144);
        //}
        //else {
        //    image.scale(500, 120);
        //}
        if (Greenfoot.mouseClicked(this)) {
            t = (TitleScreen) getWorld();
            if (buttonChooser == 0) {
                t.transitionToNewWorld(0);
                
                t.addObject(new LoadingScreen(false, 60, true, new Background(), new MainWorld(), 1), 534, 360);
            }
            if (buttonChooser == 1) {
                t.transitionToNewWorld(1);
            }
            if (buttonChooser == 2) {
                t.transitionToNewWorld(2);
            }    
            if(buttonChooser == 3 || buttonChooser == 16){
                Greenfoot.setWorld(t);
            }
        }
    }
}
