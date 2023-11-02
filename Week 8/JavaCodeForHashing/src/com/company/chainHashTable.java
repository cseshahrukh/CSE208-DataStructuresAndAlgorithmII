package com.company;



// Class to represent entire hash table
class chainHashTable {
    int count=0;
    int collisions=0;
    public chainHashNode[] myArray;

    // Current capacity of array list
    public int size; //actually size ta


    public chainHashTable(int prime)
    {
        myArray = new chainHashNode[prime];
        size = prime;
        for (int i = 0; i < size; i++)
            myArray[i]=null;
    }

    //public int size() { return size; }


    public int hashCode1 (String str, int m) {
        int len = 7;
        int power = 1;
        int hashValue = 0;
        for (int i = 0; i < str.length(); i++)
        {
            hashValue= (hashValue+ (str.charAt(i) - 'a' + 1) * power)% m;
            power=(power * len) % m;
        }
        return (hashValue%m + m) % m;
    }

    public int hashCode2 (String str, int m) {
        int len = 7;
        int power = 1;
        int hashValue = 0;
        for (int i = str.length()-1; i >=0; i--)
        {
            //int temp=str.[i]-'a' ;
            hashValue= (hashValue+ (str.charAt(i) - 'a' + 1) * power)% m;
            power=(power * len) % m;
        }
        return (hashValue%m + m) % m;
    }



    // Method to remove a given key
    public void remove(String key)
    {
        //index ber korbo
        int index = hashCode1(key, size)% size;

        // Get head of chain
        chainHashNode head = myArray[index];

        //Chain e khujbo
        chainHashNode prev = null;
        while (head != null) {
            if (head.key.compareTo(key)==0)
                break;

            prev = head;
            head = head.next;
        }

        //  key not here
        if (head == null)
            return ;

        // Remove key
        if (prev != null)
            prev.next = head.next;
        else //mane head e remove korte hobe !!!!!!
            myArray[index]= head.next;
    }

    // Returns value for a key
    public int search(String key)
    {
        int c=1;
        //jodi string thake tahole tar value ta return korbo
        // Find head of chain for given key
        int index = hashCode1(key, size);

        chainHashNode head = myArray[index];

        // Search key in chain
        while (head != null) {
            if (head.key.compareTo(key)==0)
                return c;
            head = head.next;
            c++;
        }

        // key not found //jodio offline e applicable na
        return -1;
    }


    public void insert(String key)
    {

        int index = hashCode1(key, size);
        if (myArray[index]!=null)
            collisions++;
        chainHashNode newNode = new chainHashNode(key, ++count);
        newNode.next = myArray[index];
        myArray[index]= newNode;
    }

    // Method to remove a given key
    public void remove2(String key)
    {
        //index ber korbo
        int index = hashCode2(key, size)% size;
        chainHashNode head = myArray[index];

        chainHashNode prev = null;
        while (head != null) {
            // Key found
            if (head.key.compareTo(key)==0)
                break;
            prev = head;
            head = head.next;
        }

        if (head == null)
            return ;

        //remove
        if (prev != null)
            prev.next = head.next;
        else //mane head e remove korte hobe !!!!!!
            myArray[index]= head.next;


    }

    // Returns value for a key
    public int search2(String key)
    {
        int c=1; //for probe
        int index = hashCode2(key, size);

        chainHashNode head = myArray[index];
        while (head != null) {
            if (head.key.compareTo(key)==0)
                return c;
            head = head.next;
            c++;
        }

        return -1;
    }


    public void insert2(String key)
    {

        int index = hashCode2(key, size);

        if (myArray[index]!=null)
            collisions++;
        chainHashNode newNode
                = new chainHashNode(key, ++count);
        newNode.next = myArray[index];
        myArray[index]= newNode;
    }


    public void printChain()
    {
        for (int i = 0; i < size; i++)
        {
            if (myArray[i] != null) {
                chainHashNode head = myArray[i];
                // Search key in chain
                while (head != null) {
                    System.out.print(head.key + ' '+head.value);

                    head = head.next;
                }
            }
        System.out.println();
        }
    }
}