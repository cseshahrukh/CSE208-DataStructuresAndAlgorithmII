package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here

        BinomialHeap binHeap = new BinomialHeap();


        File file = new File(
                "F:\\Level 2 Term 2\\CSE 208\\Week 9\\BinomialHeap\\text.txt");

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
        {
            //System.out.println(st); // st te ek line ek line kore thakche
            String[] words = st.split(" ");
            for(int i=0; i<words.length; i++)
            {
                String s=words[i];
                //System.out.println("S is "+ s);
                if (s.equals("INS"))
                {
                    //System.out.println("Inside INS");
                    i++;
                    int num=Integer.parseInt(words[i]);
                    //System.out.println("NUM is "+ num);
                    binHeap.Insert(num);
                }
                else if (s.equals("INC"))
                {
                    i++;
                    int num1=Integer.parseInt(words[i]);
                    i++;
                    int num2=Integer.parseInt(words[i]);
                    binHeap.increaseKey(num1, num2);
                }
                else if (s.equals("FIN"))
                {
                    System.out.println("Find Max Returned "+binHeap.FindMax());
                }
                else if (s.equals("EXT"))
                {
                    System.out.println("Extract Max Returned "+binHeap.ExtractMax());
                }
                else if (s.equals("PRI"))
                {
                    //binHeap.displayHeap();
                    System.out.println("Printing Binomial Heap...");
                    System.out.println("—-------------------------");



                    binHeap.Print();
                    System.out.println("—-------------------------");
                }
            }

        }
    }
}
