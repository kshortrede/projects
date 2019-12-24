using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SoundEffects : MonoBehaviour
{
    public AudioClip[] footsteps;
    public AudioClip[] death;

    private AudioSource audioSource;

    // Start is called before the first frame update
    void Start()
    {
        audioSource = GetComponent<AudioSource>();
    }

    private void Step()
    {
        audioSource.PlayOneShot(footsteps[0]);
    }
    private void Death()
    {
        audioSource.PlayOneShot(death[0]);
    }
}
