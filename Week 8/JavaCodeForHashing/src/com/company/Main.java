package com.company;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Main {
    List<String> tenThousand;
    List<String> oneThousand;

    public Main()
    {
        tenThousand=new ArrayList<>(10000);
        oneThousand=new ArrayList<>(1000);
    }

    public static String stringGenerator()
    {
        int left = 97; // letter 'a'
        int right = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = left + (int)
                    (random.nextFloat() * (right - left + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static boolean isPrime(int n)
    {
        for(int i=2; i<=Math.sqrt(n); i++)
        {
            if (n%i==0)
                return false;
        }
        return true;
    }

    public static void  main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        Main main=new Main();

        System.out.println("Please insert a number for table size");
        int prime;

        int tableSize= scanner.nextInt();

        for(int i=tableSize; ; i=i+2)
        {
            if (i%2==0)
            {
                i--;
                continue;
                //1 baralam
            }
            if (isPrime(i))
            {
                prime=i;
                break;
            }
        }


        //generating 10000 unique words
        for(int i=0; i<10000; ) //10000 korte hobe
        {
            //System.out.println("inside 10000 loop");
            String now=main.stringGenerator();
            if (main.tenThousand.contains(now))
            {
                continue;
            }
            else
            {
                main.tenThousand.add(now);
                i++;
            }

        }

        for(int i=0; i<1000; i++) //1000 korte hobe
        {
            //System.out.println("inside five loop ");
            int index=(int)(Math.random()*1000); //10000 korte hobe
            //System.out.println(index);
            main.oneThousand.add(main.tenThousand.get(index));
        }

        /*
        //one thousand e sob string ache
        for(int i=0; i<5; i++)
        {
            System.out.println(main.oneThousand.get(i));
        }
        */

        chainHashTable myChainHashTable =new chainHashTable(prime);
        chainHashTable myChainHashTable2=new chainHashTable(prime);

        doubleHashTable mydoubleHashTable=new doubleHashTable(prime);
        doubleHashTable mydoubleHashTable2=new doubleHashTable(prime);

        doubleHashTable mycustomHashTable=new doubleHashTable(prime);
        doubleHashTable mycustomHashTable2=new doubleHashTable(prime);

        for(int i=0; i<10000; i++)
        {
            myChainHashTable.insert(main.tenThousand.get(i));
            myChainHashTable2.insert2(main.tenThousand.get(i));

            mydoubleHashTable.insert(main.tenThousand.get(i));
            mydoubleHashTable2.insert2(main.tenThousand.get(i));

            mycustomHashTable.customInsert(main.tenThousand.get(i));
            mycustomHashTable2.customInsert2(main.tenThousand.get(i));
        }

        System.out.println("Collision in separate chain in first hash "+myChainHashTable.collisions);
        System.out.println("Collision in separate chain in second hash "+myChainHashTable2.collisions);
        System.out.println("Collision in double hash in first hash "+mydoubleHashTable.collisions);
        System.out.println("Collision in double hash in second hash "+mydoubleHashTable2.collisions);
        System.out.println("Collision in custom hash in first hash "+ mycustomHashTable.collisions);
        System.out.println("Collision in custom hash in second hash "+ mycustomHashTable2.collisions);


        //myChainHashTable.printChain();
        //System.out.println("done one");
        //myChainHashTable2.printChain();

        int c1=0, c2=0, c3=0, c4=0, c5=0, c6=0;
        for(int i=0; i<1000; i++)
        {

            c1+=myChainHashTable.search(main.oneThousand.get(i));
            c2+=myChainHashTable2.search2(main.oneThousand.get(i));

            c3+=mydoubleHashTable.search(main.oneThousand.get(i));
            c4+=mydoubleHashTable2.search2(main.oneThousand.get(i));

            c5+=mycustomHashTable.customSearch(main.oneThousand.get(i));
            c6+=mycustomHashTable2.customSearch2(main.oneThousand.get(i));


        }

        System.out.println("Avg probes in chain with first hash function "+ (double)c1/1000.00);
        System.out.println("Avg probes in chain with second hash function "+ (double)c2/1000.00);

        System.out.println("Avg probes in double hash with first hash function "+ (double)c3/1000.00);
        System.out.println("Avg probes in double hash with second hash function "+ (double)c4/1000.00);

        System.out.println("Avg probes in custom hash with first hash function "+ (double)c5/1000.00);
        System.out.println("Avg probes in double hash with second hash function "+ (double)c6/1000.00);

        //System.out.println(prime);

    }
}
