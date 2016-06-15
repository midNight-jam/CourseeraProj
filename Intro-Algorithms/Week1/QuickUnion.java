package com.DarkRealm;

/**
 * Created by Jayam on 3/6/2016.
 * Basic idea is to store the parent of the current object. So when we connect tow objects we just update
 * the root of obj that is getting connected, to the root of the obj to which it is getting connected
 * i.e connect the roots of two forest(by making one a child), when connecting two connected components
 */
public class QuickUnion {

    int size;
    int[] connectedComponents;

    QuickUnion(int N) {
        size = N;
        connectedComponents = new int[size];
        for (int i = 0; i < size; i++) {
            connectedComponents[i] = i;
        }
    }

    private boolean inBounds(int p, int q) {
        return p < size && q < size;
    }

    private void currentForest() {
        for (int i = 0; i < size; i++) {
            System.out.print(" " + connectedComponents[i] + " |");
        }
        System.out.println("");
    }

    private int root(int x) {
        while (x != connectedComponents[x]) {
            x = connectedComponents[x];
        }
        return x;
    }

    public void union(int p, int q) {
        if (inBounds(p, q)) {
            int pRoot = root(p);
            int qRoot = root(q);
            connectedComponents[qRoot] = pRoot;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void run() {
        QuickUnion qu = new QuickUnion(7);
        qu.currentForest();

        qu.union(4, 5);
        qu.union(0, 2);
        qu.currentForest();
        qu.union(2, 4);
        qu.union(1, 3);
        qu.currentForest();
        qu.union(6, 5);
        qu.currentForest();
        System.out.println("2 - 3 connceted --- " + qu.connected(2, 3));
        System.out.println("1 - 5 connceted --- " + qu.connected(1, 5));
        System.out.println("4 - 2 connceted --- " + qu.connected(2, 4));
    }
}
