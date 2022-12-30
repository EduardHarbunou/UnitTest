
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class TestPen {
    SoftAssert softAssert = new SoftAssert();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Test (dataProvider = "firstConstructor", description = "Checking the first constructor")
    public void checkOneParametrConstructor(int testInkContainerValue) throws NoSuchFieldException, IllegalAccessException {
        Pen testPen = new Pen(testInkContainerValue);
        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = Integer.parseInt(privateField.get(testPen).toString());

        Assert.assertEquals(actualInkContainerValue, testInkContainerValue);
    }

    @DataProvider
    public Object[][] firstConstructor() {
        return new Object[][]{
                {101},
                {1},
                {0}
        };
    }

    @Test (dataProvider = "secondConstructor", description = "Checking the second constructor")
    public void checkTwoParametrsConstructor(int testInkContainerValue, double testSizeLetter) throws NoSuchFieldException, IllegalAccessException {
        Pen testPen = new Pen(testInkContainerValue, testSizeLetter);

        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = Integer.parseInt(privateField.get(testPen).toString());

        Field privateField2 = Pen.class.getDeclaredField("sizeLetter");
        privateField2.setAccessible(true);
        double actualSizeLetter = Double.parseDouble(privateField2.get(testPen).toString());

        softAssert.assertEquals(actualInkContainerValue, testInkContainerValue);
        softAssert.assertEquals(actualSizeLetter, testSizeLetter);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] secondConstructor() {
        return new Object[][]{
                {101, 0.01},
                {1, 0.99},
                {0, 1.01}
        };
    }

    @Test (dataProvider = "thirdConstructor", description = "Checking the third constructor")
    public void checkThreeParametrsConstructor(int testInkContainerValue, double testSizeLetter, String testColor) throws NoSuchFieldException, IllegalAccessException {
        Pen testPen = new Pen(testInkContainerValue, testSizeLetter, testColor);

        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = Integer.parseInt(privateField.get(testPen).toString());

        Field privateField2 = Pen.class.getDeclaredField("sizeLetter");
        privateField2.setAccessible(true);
        double actualSizeLetter = Double.parseDouble(privateField2.get(testPen).toString());

        Field privateField3 = Pen.class.getDeclaredField("color");
        privateField3.setAccessible(true);
        String actualColor = (String) privateField3.get(testPen);

        softAssert.assertEquals(actualInkContainerValue, testInkContainerValue);
        softAssert.assertEquals(actualSizeLetter, testSizeLetter);
        softAssert.assertEquals(actualColor, testColor);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] thirdConstructor() {
        return new Object[][]{
                {101, 0.01, "Red"},
                {1, 0.99, "Black"},
                {0, 1.01, "Violet"}
        };
    }


    @Test(description = "Positive test of isWork()")
    public void testPositiveCaseIsWork() {
       Pen testPen = new Pen(-3);
       Assert.assertTrue(testPen.isWork(), "Ruchka ne rabotaet potomy chto net chernil");
    }

    @Test(description = "Negative test of isWork()")
    public void testNegativeCaseIsWork() {
        Pen testPen = new Pen(2);
        Assert.assertFalse(testPen.isWork(), "Ruchka rabotaet potomy chto est' chernila");
    }

    @Test(dataProvider = "colors", description = "Test of getColor()")
    public void testMetodaGetColor(String testColor) {
        Pen testPen = new Pen(2, 0.5, testColor);
        Assert.assertEquals(testPen.getColor(), testColor);
    }
    @DataProvider
    public Object[][] colors() {
        return new Object[][]{
                {"RED"},
                {"BLUE"},
                {"BLACK"}
        };
    }

    @Test (dataProvider = "positiveWord", description = "Positive test of write()")
    public void testMetodaWritePositiveCase(int testInkContainerValue, double testSizeLetter, String inputWord) {
            Pen testPen = new Pen(testInkContainerValue, testSizeLetter);
            Assert.assertEquals(testPen.write(inputWord), inputWord);
    }
    @DataProvider
    public Object[][] positiveWord() {
        return new Object[][]{
                {5, 1.0, "Test"},
                {45, 2.0, "Predlozhenie"},
                {333, 4.0, "Supersochinenie"}
        };
    }

    @Test (dataProvider = "negativeWord", description = "The first negative test of write()")
    public void testMetodaWriteNegativeCase1(int testInkContainerValue, double testSizeLetter, String inputWord, String result) {
        Pen testPen = new Pen(testInkContainerValue, testSizeLetter);
        Assert.assertEquals(testPen.write(inputWord), result);
    }
    @DataProvider
    public Object[][] negativeWord() {
        return new Object[][]{
                {-5, 1.0, "Test", ""},
                {-0, 2.0, "Predlozhenie", ""},
                {-333, 4.0, "Supersochinenie", ""}
        };
    }

    @Test (dataProvider = "partOfWord", description = "The second negative test of write()")
    public void testMetodaWriteNegativeCase2(int obemSterzhne, double shrift, String inputWord, String partOfWord) {
        Pen testPen = new Pen(obemSterzhne, shrift);
        Assert.assertEquals(testPen.write(inputWord), partOfWord);
    }
    @DataProvider
    public Object[][] partOfWord() {
        return new Object[][]{
                {3, 1.0, "Test", "Tes"},
                {10, 2.0, "Predlozhenie", "Predl"},
                {40, 4.0, "Supersochinenie", "Supersochi"}
        };
    }

    @Test
    public void testMetodaDoSomethingElse() {
        String testColor = "Red";
        System.setOut(new PrintStream(output));
        Pen testPen = new Pen(4,3.0, testColor);
        testPen.doSomethingElse();
        Assert.assertEquals(testColor,output.toString().trim());
    }
}

