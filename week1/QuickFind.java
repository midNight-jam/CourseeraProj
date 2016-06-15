package com.DarkRealm;

/**
 * Created by Jayam on 3/6/2016.
 */
public class QuickFind {

    int size;
    int[] connectedComponents;

    QuickFind(int N) {
        size = N;
        connectedComponents = new int[size];
        for (int i = 0; i < size; i++) {
            connectedComponents[i] = i;
        }
    }

    //insidiiuos Buggy Junk Code
    public void unionZZZZ(int p, int q) {
        if (inBounds(p, q) && connectedComponents[p] != connectedComponents[q]) {
            int temp = -1;
            if (connectedComponents[p] != p) {
                temp = connectedComponents[q];
                connectedComponents[q] = connectedComponents[p];
            } else {
                temp = connectedComponents[p];
                connectedComponents[p] = connectedComponents[q];
            }
            for (int i = 0; i < size; i++) {    //update all
                if (connectedComponents[i] == temp) {
                    connectedComponents[i] = connectedComponents[p];
                }
            }
        }
    }

    public void union(int p, int q){
        int pid = connectedComponents[p];
        int qid = connectedComponents[q];
        for(int i=0;i<size;i++)
            if(connectedComponents[i]==qid)
                connectedComponents[i]=pid;
    }

    public boolean connected(int p, int q) {
        if (inBounds(p, q)) {
            return connectedComponents[p] == connectedComponents[q];
        }
        return false;
    }

    private boolean inBounds(int p, int q) {
        return p < size && q < size;
    }

    private void currentAdjacency(){
        for (int i = 0; i < size; i++) {//update all
         System.out.print(" "+connectedComponents[i]+" |");
        }
        System.out.println("");
    }

    public static void run(){
        QuickFind qf = new QuickFind(7);
        qf.union(4,5);
        qf.union(0,2);
        qf.currentAdjacency();
        qf.union(2,4);
        qf.union(1,3);
        qf.currentAdjacency();
        qf.union(6,5);
        qf.currentAdjacency();

    }
}
