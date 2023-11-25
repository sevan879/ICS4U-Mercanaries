import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author Justin Wu 
 * @version V0
 */
public class Settings extends World
{
    private GreenfootImage background;
    private final int MAX_PARTY = 8;
    private int current;

    //somehow transfer these numbers to the game world
    private int numKnights;
    private SettingsButton kU;
    private SettingsButton kD;
    
    private int numHealers;
    private SettingsButton hU;
    private SettingsButton hD;
    
    private int numMages;
    private SettingsButton mU;
    private SettingsButton mD;
    
    //constructor
    public Settings()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("SettingsBackground.png");
        setBackground(background);
        numKnights = 4;
        numHealers = 1;
        numMages = 1;
        current = 6;
        addObject(new Button(16), 534, 600);

        
        kD = new SettingsButton(false);
        kU = new SettingsButton(true);
        
        hD = new SettingsButton(false);
        hU = new SettingsButton(true);
        
        mD = new SettingsButton(false);
        mU = new SettingsButton(true);
        
        addObject(kD, 350, 200);
        addObject(kU, 700, 200);
        addObject(new Button(4), 500, 200);
        
        addObject(hD, 350, 300);
        addObject(hU, 700, 300);
        addObject(new Button(5), 500, 300);
        
        addObject(mD, 350, 400);
        addObject(mU, 700, 400);
        addObject(new Button(6), 500, 400);
        set(0);
        set(1);
        set(2);

    }
    
    public void act(){
        if (Greenfoot.mouseClicked(kU)) {
            if(current < MAX_PARTY){
                numKnights++;
                current++;
                set(0);
            }
        }
        if (Greenfoot.mouseClicked(kD)) {
            if(current > 0 && numKnights > 0){
                numKnights--;
                current--;
                set(0);
            }
        }
        
        if (Greenfoot.mouseClicked(hU)) {
            if(current < MAX_PARTY){
                numHealers++;
                current++;
                set(1);
            }
        }
        if (Greenfoot.mouseClicked(hD)) {
            if(current > 0 && numHealers > 0){
                numHealers--;
                current--;
                set(1);
            }
        }
        
        if (Greenfoot.mouseClicked(mU)) {
            if(current < MAX_PARTY){
                numMages++;
                current++;
                set(2);
            }
        }
        if (Greenfoot.mouseClicked(mD)) {
            if(current > 0 && numMages >0){
                numMages--;
                current--;
                set(2);
            }
        }
    }
    public void set(int type){
        if(type == 0){
            addObject(new Button(7+numKnights),635, 200);
        }
        else if(type == 1){
            addObject(new Button(7+numHealers),635, 300 );
        }
        else{
            addObject(new Button(7+numMages),635, 400);
        }
    }
}
