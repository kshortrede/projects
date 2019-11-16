using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UIHover : MonoBehaviour
{
    public bool hoverOverActive;
    public string hoverName;
    public string hoverUnitClass;
    public float hoverUnitHealth;

    Ray ray; 
    RaycastHit hit2; 

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        HoverInformation();
    }

    public void HoverInformation()
    {
        if(Physics.Raycast (ray, out hit2, 10000))
        {
            if(hit2.transform.tag == "Demacia" || hit2.transform.tag == "Noxus")
            {
                hoverOverActive = true;
                hoverName = hit2.transform.gameObject.name;
                hoverUnitHealth = hit2.transform.GetComponent<HeroControl>().hero.Hp;
                hoverUnitClass = (hit2.transform.GetComponent<HeroControl>().tmpType).ToString();
            } else
            {
                hoverOverActive = false;
            }
        }
    }
    void OnGUI()
    {
        if (hoverOverActive)
        {
            //Can customize this to have different colors, or even different background in the back
            //https://docs.unity3d.com/ScriptReference/GUI.Label.html
            GUIStyle style = new GUIStyle();
            style.fontSize = 20;
            style.normal.textColor = Color.black;
            string textToHover = hoverName + "\n" + hoverUnitClass + "\nHealth: " + hoverUnitHealth;
            GUI.Label(new Rect(Input.mousePosition.x - 100, Screen.height - Input.mousePosition.y, 100, 20), textToHover, style);
        }    
    }
}
