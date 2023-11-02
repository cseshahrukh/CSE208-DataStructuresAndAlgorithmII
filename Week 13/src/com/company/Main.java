package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        AVLTree mytree=new AVLTree();


        File file = new File(
                "F:\\Level 2 Term 2\\CSE 208\\Week 13\\input.txt");

        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;

        while ((st = br.readLine()) != null)
        {
            //System.out.println(st); // st te ek line ek line kore thakche
            String[] words = st.split(" ");
            String s; int value;
            s=words[0];
            value=Integer.parseInt(words[1]);
                //System.out.println("S is "+ s);
                if (s.equals("I"))
                {
                    mytree.root = mytree.insert(mytree.root, value);
                    mytree.printTree(mytree.root);
                    System.out.println();
                }
                else if (s.equals("D"))
                {
                    mytree.root = mytree.delete(mytree.root, value);
                    mytree.printTree(mytree.root);
                    System.out.println();
                }
                else if (s.equals("F"))
                {
                    int num=Integer.parseInt(words[1]);
                    mytree.find(num);
                }
        }
    }
}
