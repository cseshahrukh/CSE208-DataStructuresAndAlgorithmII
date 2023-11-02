package com.company;

public class doubleHashTable {

    private int size;
    private doubleNode[] table;
    int count =0; //eita oije string er emni value rakhar jonno
    int collisions=0;


    public doubleHashTable(int prime)
    {
        size = prime;
        table = new doubleNode[size];

        for (int i = 0; i < size; i++)
            table[i] = null;

    }

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

    public int auxHash ( int hashValue) {
        //return positive remainder only
        return size -hashValue% size;
    }




    //done
    public int search(String key)
    {
        int c=1;
        int u = hashCode1(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+i*v) % size;
            now=(now+ size)% size;
            if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                return c;
            }
            c++;
        }
        return c;
    }


    //done
    public void insert(String key)
    {


        int u = hashCode1(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+i*v) % size;
            now=(now+ size)% size;
            if (table[now]==null)
            {
                //visited[now]=true;
                table[now]=new doubleNode(key, count++);
                return;
            }
            collisions++;
        }
    }


    //done
    public void remove(String key)
    {
        int u = hashCode1(key, size); //u is hash(k)
        int v = auxHash(u);
        int now=u, i;
        for(i=0; i< size; i++)
        {
            now=(u+i*v)% size;
            now=(now+ size)% size;
            if( table[now]!=null && table[now].key.compareTo(key)!=0 )
                continue;
            else if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                table[now]=null;
                return;
            }
        }


        //table[now]=null;

    }


    //done
    public int search2(String key)
    {
        int c=1;
        int u = hashCode2(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+i*v) % size;
            now=(now+ size)% size;
            if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                return c;
            }
            c++;
        }
        return c;
    }


    //done
    public void insert2(String key)
    {


        int u = hashCode2(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+i*v) % size;
            now=(now+ size)% size;
            if (table[now]==null)
            {
                //visited[now]=true;
                table[now]=new doubleNode(key, count++);
                return;
            }
            collisions++;
        }
    }


    //done
    public void remove2(String key)
    {
        int u = hashCode2(key, size); //u is hash(k)
        int v = auxHash(u);
        int now=u, i;
        for(i=0; i< size; i++)
        {
            now=(u+i*v)% size;
            now=(now+ size)% size;
            if( table[now]!=null && table[now].key.compareTo(key)!=0 )
                continue;
            else if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                table[now]=null;
                return;
            }
        }


        //table[now]=null;

    }


    //done
    public int customSearch(String key)
    {
        int c1=2, c2=3; //arbitrary
        int c=1;
        int u = hashCode1(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+c1*i*v+c2*i*i) % size;
            now=(now+ size)% size;
            if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                return c;
            }
            c++;
        }
        return c;
    }


    //done
    public void customInsert(String key)
    {
        int c1=2, c2=3;

        int u = hashCode1(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+c1*i*v+c2*i*i) % size;
            now=(now+ size)% size;
            if (table[now]==null)
            {
                //visited[now]=true;
                table[now]=new doubleNode(key, count++);
                return;
            }
            collisions++;
        }
    }


    //done
    public void customRemove(String key)
    {
        int c1=2, c2=3;
        int u = hashCode1(key, size); //u is hash(k)
        int v = auxHash(u);
        int now=u, i;
        for(i=0; i< size; i++)
        {
            now=(u+c1*i*v+c2*i*i)% size;
            now=(now+ size)% size;
            if( table[now]!=null && table[now].key.compareTo(key)!=0 )
                continue;
            else if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                table[now]=null;
                return;
            }
        }


        //table[now]=null;

    }

    //done
    public int customSearch2(String key)
    {
        int c1=2, c2=3; //arbitrary
        int c=1;
        int u = hashCode2(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+c1*i*v+c2*i*i) % size;
            now=(now+ size)% size;
            if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                return c;
            }
            c++;
        }
        return c;
    }


    //done
    public void customInsert2(String key)
    {
        int c1=2, c2=3;

        int u = hashCode2(key, size); //u is hash(k)
        int v = auxHash(u);

        for(int i = 0; i< size; i++)
        {
            int now= (u+c1*i*v+c2*i*i) % size;
            now=(now+ size)% size;
            now=(now+ size)% size;
            if (table[now]==null)
            {
                //visited[now]=true;
                table[now]=new doubleNode(key, count++);
                return;
            }
            collisions++;
        }
    }


    //done
    public void customRemove2(String key)
    {
        int c1=2, c2=3;
        int u = hashCode2(key, size); //u is hash(k)
        int v = auxHash(u);
        int now=u, i;
        for(i=0; i< size; i++)
        {
            now=(u+c1*i*v+c2*i*i)% size;
            now=(now+ size)% size;
            if( table[now]!=null && table[now].key.compareTo(key)!=0 )
                continue;
            else if (table[now]!=null && table[now].key.compareTo(key)==0)
            {
                table[now]=null;
                return;
            }
        }
    }


    public void printHashTable()
    {
        System.out.println("\nPrinting my Hash Table");
        for (int i = 0; i < size; i++)
            if (table[i] != null)
                System.out.println(table[i].key + " " + table[i].value);
    }
}
