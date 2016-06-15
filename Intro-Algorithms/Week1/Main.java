package com.DarkRealm;

import org.apache.commons.lang3.time.StopWatch;

public class Main {

    public static void main(String[] args) {
//        QuickFind.run();
//        QuickUnion.run();
//        WeightedQuickUnion.run();
//        WeightedQuickUnionPathCompression.run();
//        addTime();

        Percolation.run();
    }

    private static void addTime(){
        double a=0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        a = Math.atan2(10224,151351);
        stopWatch.stop();
        System.out.println("time - "+stopWatch.getTime());
    }
}
