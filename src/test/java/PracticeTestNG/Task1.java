package PracticeTestNG;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.lang.reflect.Method;

public class Task1 {
    private org.testng.log4testng.Logger logger =  Logger.getLogger(Task1.class);

    @BeforeSuite
    public  void initialLogger(){
        logger.info("Ping from BeforeSuite");
        System.out.println(" --- Prepare executing suite ---");
    }

    @AfterSuite
    public  void suiteEnd(){
        logger.info("Ping from AfterSuite");
        System.out.println("\n --- Suite executing end ---");
    }

    @BeforeGroups
    public void groupStart(){
        System.out.println("\n - Group executing start -");
    }

    @AfterGroups
    public void groupsEnd(){
        System.out.println("\n - Group executing end -");
    }

    @BeforeClass
    public void startClass(){
        System.out.println("\n*** Prepare executing " + this.getClass().getName() + " ***");
    }

    @AfterClass
    public void stopClass(){
        System.out.println("\n*** Stop executing " + this.getClass().getName() + " ***");
    }

    @BeforeMethod
    public void prepareMethod(Method method){
        System.out.println("\n * Prepare executing " + method.getName() + " *");
    }

    @AfterMethod
    public void endMethod(Method method){
        System.out.println("\n * End of executing " + method.getName() + " *");
    }

    @DataProvider
    public Object[][] setOfNumbers(){
        return new Object[][]{
                {new Double(2), new Double(4)},
                {new Double(4), new Double(256.00001)}
        };
    }

    @Test(priority = 0, dataProvider = "setOfNumbers", groups = {"failTests"})
    public void powTest(Double number, Double expectedPower){
       logger.info("Test pow function");
       System.out.println("\tTest pow function start with " + number);

       Assert.assertEquals(Math.pow(number, number), expectedPower, String.format("Compare %.1f^%.1f and %.1f", number, number, expectedPower));

       System.out.println("\tTest pow function end");
    }

    @Test(priority = 1, parameters = {"myPi"}, groups = {"positiveTests"})
    @Parameters("myPi")
    public void comparePi(Double myPi){
        logger.info("Test pi const");
        System.out.println("\tTest pi const start");

        Assert.assertNotEquals(Math.PI, myPi, "");
        System.out.println("\tTest pi const end");
    }

    @Test(priority = 2, dataProvider = "setOfNumbers", groups = {"positiveTests"})
    public void testAbsoluteNumbers(Double number1, Double number2){
        System.out.println("\tTest Absolute Numbers start with" + number1);
        Assert.assertFalse(number1.equals(Math.abs(number2)));
        System.out.println("\tTest Absolute Numbers end");
    }


    @Test(priority = 3, dataProvider = "setOfNumbers", groups = {"positiveTests"})
    public void testFindMax(Double number1, Double number2){
        System.out.println("\tTest find max start with" + number1);

        Assert.assertTrue(number2.equals(Math.max(number1,number2)));

        System.out.println("\tTest find max end");
    }

    @Test(priority = 4, groups = {"failTests"})
    @Parameters("myE")
    public void testE(Double myE){
        System.out.println("\tTest compare E start with " + myE);

        if (!myE.equals(Math.E))
            Assert.fail("E doesn't equals 2.71");

        System.out.println("\tTest compare E end");
    }


}