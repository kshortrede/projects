using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;
using UnityEngine.UI;

public class Global : MonoBehaviour
{
    public static int coins;
    public static double baseHp;

    public static int archerPrice = 100;
    public static int fighterPrice = 150;
    public static int[] heroPrices = { 500, 500, 500, 500 };

    Stopwatch timer = new Stopwatch();

    public static float DemaciaBaseHP = 5000;
    public static float NoxusBaseHP = 5000;

    public static bool insideUnit = false;

    void Start()
    {
        timer.Start();
    }
    void Update()
    {
        Text text = GameObject.Find("CoinsAmount").GetComponent<Text>();
        text.text = "" + Global.coins;

        //Generate units every few seconds
        //UnityEngine.Debug.Log("Time: " + timer.Elapsed);
        if (timer.Elapsed >= TimeSpan.FromSeconds(1))
        {
            coins += 25;
            timer.Reset();
            timer.Start();
        }
    }
}
