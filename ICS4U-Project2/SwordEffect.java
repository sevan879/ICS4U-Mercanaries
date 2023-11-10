import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BlueSlash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SwordEffect extends Effect
{
    private GreenfootImage[] swordEffect;
    private int swordEffectIndex;
    private int swordEffectDelay;
    private int swordEffectCounter;
    
    public SwordEffect(){
        
        swordEffect = new GreenfootImage[8];
        for(int i = 0; i < swordEffect.length; i++){
            swordEffect[i] = new GreenfootImage ("B" + (8-i) + ".png");
        }
        swordEffectIndex = 0;
        swordEffectDelay = 6;
        swordEffectCounter = swordEffectDelay;
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("w")){
            SwordEffect();
        }
        else{
            
        }   
    }
    
    public void SwordEffect(){
        if(swordEffectCounter == 0){
            swordEffectCounter = swordEffectDelay;
            swordEffectIndex++;
            
            if(swordEffectIndex == swordEffect.length){
                swordEffectIndex = 0;
            }
            
            setImage (swordEffect[swordEffectIndex]);
        }
        else{
            swordEffectCounter--;
        }
    }
}
