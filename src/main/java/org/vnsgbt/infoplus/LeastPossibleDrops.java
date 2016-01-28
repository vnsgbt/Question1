package org.vnsgbt.infoplus;

/**
 * Created by dev on 1/22/16.
 *
 */

public class LeastPossibleDrops {

    private int breakingPoint;
    public TestResult startTest;

    public LeastPossibleDrops(int breakPoint) {
        startTest = new TestResult(12,1,0);
        breakingPoint = breakPoint;

        System.out.println("\nBreaking at " + breakingPoint + " : \n");
    }

    public int getBreakingPoint() {
        return breakingPoint;
    }

    private int totalDropsWithRemainingFloors (TestResult test) {
        return 50 - test.getDroppingFloor() + test.getNumberOfDropped() + 1;
    }

    private void dropLog (String msg, TestResult dropState) {
        System.out.println(msg + "\n" + dropState.toString());
    }

    public int getLeastPossibleDrops(TestResult start) {

        int numberOfDrops = tryFromNextSection(start);

        if (numberOfDrops > 1) {

            /** We have found optimal drops **/

            return start.getNumberOfDropped() + numberOfDrops;
        }
        else {

            /** Move on to next section, the new factor is one less
             *  than previous since we lost one drop for it  **/

            int newFactor = start.getDroppingFloor() - start.getLowerDroppingFloor();

            if (totalDropsWithRemainingFloors(start) <= 12) {

                /** We have reached the last section to try
                 *  the remaining floors are the possible drops **/

                dropLog("Found breakingPoint from remaining floors: ",start);

                return start.getNumberOfDropped() + numberOfDrops
                        + (breakingPoint - start.getDroppingFloor() + lookNoFurther(start));
            }

            int nextFloorToDrop = start.getDroppingFloor() + newFactor;
            int nextLowerFloor = start.getDroppingFloor() + 1;

            TestResult nextSection = new TestResult(nextFloorToDrop,nextLowerFloor,numberOfDrops + start.getNumberOfDropped());

            return getLeastPossibleDrops(nextSection);
        }
    }

    private int lookNoFurther (TestResult test) {

        /** If breakingPoint is at droppingFloor or top level 50
         *  There's no need to test drop anymore **/

        return breakingPoint == test.getDroppingFloor() || breakingPoint == 50 ? 0 : 1;
    }

    private int tryFromNextSection(TestResult start) {

        /** It takes at least one drop to find out **/
        int numberOfDrops = 1;

        if (start.getDroppingFloor() >= breakingPoint) {

            /** The difference between breakingPoint and beginning of this section
             *  plus 1 is the number of drops to see it breaks **/

            numberOfDrops += breakingPoint - start.getLowerDroppingFloor() + lookNoFurther(start);

            dropLog("Found breakingPoint from lowerFloor: ",start);
        }

        return numberOfDrops;
    }

    class TestResult {
        private int droppingFloor;
        private int lowerDroppingFloor;
        private int numberOfDropped;

        public TestResult(int droppingFloor, int lowerDroppingFloor,int numberOfDropped){
            this.setDroppingFloor(droppingFloor);
            this.setLowerDroppingFloor(lowerDroppingFloor);
            this.setNumberOfDropped(numberOfDropped);
        }

        public String toString () {
            return    "\n droppingFloor:      " + Integer.toString(getDroppingFloor())
                    + "\n lowerDroppingFloor: " + Integer.toString(getLowerDroppingFloor())
                    + "\n numberOfDropped:    " + Integer.toString(getNumberOfDropped())
                    + "\n";
        }

        public int getDroppingFloor() {
            return droppingFloor;
        }

        public void setDroppingFloor(int droppingFloor) {
            this.droppingFloor = droppingFloor;
        }

        public int getLowerDroppingFloor() {
            return lowerDroppingFloor;
        }

        public void setLowerDroppingFloor(int lowerDroppingFloor) {
            this.lowerDroppingFloor = lowerDroppingFloor;
        }

        public int getNumberOfDropped() {
            return numberOfDropped;
        }

        public void setNumberOfDropped(int numberOfDropped) {
            this.numberOfDropped = numberOfDropped;
        }
    }


}
