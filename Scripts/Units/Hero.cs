using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum HeroType {GAREN, JARVAN, DARIUS, SWAIN, FIGHTER, ARCHER};
public enum AbilityType {SPIN, EMPOWER, EXECUTE, HEAL}

public class Hero : Unit
{
    public HeroType myHero = new HeroType();
    private AbilityType myAbility = new AbilityType();
    

    public HeroType getHeroType()
    {
        return myHero;
    }
    public void setHeroType(HeroType type)
    {
        myHero = type;
        initiateHero();
    }

    public Hero (HeroType type){
        setHeroType(type);
        initiateHero();    
    }
    public Hero()
    {

    }
    


    public void initiateHero()
    {
        switch (myHero)
        {
            case HeroType.GAREN:
                myAbility = AbilityType.SPIN;
                name = "Garen";
                hp = 200;
                movSpeed = 6;
                damage = 40;
                break;
            case HeroType.JARVAN:
                myAbility = AbilityType.EMPOWER;
                name = "Jarvan IV";
                movSpeed = 4;
                damage = 30;
                hp = 400;
                break;
            case HeroType.DARIUS:
                myAbility = AbilityType.EXECUTE;
                name = "Darius";
                movSpeed = 4;
                hp = 500;
                damage = 60;
                break;
            case HeroType.SWAIN:
                myAbility = AbilityType.HEAL;
                name = "Swain";
                hp = 300;
                movSpeed = 7;
                damage = 20;
                break;
            case HeroType.FIGHTER:
                myAbility = AbilityType.HEAL;
                name = "Fighter"; //Make name random from a list
                hp = 200;
                movSpeed = 10;
                damage = 30;
                break;
            case HeroType.ARCHER:
                myAbility = AbilityType.HEAL;
                name = "Archer"; //Make name random from a list
                hp = 150;
                movSpeed = 5;
                damage = 20;
                break;
        }
    }
}
