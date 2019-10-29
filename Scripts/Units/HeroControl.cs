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
    NavMeshAgent agent;
    //Keep track of the position we are in
    private int reachedPosition = 0; 
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
        } else
        {
            reachedPosition = 0; ;
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
        //Play Death animation
        if(hero.Hp <= 0)
        {
            anim.Play("death");
            //anim.SetTrigger("death");
            deathTimer.Start();
        }
        //Destroy gameobject after animation
        if(deathTimer.Elapsed >= TimeSpan.FromSeconds(2))
        {
            //Delete my presence in other Units first, then be destroyed 
            for(int i = 0; i < detectedEnemies.Count; i++)
            {
                detectedEnemies[i].GetComponent<HeroControl>().detectedEnemies.RemoveAt(0);

                //Maybe check if I am in the list, then remove myself
                detectedEnemies[i].GetComponent<HeroControl>().hero.removeAttacker(gameObject);
            }
            Destroy(gameObject);
        }

        //If enemy has been found, we are going to fight
        if (enemyFound)
        {
            //If enemy we were fighting died
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
        
            if(detectedEnemies.Count <= 0)
            {
                    resumeCharacter();
                    chooseNextPath();
            }
            if (movingToEnemy)
            {
                if (agent.isStopped || Vector3.Distance(gameObject.transform.position, objective.transform.position) < 8)
                {
                    stopCharacter();
                    Fight();
                }
            }
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
    }
    public void resumeCharacter()
    {
        fighting = false;
        enemyFound = false;
        moving = true;
        movingToEnemy = false;
        agent.enabled = true;
        agent.isStopped = false;
        anim.enabled = true;
    }
    public void Fight()
    {
        anim.enabled = true;
        anim.Play("attack1");
    }

    
    public void changePosition(int index1)
    {
        GameObject tmp = detectedEnemies[index1];
        
        //Eliminate from the list any enemy that is being attacked by too many units already
        for(int i =0; i < detectedEnemies.Count; i++)
        {
            if(detectedEnemies[0].GetComponent<HeroControl>().hero.getAttackersNumber() > 2)
            {
                detectedEnemies.RemoveAt(i);
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
                    if(detectedEnemies.Count > 0 && detectedEnemies[0].GetComponent<HeroControl>().hero.getAttackersNumber() >= 2)
                    {
                        //Fix this if I want only 2 units to attack an enemy at a time

                        //changePosition(0);
                    } else
                    {
                        break;
                    }
                }

                if(detectedEnemies.Count > 0 )//&& detectedEnemies[0].GetComponent<HeroControl>().hero.getAttackersNumber() < 2)
                {
                    UnityEngine.Debug.Log("Let's fight!!!");
                    detectedEnemies[0].GetComponent<HeroControl>().hero.addAttacker(gameObject); //Add myself as an attacker to the enemy
                    UnityEngine.Debug.Log("I added one enemy. Total enemies are: " + detectedEnemies[0].GetComponent<HeroControl>().hero.getAttackersNumber());
                    objective = detectedEnemies[0];
                    activeEnemy = objective;
                    movingToEnemy = true;
                    agent.destination = objective.transform.position;
                    agent.stoppingDistance = 6;
                } else
                {
                    resumeCharacter();
                }
                
            }
        }
        //Else, find the next position to move towards
        else {
            int[] tmpPos = new int[2];
            tmpPos = randomDestination();
            moving = true;
            objective = destinations[tmpPos[0], tmpPos[1]];
            agent.destination = objective.transform.position;
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
        //If we find an enemy, we update the list, and update the objective of NavMesh
        if((gameObject.tag).Equals("Demacia") && (other.gameObject.tag).Equals("Noxus") ||
            (gameObject.tag).Equals("Noxus") && (other.gameObject.tag).Equals("Demacia"))
        {
            //Find amount of soldiers already attackin detected enemy, to check if we should attack as well
            HeroControl tmp = (other.transform.gameObject).GetComponent<HeroControl>();
            if (tmp.getEnemiesAmt() < 2)
            {
                enemyFound = true;
                detectedEnemies.Add(other.gameObject);
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
            for(int i = 0; i < detectedEnemies.Count; i++)
            {
                if(detectedEnemies[i] == other.gameObject)
                {
                    detectedEnemies.RemoveAt(i);
                }
            }
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
                if (detectedEnemies.Count <= 0 || detectedEnemies[0].GetComponent<HeroControl>().hero.Hp - hero.Damage <= 0 
                                               || (activeEnemy != null &&  detectedEnemies[0] != null && detectedEnemies[0] != activeEnemy))
                {
                    killedEnemy = true;
                }
                detectedEnemies[0].GetComponent<HeroControl>().hero.Hp = detectedEnemies[0].GetComponent<HeroControl>().hero.Hp - hero.Damage;
                //UnityEngine.Debug.Log(detectedEnemies[0].GetComponent<HeroControl>().hero.Hp);
                timer.Restart();
                timer.Start();
            }   
    }
    

}
