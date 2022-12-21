
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;

public class TestPen {
    SoftAssert softAssert = new SoftAssert();
    @Test (description = "Proverka pervogo konstruktora")
    public void checkConstructor1() throws NoSuchFieldException, IllegalAccessException {
        int testInkContainerValue = 6;
        Pen testPen1 = new Pen(testInkContainerValue);
        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = (int)privateField.get(testPen1);
        Assert.assertEquals(actualInkContainerValue, testInkContainerValue);
    }

    @Test (description = "Proverka vtorogo konstructora")
    public void checkConstructor2() throws NoSuchFieldException, IllegalAccessException {
        int testInkContainerValue = 8;
        double testSizeLetter = 0.338;
        Pen testPen2 = new Pen(testInkContainerValue, testSizeLetter);

        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = (int)privateField.get(testPen2);

        Field privateField2 = Pen.class.getDeclaredField("sizeLetter");
        privateField2.setAccessible(true);
        double actualSizeLetter = (double)privateField2.get(testPen2);

        softAssert.assertEquals(actualInkContainerValue, testInkContainerValue);
        softAssert.assertEquals(actualSizeLetter, testSizeLetter);
        softAssert.assertAll();
    }

    @Test (description = "Proverka tretego konstructora")
    public void checkConstructor3() throws NoSuchFieldException, IllegalAccessException {
        int testInkContainerValue = 10;
        double testSizeLetter = 0.65;
        String testColor = "Red";
        Pen testPen3 = new Pen(testInkContainerValue, testSizeLetter, testColor);

        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = (int) privateField.get(testPen3);

        Field privateField2 = Pen.class.getDeclaredField("sizeLetter");
        privateField2.setAccessible(true);
        double actualSizeLetter = (double) privateField2.get(testPen3);

        Field privateField3 = Pen.class.getDeclaredField("color");
        privateField3.setAccessible(true);
        String actualColor = (String) privateField3.get(testPen3);

        softAssert.assertEquals(actualInkContainerValue, testInkContainerValue);
        softAssert.assertEquals(actualSizeLetter, testSizeLetter);
        softAssert.assertEquals(actualColor, testColor);
        softAssert.assertAll();
    }

    @Test
    public void testMetodaIsWorkPositiveCase() {
       Pen pen4 = new Pen(5);
       Assert.assertTrue(pen4.isWork(), "Ruchka ne rabotaet potomy chto net chernil");
    }

    @Test
    public void testMetodaIsWorkNegativeCase() {
        Pen pen5 = new Pen(2);
        Assert.assertFalse(pen5.isWork(), "Ruchka rabotaet potomy chto est' chernila");
    }

    @Test(dataProvider = "colors")
    public void testMetodaGetColorPositiveCase(String inputColor) {
        Pen pen6 = new Pen(2, 0.5, inputColor);
        Assert.assertEquals(pen6.getColor(), inputColor);
    }
    @DataProvider
    public Object[][] colors() {
        return new Object[][] {
                {"RED"},
                {"BLUE"},
                {"BLACK"}
        };
    }
}

