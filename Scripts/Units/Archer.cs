using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Archer : Unit
{
     const int cost = 20;
    
    // Start is called before the first frame update
    void Start()
    {
        //Generate Random Name
        hp = 50;
        movSpeed = 4;
        damage = 40;
        coins = 10;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
