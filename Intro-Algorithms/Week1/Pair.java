package com.DarkRealm;

/**
 * Created by Jayam on 3/13/2016.
 */
public class Pair {
    int i;
    int j;
    Pair(int row, int col){
        i=row;
        j=col;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pair){
            Pair temp = (Pair)obj;
            return temp.i ==i && temp.j ==j;
        }
        return false;
    }
}
