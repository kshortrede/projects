using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;
using System.Diagnostics;

public class HeroControl : MonoBehaviour
{
    public Hero hero;
    

    private bool enemyFound = false; //Has enemies in its list
    private bool fighting = false; //Stopped Moving and started to fight
    private bool movingToEnemy = false; //Walking towards the enemy to attack
    private bool hasAttacked = false;

    private bool moving = false;

    public List<GameObject> detectedEnemies = new List<GameObject>();
    private int attackersAmt = 0;

    public UnitType type = new UnitType();
    public HeroType tmpType = new HeroType();

    //All positions that the units can choose from to move with the NavMesh
    public GameObject[,] destinations = new GameObject[5,4];
    public GameObject[] enemyBase = new GameObject[4];
    NavMeshAgent agent;
    //Keep track of the position we are in
    private int reachedPosition = 0;
    private bool reachedEnemyBase; 
    //Keep track of our NavMesh's objective
    public GameObject objective;

    private bool killedEnemy = false;
    private GameObject activeEnemy; 

    private Animator anim;

    Stopwatch timer = new Stopwatch();
    Stopwatch deathTimer = new Stopwatch();

    // Start is called before the first frame update
    void Start()
    {
        hero = new Hero();
        hero.Faction = type;
        hero.initiateHero();
        hero.setHeroType(tmpType);

        //Set the starting position for each team
        if(hero.Faction == UnitType.DEMACIA)
        {
            reachedPosition = 4;
            enemyBase[0] = GameObject.Find("NoxusBase1");
            enemyBase[1] = GameObject.Find("NoxusBase2");
            enemyBase[2] = GameObject.Find("NoxusBase3");
            enemyBase[3] = GameObject.Find("NoxusBase4");
        } else
        {
            reachedPosition = 0; ;
            enemyBase[0] = GameObject.Find("DemaciaBase1");
            enemyBase[1] = GameObject.Find("DemaciaBase2");
            enemyBase[2] = GameObject.Find("DemaciaBase3");
            enemyBase[3] = GameObject.Find("DemaciaBase4");
        }
        
        //Set the NavMesh Agents, and the Animations
        agent = GetComponent<NavMeshAgent>();
        agent.speed = hero.MovSpeed;
        anim = gameObject.GetComponent<Animator>();

        //Find all possible destination in the map
        for (int row =0; row < 5; row++)
        {
            for (int col = 0; col < 4; col++)
            {
                String name = "Destination" + (row + 1) + "-" + (col + 1);
                destinations[row,col] = GameObject.Find(name);
            }
        }
        chooseNextPath();
    }

    // Update is called once per frame
    void Update()
    {
        //Print the attackers of each unit
        UnityEngine.Debug.Log(gameObject.name + ": " + hero.getAttackersNumber());

        //Play Death animation
        if(hero.Hp <= 0)
        {
            anim.Play("death");

            //Delete my presence in other Units first, then be destroyed 
            for (int i = 0; i < detectedEnemies.Count; i++)
            {
                if (detectedEnemies[i] != null)
                {
                    //UnityEngine.Debug.Log(gameObject.name + "I am going to delete myself from enemies");
                    detectedEnemies[i].GetComponent<HeroControl>().detectedEnemies.Remove(gameObject);

                    //Maybe check if I am in the list, then remove myself
                    detectedEnemies[i].GetComponent<HeroControl>().hero.removeAttacker(gameObject);
                }
            }
            deathTimer.Start();
        }
        
        //Destroy gameobject after animation
        if (deathTimer.Elapsed >= TimeSpan.FromSeconds(2))
        {
            Destroy(gameObject);
        }
       
        //Check if unit reached the enemy base
        if (reachedEnemyBase && !movingToEnemy && !fighting)
        {
            UnityEngine.Debug.Log("I got inside, and can finally explode");
            AttackBase();
        }

        //If enemy has been found, we are going to fight
        if (enemyFound)
        {
            //If enemy we were fighting died, we disable our attack and find the next path
            //Kind of works, could be improved
            if (killedEnemy)
            {
                fighting = false;
                moving = false;
                agent.isStopped = false;
                agent.enabled = true;
                anim.enabled = true;
                killedEnemy = false;
                chooseNextPath();
            }

            if (gameObject.name.Equals("TrundleNoxus"))
            {
                UnityEngine.Debug.Log(gameObject.name + ": Detected Enemies:" + detectedEnemies.Count);
            }
            //If there is are no more enemies, we will resume the normal path of the character
            if (detectedEnemies.Count <= 0)
            {
                UnityEngine.Debug.Log(gameObject.name + ": I am going to keep walking");
                    resumeCharacter();
                    chooseNextPath();
            }
            //If unit is moving towards an enemy 
            if (movingToEnemy)
            {
                //If unit is close enough to the enemy, we will stop the unit, and start Fighting mode
                if (agent.isStopped || Vector3.Distance(gameObject.transform.position, objective.transform.position) < 8)
                {
                    stopCharacter();
                    Fight();
                }
            }

            //If we are in fighting mode, we will attack the enemy
            if(fighting == true)
            {
                Attack();
            }
        }
        
    }

    public void stopCharacter()
    {
        fighting = true;
        moving = false;
        movingToEnemy = false;
        agent.isStopped = true;
        anim.enabled = false;
    } //Stop movement to fight
    public void resumeCharacter()
    {
        fighting = false;
        enemyFound = false;
        moving = true;
        movingToEnemy = false;
        agent.enabled = true;
        agent.isStopped = false;
        anim.enabled = true;
    } //Resume normal moving stance
    public void Fight()
    {
        anim.enabled = true;
        anim.Play("attack1");
    } //Play attack animation 

    
    public void changePosition(int index1)
    {
        GameObject tmp = detectedEnemies[index1];
        
        //Eliminate from the list any enemy that is being attacked by too many units already
        for(int i =0; i < detectedEnemies.Count; i++)
        {
            if(detectedEnemies[0].GetComponent<HeroControl>().hero.getAttackersNumber() > 2)
            {
                //detectedEnemies.RemoveAt(i);
            }
        }
    }

    //Change objective based on whether an enemy is detected or not
    public void chooseNextPath()
    {
        //Check if we have found an enemy
        if(enemyFound)
        {
            //If we aren't fighting anyone yet, start fight with someone
            if (!fighting)
            {
                for (int i =0; i < detectedEnemies.Count; i++)
                {
                    if(detectedEnemies[i].GetComponent<HeroControl>().hero.getAttackersNumber() >= 2)
                    {
                        //CHECK!!! CHECK!!! CHECK!!! 
                        //Fix this if I want only 2 units to attack an enemy at a time
                        //changePosition(0);

                        //detectedEnemies.RemoveAt(i);
                        //chooseNextPath();
                        //return;
                    }
                    else
                    {
                        break;
                    }
                }

                //Also fix if we want only 2 attackers at a time
                if(detectedEnemies.Count > 0 && detectedEnemies[0].GetComponent<HeroControl>().hero.getAttackersNumber() < 3)
                {
                    //UnityEngine.Debug.Log(gameObject.name + ": I Am going to add myself to: " + detectedEnemies[0].gameObject.name);
                    detectedEnemies[0].GetComponent<HeroControl>().hero.addAttacker(gameObject); //Add myself as an attacker to the enemy
                    //UnityEngine.Debug.Log("I added one enemy. Total enemies are: " + detectedEnemies[0].GetComponent<HeroControl>().hero.getAttackersNumber());
                    objective = detectedEnemies[0];
                    activeEnemy = objective;
                    movingToEnemy = true;
                    agent.destination = objective.transform.position;
                    agent.stoppingDistance = 6;
                } else
                {
                    //UnityEngine.Debug.Log(gameObject.name + ": I Am going to resume now");
                    resumeCharacter();
                }
                
            }
        }
        //Else, find the next position to move towards
        else {
            if ((hero.Faction).Equals(UnitType.NOXUS) && !reachedEnemyBase)
            {
                if(reachedPosition == 4)
                {
                    moving = true;
                    System.Random rnd = new System.Random();
                    int x = rnd.Next(0, 4);
                    objective = enemyBase[x];
                    agent.destination = objective.transform.position;
                }
                else
                {
                    int[] tmpPos = new int[2];
                    tmpPos = randomDestination();
                    moving = true;
                    objective = destinations[tmpPos[0], tmpPos[1]];
                    agent.destination = objective.transform.position;
                }
            }
            else if(!reachedEnemyBase)
            {
                if(reachedPosition == 0)
                {
                    moving = true;
                    System.Random rnd = new System.Random();
                    int x = rnd.Next(0, 4);
                    objective = enemyBase[x];
                    agent.destination = objective.transform.position;
                }
                else
                {
                    int[] tmpPos = new int[2];
                    tmpPos = randomDestination();
                    moving = true;
                    objective = destinations[tmpPos[0], tmpPos[1]];
                    agent.destination = objective.transform.position;
                }
            }
        }
        if (moving || movingToEnemy)
        {
            anim.Play("walk");
        }
    }

    //Randomly choose the next objective # if enemy was not detected
    public int[] randomDestination()
    {
        if ((hero.Faction).Equals(UnitType.NOXUS))
        {
            if (reachedPosition < 5)
            {
                //Move from 0 to 5
                System.Random rnd = new System.Random();
                int[] pos = new int[2];
                pos[0] = reachedPosition;
                pos[1] = rnd.Next(0, 4);
                return pos;
            }
        }
        else
        {
            if(reachedPosition >= 0)
            {
                //Move from 5 to 0
                System.Random rnd = new System.Random();
                int[] pos = new int[2];
                pos[0] = reachedPosition;
                pos[1] = rnd.Next(0, 4); 
                return pos;
            }
        }
        return null;
    }
    public void changeEnemiesAmt(int x)
    {
        attackersAmt += x;
    }
    public int getEnemiesAmt()
    {
        return attackersAmt;
    }
    public void OnTriggerEnter(Collider other)
    {
        //When I detect the enemy base, I will explode and do some damage based on my own AD
        //In future releases, I will keep attacking the base until someone comes after me


        //If we find an enemy, we update the list, and update the objective of NavMesh
        if((gameObject.tag).Equals("Demacia") && (other.gameObject.tag).Equals("Noxus") ||
            (gameObject.tag).Equals("Noxus") && (other.gameObject.tag).Equals("Demacia"))
        {
            //Find amount of soldiers already attackin detected enemy, to check if we should attack as well
            HeroControl tmp = (other.transform.gameObject).GetComponent<HeroControl>();
            if (tmp.getEnemiesAmt() < 2)
            {
                enemyFound = true;
                bool addedHimAlready = false;

                for(int i = 0; i < detectedEnemies.Count; i++)
                {
                    if(detectedEnemies[i] == other.gameObject)
                    {
                        addedHimAlready = true;
                    }
                }

                if (!addedHimAlready)
                {
                    detectedEnemies.Add(other.gameObject);
                }
            }
            chooseNextPath();
        }
        //If we get to the position that we were walking to, find next Path to move to
        else if (objective != null && (other.gameObject.name).Equals(objective.name) && !movingToEnemy){
            if ((hero.Faction).Equals(UnitType.NOXUS)){
                reachedPosition++;
            } else
            {
                reachedPosition--;
            }
            chooseNextPath();
        } else if (objective == null)
        {
            chooseNextPath();
        }
    }
    public void OnTriggerExit(Collider other)
    {
        //If we find an enemy, we update the list, and update the objective of NavMesh
        if ((gameObject.tag).Equals("Demacia") && (other.gameObject.tag).Equals("Noxus") ||
            (gameObject.tag).Equals("Noxus") && (other.gameObject.tag).Equals("Demacia"))
        {
            detectedEnemies.Remove(other.gameObject);
            /*for(int i = 0; i < detectedEnemies.Count; i++)
            {
                if(detectedEnemies[i] == other.gameObject)
                {
                    detectedEnemies.RemoveAt(i);
                }
            }*/
        }
    }
   
    //Sometimes the players get inside the position they were walking to while fighting
    //So then they get stuck there forever, because they don't get OnTriggerEnter
    //Use OnTriggerStay to check those cases
    public void OnTriggerStay(Collider other)
    {
        if(!movingToEnemy && !fighting && objective != null && (other.gameObject.name).Equals(objective.name))
        {
            if ((hero.Faction).Equals(UnitType.NOXUS))
            {
                reachedPosition++;
            }
            else
            {
                reachedPosition--;
            }
            chooseNextPath();
        } else if(objective == null)
        {
            chooseNextPath();
        }

        //If we got to the enemy base, we are going to set that variable to true, so we can start attacking the base
        if(((hero.Faction).Equals(UnitType.NOXUS) && (other.tag).Equals("DemaciaBase")) ||
            ((hero.Faction).Equals(UnitType.DEMACIA) && (other.tag).Equals("NoxusBase"))) {
            reachedEnemyBase = true;
            UnityEngine.Debug.Log("I finally got to the base!!!");
        }
            
    }


    public void Attack()
    {
            
            if (timer.Elapsed >= TimeSpan.FromSeconds(3)){
                hasAttacked = false;
                timer.Stop();
            }

            //If hasn't attacked, it means we are ready to attack again
            if (hasAttacked == false) {
                hasAttacked = true;
            //Detect if the enemy is going to die after our attack, to be ready to move to the next one
                if (detectedEnemies.Count <= 0 || ((detectedEnemies[0] != null && (detectedEnemies[0].GetComponent<HeroControl>().hero.Hp - hero.Damage) <= 0 ))
                                               || (activeEnemy != null &&  detectedEnemies[0] != null && detectedEnemies[0] != activeEnemy))
                {
                    killedEnemy = true;
                    if (detectedEnemies[0] != null)
                    {
                        detectedEnemies[0].GetComponent<HeroControl>().hero.Hp = detectedEnemies[0].GetComponent<HeroControl>().hero.Hp - hero.Damage;
                        timer.Restart();
                        timer.Start();
                        UnityEngine.Debug.Log(gameObject.name + ": I am going to find new path");
                        chooseNextPath();
                        return;
                    }

            }
            if( detectedEnemies[0] != null)
            {
                detectedEnemies[0].GetComponent<HeroControl>().hero.Hp = detectedEnemies[0].GetComponent<HeroControl>().hero.Hp - hero.Damage;
            } else
            {
                resumeCharacter();
            }
            //UnityEngine.Debug.Log(detectedEnemies[0].GetComponent<HeroControl>().hero.Hp);
                timer.Restart();
                timer.Start();
            }   
    }
    public void AttackBase()
    {
        if (gameObject.tag.Equals("Demacia"))
        {
            Global.NoxusBaseHP -= hero.Damage * 5;
            UnityEngine.Debug.Log(Global.NoxusBaseHP);
            Destroy(gameObject);
        } else if (gameObject.tag.Equals("Noxus"))
        {
            Global.DemaciaBaseHP -= hero.Damage * 5;
            UnityEngine.Debug.Log(Global.DemaciaBaseHP);
            Destroy(gameObject);
        }
    }
    

}
