package org.vnsgbt.infoplus;

/**
 * Created by dev on 1/22/16.
 *
 */

public class Question1 {

    private int breakingPoint;
    public TestResult startTest;

    public Question1(int breakPoint) {
        startTest = new TestResult(12,1,0);
        breakingPoint = breakPoint;

        System.out.println("\nBreaking at " + breakingPoint + " : \n");
    }

    public int getBreakingPoint() {
        return breakingPoint;
    }

    private int totalDropsWithRemainingFloors (TestResult test) {
        return 50 - test.droppingFloor + test.numberOfDropped + 1;
    }

    private void dropLog (String msg, TestResult dropState) {
        System.out.println(msg + "\n" + dropState.toString());
    }

    public int getLeastPossibleDrops(TestResult start) {

        int numberOfDrops = tryFromNextSection(start);

        if (numberOfDrops > 1) {

            /** We have found optimal drops **/

            return start.numberOfDropped + numberOfDrops;
        }
        else {

            /** Move on to next section, the new factor is one less
             *  than previous since we lost one drop for it  **/

            int newFactor = start.droppingFloor - start.lowerDroppingFloor;

            if (totalDropsWithRemainingFloors(start) <= 12) {

                /** We have reached the last section to try
                 *  the remaining floors are the possible drops **/

                dropLog("Found breakingPoint from remaining floors: ",start);

                return start.numberOfDropped + numberOfDrops
                        + (breakingPoint - start.droppingFloor + lookNoFurther(start));
            }

            int nextFloorToDrop = start.droppingFloor + newFactor;
            int nextLowerFloor = start.droppingFloor + 1;

            TestResult nextSection = new TestResult(nextFloorToDrop,nextLowerFloor,numberOfDrops + start.numberOfDropped);

            return getLeastPossibleDrops(nextSection);
        }
    }

    private int lookNoFurther (TestResult test) {

        /** If breakingPoint is at droppingFloor or top level 50
         *  There's no need to test drop anymore **/

        return breakingPoint == test.droppingFloor || breakingPoint == 50 ? 0 : 1;
    }

    private int tryFromNextSection(TestResult start) {

        /** It takes at least one drop to find out **/
        int numberOfDrops = 1;

        if (start.droppingFloor >= breakingPoint) {

            /** The difference between breakingPoint and beginning of this section
             *  plus 1 is the number of drops to see it breaks **/

            numberOfDrops += breakingPoint - start.lowerDroppingFloor + lookNoFurther(start);

            dropLog("Found breakingPoint from lowerFloor: ",start);
        }

        return numberOfDrops;
    }

    class TestResult {
        public int droppingFloor;
        public int lowerDroppingFloor;
        public int numberOfDropped;

        public TestResult(int droppingFloor, int lowerDroppingFloor,int numberOfDropped){
            this.droppingFloor = droppingFloor;
            this.lowerDroppingFloor = lowerDroppingFloor;
            this.numberOfDropped = numberOfDropped;
        }

        public String toString () {
            return    "\n droppingFloor:      " + Integer.toString(droppingFloor)
                    + "\n lowerDroppingFloor: " + Integer.toString(lowerDroppingFloor)
                    + "\n numberOfDropped:    " + Integer.toString(numberOfDropped)
                    + "\n";
        }
    }

    public static void main (String[] args) {

        Question1 optimalDrops;

        if (args.length > 0 && Integer.getInteger( args[0]) != null) {
            optimalDrops = new Question1(Integer.getInteger(args[0]));
        }
        else {
            for (int i = 1; i <= 50; i++) {
                optimalDrops = new Question1(i);
                int result = optimalDrops.getLeastPossibleDrops(optimalDrops.startTest);
                System.out.println("Optimal drops: " + result);
            }
            return;
        }

        int result = optimalDrops.getLeastPossibleDrops(optimalDrops.startTest);
        System.out.println("Optimal drops: " + result);
    }
}
