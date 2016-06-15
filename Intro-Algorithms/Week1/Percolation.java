package com.DarkRealm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Jayam on 3/9/2016.
 * Programming Assignment for Week 1
 */
public class Percolation {
    private Pair[][] connected;
    private int[][] grid;
    private int size;
    private int row,col;

    Percolation() {
        try {
            System.out.println("Enter the input file....");
            Scanner scan = new Scanner(System.in);
            String fileName = "percolation-testing/percolation/"+scan.nextLine();
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            int N = Integer.parseInt(line);

            size = N;
            row = size+2;
            col = size+1;
            connected = new Pair[row][col];
            grid = new int[row][col];
            for(int i=0;i<row;i++){
                for (int j = 0; j<col;j++){
                        grid[i][j] = 0;
                    connected[i][j]=new Pair(i,j);
                }
            }

            for (int i=0;i<col;i++){
                grid[0][i]=1;
                connected[0][i] = new Pair(0,0);
                connected[row-1][i] = new Pair(row-1, col-1);
                grid[row-1][i]=1;
            }
            readInput(br);

        } catch (IOException e) {
        }
    }

    private void readInput(BufferedReader br){
        try{
            String line;
            int p, q;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                p = Integer.parseInt(line.charAt(0) + "");
                q = Integer.parseInt(line.charAt(2) + "");
                open(p, q);
            }
        }catch (IOException e){}
    }

    private boolean inBounds(int i, int j) {
        return i >= 0 && i <=size && j >=0 && j <=size;
    }

    private void open(int p, int q) {
            grid[p][q]=1;
            //connect with adjacent open elements
            if(grid[p-1][q]==1)
                quickUnion(p,q,p-1,q);
            if(grid[p+1][q]==1)
                quickUnion(p,q,p+1,q);
            if(q>1 && grid[p][q-1]==1)
                quickUnion(p,q,p,q-1);
            if(q<size-1 && grid[p][q+1]==1)
                quickUnion(p,q,p,q+1);
    }

    private Pair root(int x, int y) {
      Pair pair  = new Pair(x,y);
        int  tx,ty;
        while (pair.i!=connected[pair.i][pair.j].i || pair.j!=connected[pair.i][pair.j].j){
            tx = connected[pair.i][pair.j].i;
            ty= connected[pair.i][pair.j].j;
            pair.i = tx;
            pair.j = ty;
        }
        return pair;
    }

    private void quickUnion(int p, int q, int r ,int s) {
        Pair pRoot = root(p, q);
        Pair qRoot = root(r, s);

        if (!pRoot.equals(qRoot)) {
            connected[qRoot.i][qRoot.j]  = new Pair(pRoot.i,pRoot.j);
        }
    }

    private boolean quickFind(int p, int q,int r ,int s) {
        return root(p,q).equals(root(r,s));
    }

    public boolean percolates() {
        return quickFind(0, 0, row - 1, col - 1);
    }

    public static void run(){
        Percolation perc = new Percolation();
        boolean done = perc.percolates();
        System.out.println("System percolates  -- "+done);
    }
}
