using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateUnit : MonoBehaviour
{
    public GameObject archer;
    public GameObject fighter;
    public GameObject darius;
    public GameObject garen;
    public GameObject swain;
    public GameObject jarvan;

    public List<GameObject> DemaciaSpawns = new List<GameObject>();

    //Button[0] is Archer
    //Button[1] is Fighter
    //Button[2] is Hero
    public GameObject[] buttons;
    public Animator[] anim;

    //Finish these methods to be able to create different units
    //Finish these to be able to create units from a Noxus perspective
    //Finish these from editor to produce the right prefabs
    

    private void Start()
    {
        anim[0] = buttons[0].GetComponent<Animator>();
        anim[1] = buttons[1].GetComponent<Animator>();
        anim[2] = buttons[2].GetComponent<Animator>();
    }
    void Update()
    {
        UpdateButtonStateDisable();
        UpdateButtonStateEnable();
    }

    public void UpdateButtonStateDisable()
    {
        if (Global.coins <= 0 || Global.coins < Global.archerPrice)
        {
            anim[0].SetTrigger("Disabled");
            anim[0].SetBool("isDisabled", true);
        }
        if (Global.coins <= 0 || Global.coins < Global.fighterPrice)
        {
            anim[1].SetTrigger("Disabled");
            anim[1].SetBool("isDisabled", true);
        }
        if (Global.coins <= 0 || Global.coins < Global.heroPrices[0])
        {
            anim[2].SetTrigger("Disabled");
            anim[2].SetBool("isDisabled", true);
        }
        
    }
    public void UpdateButtonStateEnable()
    {
        if (Global.coins > Global.archerPrice)
        {
            anim[0].SetBool("isDisabled", false);
        }
        if (Global.coins > Global.fighterPrice)
        {
            anim[1].SetBool("isDisabled", false);
        }
        if (Global.coins > Global.heroPrices[0])
        {
            anim[2].SetBool("isDisabled", false);
        }
    }

    public void createArcher()
    {
        if(anim[0].GetBool("isDisabled") == false)
        {
            System.Random rnd = new System.Random();
            int pos = rnd.Next(0, 4);
            Instantiate(archer, DemaciaSpawns[pos].transform.position, Quaternion.identity);
            Global.coins -= Global.archerPrice;
        }
    }
    public void createFighter()
    {
        if (anim[1].GetBool("isDisabled") == false)
        {
            System.Random rnd = new System.Random();
            int pos = rnd.Next(0, 4);
            Instantiate(fighter, DemaciaSpawns[pos].transform.position, Quaternion.identity);
            Global.coins -= Global.fighterPrice;
        }
    }

    //Maybe use one method and randomize the creation of the unit
    public void createDarius()
    {
        if(anim[2].GetBool("isDisabled") == false)
        {
            System.Random rnd = new System.Random();
            int pos = rnd.Next(0, 4);
            Instantiate(darius, DemaciaSpawns[pos].transform.position, Quaternion.identity);
            Global.coins -= Global.heroPrices[0];
        }
        
    }
    public void createGaren()
    {
        if(anim[2].GetBool("isDisabled") == false)
        {
            System.Random rnd = new System.Random();
            int pos = rnd.Next(0, 4);
            Instantiate(garen, DemaciaSpawns[pos].transform.position, Quaternion.identity);
            Global.coins -= Global.heroPrices[0];
        }
    }
    public void createJarvan()
    {
        if (anim[2].GetBool("isDisabled") == false)
        {
            System.Random rnd = new System.Random();
            int pos = rnd.Next(0, 4);
            Instantiate(jarvan, DemaciaSpawns[pos].transform.position, Quaternion.identity);
            Global.coins -= Global.heroPrices[0];
        }
    }
    public void createSwain()
    {
        if (anim[2].GetBool("isDisabled") == false)
        {
            System.Random rnd = new System.Random();
            int pos = rnd.Next(0, 4);
            Instantiate(swain, DemaciaSpawns[pos].transform.position, Quaternion.identity);
            Global.coins -= Global.heroPrices[0];
        }
    }
}
