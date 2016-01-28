package org.vnsgbt.infoplus;

/**
 * Created by dev2 on 1/27/16.
 *
 */

public class Question1 {

    public static void main (String[] args) {

        LeastPossibleDrops optimalDrops;

        if (args.length > 0 && Integer.parseInt( args[0]) > 0) {
            optimalDrops = new LeastPossibleDrops(Integer.parseInt(args[0]));
        }
        else {
            System.out.println("\n Input not found or invalid : testing with all levels ... ");

            for (int i = 1; i <= 50; i++) {
                optimalDrops = new LeastPossibleDrops(i);
                int result = optimalDrops.getLeastPossibleDrops(optimalDrops.startTest);
                System.out.println("Optimal drops: " + result);
            }
            return;
        }

        int result = optimalDrops.getLeastPossibleDrops(optimalDrops.startTest);
        System.out.println("Optimal drops: " + result);
    }
}
