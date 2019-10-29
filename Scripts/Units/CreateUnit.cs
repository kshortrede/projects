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
        
    }

    public void UpdateButtonState()
    {
        if(Global.coins < Global.archerPrice)
        {
            anim[0].SetTrigger("Disabled");
            anim[1].SetTrigger("Disabled");
            anim[2].SetTrigger("Disabled");
        } else if (Global.coins > Global.archerPrice && Global.coins < Global.fighterPrice)
        {
            //Allow only archers to be clicked
            anim[0].ResetTrigger("Disabled");
            anim[1].SetTrigger("Disabled");
            anim[2].SetTrigger("Disabled");
        } else if (Global.coins > Global.fighterPrice)
        {
            anim[0].ResetTrigger("Disabled");
            anim[1].ResetTrigger("Disabled");
            //Check for Hero
        }
        
    }

    public void createArcher()
    {
        System.Random rnd = new System.Random();
        int pos = rnd.Next(0, 4);
        Instantiate(archer, DemaciaSpawns[pos].transform.position, Quaternion.identity);
    }
    public void createFighter()
    {
        System.Random rnd = new System.Random();
        int pos = rnd.Next(0, 4);
        Instantiate(fighter, DemaciaSpawns[pos].transform.position, Quaternion.identity);
    }
    public void createDarius()
    {
        System.Random rnd = new System.Random();
        int pos = rnd.Next(0, 4);
        Instantiate(darius, DemaciaSpawns[pos].transform.position, Quaternion.identity);
    }
    public void createGaren()
    {
        System.Random rnd = new System.Random();
        int pos = rnd.Next(0, 4);
        Instantiate(garen, DemaciaSpawns[pos].transform.position, Quaternion.identity);
    }
    public void createJarvan()
    {
        System.Random rnd = new System.Random();
        int pos = rnd.Next(0, 4);
        Instantiate(jarvan, DemaciaSpawns[pos].transform.position, Quaternion.identity);
    }
    public void createSwain()
    {
        System.Random rnd = new System.Random();
        int pos = rnd.Next(0, 4);
        Instantiate(swain, DemaciaSpawns[pos].transform.position, Quaternion.identity);
    }
}
