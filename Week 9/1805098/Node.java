package com.company;

public class Node {
    int level, value, degree;
    Node parent, sibling, child;

    public Node(int value)
    {
        parent=null;
        sibling=null;
        child=null;
        this.value=value;
        degree=0;
    }

    public Node findMaxNode()
    {
        Node x, y;
        x=y=this;
        int max=x.value;

        //y te answer mane maximum value er node ta rakhbo
        while(x!=null)
        {
            if (max<x.value)
            {
                y=x; //maximum er node ta save kore rakhlam
                max=x.value;
            }
            x=x.sibling; //niche to boro number pabo na
            //only ei node ei boro value ta pabo
        }

        return y;
    }

    public Node findANodeWithKey(int value)
    {

        Node temp = this, node = null;

        while (temp != null) {
            if (temp.value == value) {
                node = temp;
                break;
            }

            if (temp.child == null)
                temp = temp.sibling;

            else {
                node = temp.child.findANodeWithKey(value);
                if (node == null)
                    temp = temp.sibling;
                else
                    break;
            }
        }

        return node;
    }

    public Node reverse(Node u)
    {
        //extract hole ei jinis ta kaje lagabo
        Node res;
        if (sibling != null)
            res = sibling.reverse(this); //left  e jara ache oder reverse kore dao
        else
            res = this; //left e keu nai
        sibling = u;
        return res;
    }

}
