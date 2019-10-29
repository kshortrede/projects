using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum UnitType {DEMACIA, NOXUS};

public abstract class Unit : object
{
    protected string name;
    protected float hp;
    protected float movSpeed;
    protected float damage;

    protected int coins; //Coins it gives when it dies
    protected int cost;  //Coins it costs to purchase
    protected UnitType faction;

    //Every unit will have a list of it's attacker, with no more than 3 enemies fighting the unit at a time
    public List<GameObject> attackers = new List<GameObject>(); 

    public void setName(string n)
    {
        name = n;
    }
    public global::System.String Name {
        get => name;
        set => name = value;
    }
    
    public UnitType Faction { get => faction; set => faction = value; }
    public float Hp { get => hp; set => hp = value; }
    public float MovSpeed { get => movSpeed; set => movSpeed = value; }
    public float Damage { get => damage; set => damage = value; }
    public void addAttacker(GameObject g)
    {
        attackers.Add(g);
    }
    public void removeAttacker(GameObject g)
    {
        attackers.Remove(g);
    }
    public int getAttackersNumber()
    {
        return attackers.Count;
    }


    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
