package PracticeTestNG;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class Task1 {
    private org.testng.log4testng.Logger logger =  Logger.getLogger(Task1.class);
    @BeforeSuite
    public  void initialLogger(){
        logger.info("Ping from BeforeSuite");
    }

    @DataProvider
    public Object[][] setOfNumbers(){
        return new Object[][]{
                {new Double(2), new Double(2)},
                {new Double(4), new Double(4)}
        };
    }


    @Test(dataProvider = "setOfNumbers")
    public void powTest(Double number, Double exponent){
       logger.info("Test pow function");

       Assert.assertEquals(Math.pow(number, exponent), 4.0, "Compare two double");
    }
}
