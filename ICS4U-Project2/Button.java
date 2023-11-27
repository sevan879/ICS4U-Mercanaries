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

    
    private GreenfootSound sound;
    
    private static GreenfootSound music = null;
    /**
     * Stops all music
     */
    public static void stopMusic(){
        if (music != null)
        {
            music.stop();
        }
    }
    /**
     * Main Constructor for Button.
     * @param buttonChooser The specific button variation to create.
     */
    public Button(int buttonChooser) {
        sound = new GreenfootSound("click.mp3");
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
     * When simulation is stopped
     */
    public static void stopped()
    {
        stopMusic();
        //System.out.println("true");
    }
    
    /**
     * Act method.
     */
    public void act()
    {
        if (Greenfoot.mouseMoved(this)) { //hover effect
            if (buttonChooser == 0) {
                //setImage(new GreenfootImage("hoverStart.png"));
            }
            else if (buttonChooser == 1) {
                //setImage(new GreenfootImage("hoverSettings.png"));
            }
            else if (buttonChooser == 2) {
                //setImage(new GreenfootImage("hoverBackstory.png"));
            }
        }
        else {
            setImage(image);
        }
        if (Greenfoot.mouseClicked(this)) {
            if (buttonChooser == 0) {
                sound.play();
                t = (TitleScreen) getWorld();
                t.transitionToNewWorld(0);
                t.addObject(new LoadingScreen(false, 60, true, new Background(), new MainWorld(), 1), 534, 360);
            }
            if (buttonChooser == 1) {
                sound.play();
                music = new GreenfootSound("TitleScreen.mp3");
                music.playLoop();
                music.setVolume(50);
                t = (TitleScreen) getWorld();
                t.transitionToNewWorld(1);
            }
            if (buttonChooser == 2) {
                sound.play();
                music = new GreenfootSound("Naktigonis - A Light In The Sky (Deepwoken OST).mp3");
                music.playLoop();
                music.setVolume(50);
                t = (TitleScreen) getWorld();
                t.transitionToNewWorld(2);
            }    
            if(buttonChooser == 3 || buttonChooser == 16){
                stopMusic();
                sound.play();
                TitleScreen title = new TitleScreen();
                Greenfoot.setWorld(title);
                title.started();
                if (getWorld() instanceof EndScreen)
                {
                    EndScreen endScreen = (EndScreen)getWorld();
                    endScreen.stopMusic();
                }
            }
        }
    }
}
