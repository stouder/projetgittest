package com.example.demo;



class ArbreBinaire2 {
    Node root;

    public ArbreBinaire2(Node node) {
       this.root=node;
    }

    public Node rechercheIterative(int v) {
        Node current = this.root;

        while (current != null) {
            if (current.value == v) {
                return current;
            }
            
            current = current.value > v ? current.right:current.left;
        }

        return current;
    }


    public static void main(String[] args) {
       
    	 Node rootNode = new Node(2);
         rootNode.left = new Node(1);
         rootNode.right = new Node(3);
         rootNode.left.left = new Node(4);
         rootNode.left.right = new Node(5);

         ArbreBinaire2 arbre = new ArbreBinaire2(rootNode);

        int valeurRecherchee = 5;
        Node noeud = arbre.rechercheIterative(valeurRecherchee);

        System.out.println("La valeur " + valeurRecherchee + " est présente dans l'arbre : " + noeud.value);
    }
}

