using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class AIUnitGenerator : MonoBehaviour
{
    public GameObject archer;
    public GameObject fighter;
    public GameObject darius;
    public GameObject garen;
    public GameObject swain;
    public GameObject jarvan;

    public List<GameObject> noxusSpawns = new List<GameObject>();

    Stopwatch timer = new Stopwatch();

    // Start is called before the first frame update
    void Start()
    {
        timer.Start();
    }

    // Update is called once per frame
    void Update()
    {
        //Change this to produce depending on the level of the user and he time elapsed, or the amount of units the user has
        if (timer.Elapsed >= TimeSpan.FromSeconds(10))
        {
            UnityEngine.Debug.Log("Create me a unit");
            GenerateUnit();
            timer.Restart();
        }


    }
    void GenerateUnit()
    {
        GameObject newBorn = RandomChoice();

        System.Random rnd = new System.Random();
        int pos = rnd.Next(0, 4);
        Instantiate(newBorn, noxusSpawns[pos].transform.position, Quaternion.identity);
    }

    private GameObject RandomChoice()
    {
        System.Random rnd = new System.Random();
        int x = rnd.Next(0, 100);
        
        if(x < 5)
        {
            int hero = rnd.Next(0, 2);
            if(hero == 1)
            {
                return swain;
            } else
            {
                return darius;
            }
        } else if(x > 5 & x < 35)
        {
            return fighter;
        } else {
            return archer;
        }
    }

}
