package com.DarkRealm;

import org.apache.commons.lang3.time.StopWatch;

/**
 * Created by Jayam on 3/6/2016.
 */
public class WeightedQuickUnionPathCompression {
    int size;
    int[] connectedComponents;
    int[] weightOfTree;

    WeightedQuickUnionPathCompression(int N) {
        size = N;
        connectedComponents = new int[size];
        weightOfTree = new int[size];
        for (int i = 0; i < size; i++) {
            connectedComponents[i] = i;
            weightOfTree[i] = 1;
        }
    }

    private void currentForest() {
        for (int i = 0; i < size; i++) {
            System.out.print(" " + connectedComponents[i] + " |");
        }
        System.out.println("");
        currentWeight();
    }

    private void currentWeight() {
        for (int i = 0; i < size; i++) {
            System.out.print(" " + weightOfTree[i] + " |");
        }
        System.out.println("");
    }

    private int pathCompresssionRoot(int x) {
        while (x != connectedComponents[x]) {
            connectedComponents[x] = connectedComponents[connectedComponents[x]]; //this line replace parent with grandparent
            x = connectedComponents[x];
        }
        return x;
    }

    private boolean inBounds(int p, int q) {
        return p < size && q < size;
    }

    public boolean connected(int p, int q) {
        return pathCompresssionRoot(p) == pathCompresssionRoot(q);
    }

    public void unionUsingCompression(int p, int q) {
        int pRoot = pathCompresssionRoot(p);
        int qRoot = pathCompresssionRoot(q);
        if (pRoot == qRoot) return;

        int pWeight = weightOfTree[pRoot];
        int qWeight = weightOfTree[qRoot];

        if (qWeight < pWeight) {
            connectedComponents[qRoot] = pRoot;
            weightOfTree[pRoot] += weightOfTree[qRoot];
        } else {
            connectedComponents[pRoot] = qRoot;
            weightOfTree[qRoot] += weightOfTree[pRoot];
        }
    }

    public boolean connectedCompression(int p, int q) {
        return pathCompresssionRoot(p) == pathCompresssionRoot(q);
    }

    public static void run() {
        WeightedQuickUnionPathCompression wqu = new WeightedQuickUnionPathCompression(7);
        wqu.currentForest();

        wqu.unionUsingCompression(4, 5);
        wqu.unionUsingCompression(0, 2);
        wqu.currentForest();
        wqu.unionUsingCompression(2, 4);
        wqu.currentForest();
        wqu.unionUsingCompression(1, 3);
        wqu.currentForest();
        wqu.unionUsingCompression(6, 5);
        wqu.currentForest();
        System.out.println("2 - 3 connceted --- " + wqu.connected(2, 3));
        System.out.println("1 - 5 connceted --- " + wqu.connected(1, 5));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("4 - 2 connceted --- " + wqu.connected(2, 4));
        stopWatch.stop();
        System.out.println("time - "+stopWatch.getTime());
    }
}