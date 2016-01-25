package org.vnsgbt.infoplus;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * Created by dev on 1/22/16.
 *
 */
public class Question1Test {

    @Test(dataProvider = "Fifty-floors-array")
    public void TestBreakingPointProperty (Class clazz, Integer breakingPoint) {
        Question1 testWithBreakingPoint = new Question1(breakingPoint);
        Assert.assertEquals(testWithBreakingPoint.getBreakingPoint(),breakingPoint.intValue());
    }

    @Test(dataProvider = "Fifty-floors-array")
    public void TestLeastPossibleDrops (Class clazz, Integer breakingPointAndLPD) {
        Question1 testWithBreakingPoint = new Question1(breakingPointAndLPD);
        int result = testWithBreakingPoint.getLeastPossibleDrops(testWithBreakingPoint.startTest);
        System.out.println("Optimal drops: " + result);
        Assert.assertTrue(result <= 12);
    }

    @DataProvider(name = "Fifty-floors-array")
    public Object[][] parameterLPDProvider() {

        Object[][] params = new Object[50][2];

        for (int i = 0; i < 50; i++) {
            params[i][0] = Integer.class;
            params[i][1] = i+1;
        }

        return params;
    }
}
