import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game Over screen world. 
 * 
 * @author Arthur Tian 
 * @version V1
 */
public class EndScreen extends World
{

    
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
     * Constructor for EndScreen.
     * @param cohenWon Varies the end screen based on boolean value. True = Party Lost. False = Party Won.
     */
    public EndScreen(boolean cohenWon)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        GreenfootImage img1 = new GreenfootImage("CohenWins.png");
        GreenfootImage img2 = new GreenfootImage("CohenLoses.png");
        img1.scale(1068, 720);
        img2.scale(1068, 720);
        if (cohenWon) {
            music = new GreenfootSound("Naktigonis - The End. (Deepwoken OST).mp3");
            music.setVolume(60);
            music.playLoop();
            setBackground(img1);
        }
        else {
            music = new GreenfootSound("Naktigonis - A Memory Of Tides (Deepwoken OST).mp3");
            music.setVolume(60);
            music.playLoop();
            setBackground(img2);
        }
        addObject(new Button(16), 534, 360);
        
        addObject(new SuperTextBox("Total Kills: " + MainWorld.getKills(), new Font("OptimusPrinceps", true, false , 20), 240), 150, 550);
        addObject(new SuperTextBox("Damage Dealt: " + MainWorld.getDamageDealt(), new Font("OptimusPrinceps", true, false , 20), 240), 450, 550);
        addObject(new SuperTextBox("Damage Taken: " + MainWorld.getDamageTaken(), new Font("OptimusPrinceps", true, false , 20), 240), 150, 610);
        addObject(new SuperTextBox("Total Time: " + MainWorld.getTime(), new Font("OptimusPrinceps", true, false , 20), 240), 450, 610);
    }
    
    /**
     * When simulation is stopped
     */
    public void stopped()
    {
        Button.stopped();
    }
}