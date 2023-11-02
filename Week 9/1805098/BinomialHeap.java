package com.company;


public class BinomialHeap {
    public Node nodes; //oije list //sibling er track binoialHeapNode class e ache

    public BinomialHeap() {
        nodes = null;

    }

    public void merge(Node heap)
    {
        //non decreasing order e tree gula list hoye thaklo
        // but same level er 2 ta tree thakte pare ei ops er pore
        Node one=nodes, two=heap;

        while((one!=null) && (two!=null))
        {
            if (one.degree==two.degree)
            {
                //just pasapashi rekhe dao
                Node now=two; //now hocche ekta third party
                two=two.sibling;
                now.sibling=one.sibling;
                one.sibling=now;
                one=now.sibling;
                //Eita seshe amader majhe arrow create holo
                //and pore 2 ghor samne agalo one and two
            }

            else if (one.degree<two.degree)
            {
                if ((one.sibling==null) || (one.sibling.degree>two.degree))
                {
                    //one and one er sibling er majhe two ke dhukate hobe
                    Node temp=two;
                    two=two.sibling; //two te two er sibling gelo
                    temp.sibling=one.sibling; //ager two er sibling er jaygay ager one er sibling gelo
                    one.sibling=temp; //ager one er jaygay ager two gelo
                    one=temp.sibling; //ager one er sibling er sibling er value one e store holo

                    //so ultimately one gelo one er sibling e
                    //ar two gelo two er sibline e
                }
                else
                    one=one.sibling; //just one ke ek ghor agay dilam
            }

            else {
                //jokhon one er degree boro
                Node temp=one;
                one=two;
                two=two.sibling;
                one.sibling=temp;

                if (temp==nodes)
                {
                    nodes=one; //Head thik korlam
                    //jodi ager head ekhon ar head na thake
                }
            }
        }

        if (one==null)
        {
            //two null hoy nai
            //ekhono onek boro ekta list baki ache
            //tahole one er last e jabo
            //and link kore dibo
            one=nodes;

            while(one.sibling!=null)
                one=one.sibling;

            one.sibling=two;
        }
    }

    public void unionNodes(Node heap)
    {
        merge(heap);

        Node prev=null, now=nodes, next=nodes.sibling;

        while(next!=null)
        {
            //System.out.println("inside while loop of unionNodes");
            if ((now.degree!=next.degree) ||
                    ((next.sibling!=null)
                            &&(next.sibling.degree==now.degree)))
            {
                //degree soman na
                //or jokhon pashapashi 3 jon er degree same
                //3 ta one jog kora to sojai
                prev=now;
                now=next; //vejal kom

                next=now.sibling;
            }

            else if (now.value>=next.value)
            {
                now.sibling=next.sibling;
                next.parent=now; //boro tree baniye fellam
                next.sibling=now.child;
                now.child=next;
                now.degree++;

                next=now.sibling;

            }

            else {
                 if (prev==null)
                    nodes=next; //eikhan thekei suru korbo

                else
                    prev.sibling=next; //ashol tree ke kache niye ashlam

                now.parent=next; //tree boro korlam
                now.sibling=next.child;
                next.child=now;
                next.degree++;
                now=next;


                next=now.sibling;
            }



        }
    }

    // Method 3 that was asked in the question
    public void Insert(int value)
    {

        if (value != -1) {
            Node temp
                    = new Node(value);
            if (nodes == null) {
                nodes = temp;

            }
            else {
                unionNodes(temp);
            }



            System.out.println("Inserted "+value);
        }

    }


    // Method 4 that was asked in the question
    public void increaseKey(int old_value,
                            int new_value)
    {
        //maxHeap e increase key
        //minHeap e decrease ta common


        Node temp = nodes.findANodeWithKey(old_value);
        if (temp == null)
            return;
        temp.value = new_value;


        Node tempParent = temp.parent;

        while ((tempParent != null)
                && (temp.value > tempParent.value)) {
            int now = temp.value;
            temp.value = tempParent.value;
            tempParent.value = now;

            temp = tempParent;
            tempParent = tempParent.parent;
        }

        System.out.println("Increased "+old_value+". The updated value is "+new_value+".");
    }


    // Method 1 that was mentioned in the question
    public int FindMax()
    {
        return nodes.findMaxNode().value ;
    }


    // Method 2 that was asked in the question
    public int ExtractMax()
    {
        if (nodes==null)
            return -1; //-1 input dibo na

        Node now=nodes, prev=null;
        Node maxNode=nodes.findMaxNode();

        while(now.value!=maxNode.value)
        {
            prev=now;
            now= now.sibling;
        }

        if (prev==null)
        {
            //mane surur node tatei max peye gechilam
            nodes=now.sibling;
        }

        else
            prev.sibling=now.sibling; //karon ekhon now delete hobe


        now=now.child; //now is dead now :)
        //Rest in peace our previous maximum node


        Node anotherNode=now;

        while(now!=null)
        {
            now.parent=null; //eder order kintu ekdom suru tei thik hoyei ache
            now=now.sibling;
        }

        if ((nodes==null) && (anotherNode!=null))
        {
            nodes=anotherNode.reverse(null);
        }

        else if ((nodes!=null) && (anotherNode!=null))
        {
            unionNodes(anotherNode.reverse(null)); //anotherNOde mane new child gula reverse order e
            //reverse order korlam karon sorting hoye thakbe
            //mane merge er kaj ta kore rakhlam ar ki 
        }
        return maxNode.value;



    }

    public void Print()
    {
        Node r= nodes;
        while (r != null) {
            System.out.print("Binomial Tree, B"+r.degree);
            r.level=0;
            Print(r, -1);
            r=r.sibling;
            System.out.println();
        }

    }

    private Node par(Node r)
    {
        while (r.parent!=null)

        {
            //System.out.println("somossada ki??");
            r=r.parent;
        }
        //System.out.println("baire somossada ki??");
        return r;
    }

    private void Print(Node r, int c)
    {
        if (r != null) {
            if (c<r.level)

            {
                c++;
                System.out.println();
                System.out.print("Level "+c+": ");
            }
            System.out.print(r.value + " ");
            if (r.sibling!=null && par(r)==par(r.sibling)) //parent equal kina
            {
                r.sibling.level=r.level;
                Print(r.sibling, c);
            }
            if (r.child!=null)
                r.child.level=r.level+1;
            Print(r.child, c);
        }
    }

}
