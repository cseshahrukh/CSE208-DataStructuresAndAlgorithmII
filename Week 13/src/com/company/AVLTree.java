package com.company;

public class AVLTree {

    public Node root;

    public int height(Node u) {
        if (u == null)
            return 0;
        else
            return u.height;
    }



    public void find(int num)
    {
        Node node=root;
        while(node!=null)
        {
            if(node.value ==num)
            {
                System.out.println("True");
                return;
            }
            else if (node.value <num)
            {
                node=node.right;
            }
            else
                node=node.left;

        }
        System.out.println("False");
    }

    // Get balance factor of a node
    public int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }


    public Node leftRotate(Node node) {
        // x->node
        // y -> rightChild
        // T2 -> rightChildLeftChild
        Node rightChild = node.right;
        Node rightChildLeftChild = rightChild.left;

        rightChild.left = node;
        node.right = rightChildLeftChild;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rightChild.height = Math.max(height(rightChild.left), height(rightChild.right)) + 1;
        return rightChild;
    }


    public Node rightRotate(Node node) {
        //y -> node
                //x -> leftChild
                        //T2 -> leftChildRightChild
        Node leftChild = node.left;
        Node leftChildRightChild = leftChild.right;

        leftChild.right = node;
        node.left = leftChildRightChild;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        leftChild.height = Math.max(height(leftChild.left), height(leftChild.right)) + 1;
        return leftChild;
    }






    public Node insert(Node node, int value) {

        // Find the position and insert the node
        if (node == null)
            return (new Node(value));
        if (value < node.value)
            node.left = insert(node.left, value);

        //allowing duplicates and same hole ami right side e move korbo bole thik korechi
        else if (value >= node.value)
            node.right = insert(node.right, value);

        //duplicate allow jodi na korte chai then return node dileyi hobe


        //dane ba bame insert hobar por ei case dekhbo
        // Sob node er balance factor dekhbo
        // bottom up approach e eigula handle korbo
        // Tree lagle update kore dibo
        node.height = 1 + Math.max(height(node.right), height(node.left));
        int balanceFactor = getBalanceFactor(node);
        //balance factor is left minus right
        if (balanceFactor > 1) {
            System.out.print("Height invariant violated.\n" +
                    "After rebalancing:");
            if (value < node.left.value) {
                //zig zig
                return rightRotate(node);
            } else if (value > node.left.value) {
                //zig-zag
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        else if (balanceFactor < -1) {
            System.out.print("Height invariant violated.\n" +
                    "After rebalancing: ");
            if (value > node.right.value) {
                //zig-zig
                return leftRotate(node);
            } else if (value < node.right.value) {
                //zig-zag
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    Node nodeWithMinimumValue(Node node) {
        //ekdom bame
        //delete er somoy kaje dibe jodi left child or right child null na hoy :(
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a node
    Node delete(Node root, int item) {

        // Find the node to be deleted and remove it
        if (root == null)
            return null;
        if (item < root.value)
            root.left = delete(root.left, item);
        else if (item > root.value)
            root.right = delete(root.right, item);

        //ei node dlt korte hobe
        else {
            if ((root.left == null) || (root.right == null))
            {
                Node node = null;
                if (root.left==null)
                    node = root.right; //daner tree ke dhore eikhane boshay dibo
                else
                    node = root.left;

                //jodi 2 jon e null hoy tahole to aro valo
                root = node;
            }
            else {
                Node node = nodeWithMinimumValue(root.right);
                root.value = node.value;
                root.right = delete(root.right, node.value);
            }
        }
        if (root == null)
            return null;

        // Update the balance factor of each node and balance the tree
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            System.out.print("Height invariant violated.\n" +
                    "After rebalancing: ");
            if (getBalanceFactor(root.left) >= 0) {
                //zig-zig
                return rightRotate(root);
            } else {
                //zig-zag
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            System.out.print("Height invariant violated.\n" +
                    "After rebalancing: ");
            if (getBalanceFactor(root.right) <= 0) {
                //zig zig
                return leftRotate(root);
            } else {
                //zig-zag
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }


    // Print the tree
    public void printTree(Node currPtr) {
        if (currPtr != null) {

            System.out.print(currPtr.value);

            if (currPtr.left!=null || currPtr.right!=null){
                System.out.print("(");
                printTree(currPtr.left);
                System.out.print(")");
            }
            if (currPtr.right!=null || currPtr.left!=null )
            {
                System.out.print("(");
                printTree(currPtr.right);
                System.out.print(")");
            }


        }
    }
}
