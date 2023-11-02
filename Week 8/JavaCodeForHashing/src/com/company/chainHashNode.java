package com.company;

class chainHashNode {
    String key;
    int value; //prothome je input hobe se 1 , porer jon er value 2 eivabe
    //hash er value function diye ber korbo

    // Reference to next node
    chainHashNode next;

    // Constructor
    public chainHashNode(String key, int value)
    {
        this.key = key;
        this.value = value;
    }
}