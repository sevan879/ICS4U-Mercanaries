import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Settings for simulation changes
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
    private static int numKnights = 4;
    private Button knightsLabel;
    private SettingsButton kU;
    private SettingsButton kD;
    
    private static int numHealers = 1;
    private Button healersLabel;
    private SettingsButton hU;
    private SettingsButton hD;
    
    private static int numMages = 1;
    private Button magesLabel;
    private SettingsButton mU;
    private SettingsButton mD;
    
    private static int numDarkMages = 0;
    private Button darkMagesLabel;
    private SettingsButton dU;
    private SettingsButton dD;
    
    private static int waveOne = 5;
    private Button waveOneLabel;
    private SettingsButton oneU;
    private SettingsButton oneD;
    
    private static int waveTwo = 15;
    private Button waveTwoLabel;
    private SettingsButton twoU;
    private SettingsButton twoD;
    
    private static int waveThree = 25;
    private Button waveThreeLabel;
    private SettingsButton threeU;
    private SettingsButton threeD;
    
    /**
     * Getter for the amount of Knights to add.
     * @return int
     */
    public static int getNumOfKnights()
    {
        return numKnights;
    }
    /**
     * Getter for the amount of Mages to add.
     * @return int
     */
    public static int getNumOfMages()
    {
        return numMages;
    }
    /**
     * Getter for the amount of Dark Mages to add.
     * @return int
     */
    public static int getNumOfDarkMages()
    {
        return numDarkMages;
    }
    /**
     * Getter for the amount of Healers to add.
     * @return int
     */
    public static int getNumOfHealers()
    {
        return numHealers;
    }
    /**
     * Getter for the amount of wave one enemies to add.
     * @return int
     */
    public static int getWaveOne()
    {
        return waveOne;
    }
    /**
     * Getter for the amount of wave two enemies to add.
     * @return int
     */
    public static int getWaveTwo()
    {
        return waveTwo;
    }
    /**
     * Getter for the amount of wave three enemies to add.
     * @return int
     */
    public static int getWaveThree()
    {
        return waveThree;
    }
    
    /**
     * Main Constructor for Settings
     */
    public Settings()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("SettingsBackground.png");
        setBackground(background);
        numKnights = 4;
        numHealers = 1;
        numMages = 1;
        numDarkMages = 0;
        current = 6;
        waveOne = 5;
        waveTwo = 15;
        waveThree = 20;
        addObject(new Button(16), 534, 650);

        
        kD = new SettingsButton(false);
        kU = new SettingsButton(true);
        
        hD = new SettingsButton(false);
        hU = new SettingsButton(true);
        
        mD = new SettingsButton(false);
        mU = new SettingsButton(true);
        
        dD = new SettingsButton(false);
        dU = new SettingsButton(true);
        
        oneU = new SettingsButton(true);
        oneD = new SettingsButton(false);
        
        twoU = new SettingsButton(true);
        twoD = new SettingsButton(false);
        
        threeU = new SettingsButton(true);
        threeD = new SettingsButton(false);
        
        addObject(knightsLabel = new Button(4), 534, 100);
        addObject(kD, knightsLabel.getX() - 150, knightsLabel.getY());
        addObject(kU, knightsLabel.getX() + 200, knightsLabel.getY());
        
        addObject(healersLabel = new Button(5), 534, 170);
        addObject(hD, healersLabel.getX() - 150, healersLabel.getY());
        addObject(hU, healersLabel.getX() + 200, healersLabel.getY());
        
        addObject(magesLabel = new Button(6), 534, 240);
        addObject(mD, magesLabel.getX() - 150, magesLabel.getY());
        addObject(mU, magesLabel.getX() + 200, magesLabel.getY());
        
        addObject(darkMagesLabel = new Button(20), 534, 310);
        addObject(dD, darkMagesLabel.getX() - 150, darkMagesLabel.getY());
        addObject(dU, darkMagesLabel.getX() + 200, darkMagesLabel.getY());
        
        addObject(waveOneLabel = new Button(17), 534, 380);
        addObject(oneD, waveOneLabel.getX() - 150, waveOneLabel.getY());
        addObject(oneU, waveOneLabel.getX() + 200, waveOneLabel.getY());
        
        addObject(waveTwoLabel = new Button(18), 534, 450);
        addObject(twoD, waveTwoLabel.getX() - 150, waveTwoLabel.getY());
        addObject(twoU, waveTwoLabel.getX() + 200, waveTwoLabel.getY());
        
        addObject(waveThreeLabel = new Button(19), 534, 520);
        addObject(threeD, waveThreeLabel.getX() - 150, waveThreeLabel.getY());
        addObject(threeU, waveThreeLabel.getX() + 200, waveThreeLabel.getY());
        
        set(0);
        set(1);
        set(2);
        set(3);
        set(4);
        set(5);
        set(6);
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
        
        if (Greenfoot.mouseClicked(dU)) {
            if(current < MAX_PARTY){
                numDarkMages++;
                current++;
                set(6);
            }
        }
        if (Greenfoot.mouseClicked(dD)) {
            if(current > 0 && numDarkMages >0){
                numDarkMages--;
                current--;
                set(6);
            }
        }
        
        if (Greenfoot.mouseClicked(oneU)) {
            waveOne++;
            set(3);
        }
        if (Greenfoot.mouseClicked(oneD)) {
            if (waveOne > 0)
            {
                waveOne--;
                set(3);
            }
        }
        if (Greenfoot.mouseClicked(twoU)) {
            waveTwo++;
            set(4);
        }
        if (Greenfoot.mouseClicked(twoD)) {
            if (waveTwo > 0)
            {
                waveTwo--;
                set(4);

            }
        }
        if (Greenfoot.mouseClicked(threeU)) {
            waveThree++;
            set(5);
        }
        if (Greenfoot.mouseClicked(threeD)) {
            if (waveThree > 0)
            {
                waveThree--;
                set(5);
            }
        }
    }
    private void set(int type){
        if(type == 0){
            addObject(new Button(7+numKnights),knightsLabel.getX() + 135, knightsLabel.getY());
        }
        else if(type == 1){
            addObject(new Button(7+numHealers),healersLabel.getX() + 135, healersLabel.getY());
        }
        else if (type == 2){
            addObject(new Button(7+numMages),magesLabel.getX() + 135, magesLabel.getY());
        }
        else if (type == 3) {
            addObject(new SuperTextBox(Integer.toString(waveOne), new Font("OptimusPrinceps", true, false , 20), 50), waveOneLabel.getX() + 135, waveOneLabel.getY());
        }
        else if (type == 4) {
            addObject(new SuperTextBox(Integer.toString(waveTwo), new Font("OptimusPrinceps", true, false , 20), 50), waveTwoLabel.getX() + 135, waveTwoLabel.getY());
        }
        else if (type == 5) {
            addObject(new SuperTextBox(Integer.toString(waveThree), new Font("OptimusPrinceps", true, false , 20), 50), waveThreeLabel.getX() + 135, waveThreeLabel.getY());
        }
        else if (type == 6){
            addObject(new Button(7+numDarkMages),darkMagesLabel.getX() + 135, darkMagesLabel.getY());
        }
    }
}
