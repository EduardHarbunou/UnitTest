
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
    @Test (description = "Proverka pervogo konstruktora")
    public void checkConstructor1() throws NoSuchFieldException, IllegalAccessException {
        int testInkContainerValue = 6;
        Pen testPen1 = new Pen(testInkContainerValue);

        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = Integer.parseInt(privateField.get(testPen1).toString());

        Assert.assertEquals(actualInkContainerValue, testInkContainerValue);
    }

    @Test (description = "Proverka vtorogo konstructora")
    public void checkConstructor2() throws NoSuchFieldException, IllegalAccessException {
        int testInkContainerValue = 8;
        double testSizeLetter = 0.338;
        Pen testPen2 = new Pen(testInkContainerValue, testSizeLetter);

        Field privateField = Pen.class.getDeclaredField("inkContainerValue");
        privateField.setAccessible(true);
        int actualInkContainerValue = Integer.parseInt(privateField.get(testPen2).toString());
        //int actualInkContainerValue = (int)privateField.get(testPen2);

        Field privateField2 = Pen.class.getDeclaredField("sizeLetter");
        privateField2.setAccessible(true);
        double actualSizeLetter = Double.parseDouble(privateField2.get(testPen2).toString());
        //double actualSizeLetter = (double)privateField2.get(testPen2);

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
        int actualInkContainerValue = Integer.parseInt(privateField.get(testPen3).toString());

        Field privateField2 = Pen.class.getDeclaredField("sizeLetter");
        privateField2.setAccessible(true);
        double actualSizeLetter = Double.parseDouble(privateField2.get(testPen3).toString());

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
       Pen pen4 = new Pen(-3);
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
        return new Object[][]{
                {"RED"},
                {"BLUE"},
                {"BLACK"}
        };
    }

    @Test (dataProvider = "positiveWord")
    public void testMetodaWritePositiveCase(int obemSterzhne, double shrift, String inputWord) {
            Pen pen7 = new Pen(obemSterzhne, shrift);
            Assert.assertEquals(pen7.write(inputWord), inputWord);
    }
    @DataProvider
    public Object[][] positiveWord() {
        return new Object[][]{
                {5, 1.0, "Test"},
                {45, 2.0, "Predlozhenie"},
                {333, 4.0, "Supersochinenie"}
        };
    }

    @Test (dataProvider = "negativeWordPystaiaStroka")
    public void testMetodaWriteNegativeCase(int obemSterzhne, double shrift, String inputWord, String result) {
        Pen pen7 = new Pen(obemSterzhne, shrift);
        Assert.assertEquals(pen7.write(inputWord), result);
    }
    @DataProvider
    public Object[][] negativeWordPystaiaStroka() {
        return new Object[][]{
                {-5, 1.0, "Test", ""},
                {-0, 2.0, "Predlozhenie", ""},
                {-333, 4.0, "Supersochinenie", ""}
        };
    }

    @Test (dataProvider = "Proverka partOfWord")
    public void testMetodaWriteNegativeCase2(int obemSterzhne, double shrift, String inputWord, String partOfWord) {
        Pen pen8 = new Pen(obemSterzhne, shrift);
        Assert.assertEquals(pen8.write(inputWord), partOfWord);
    }
    @DataProvider(name = "Proverka partOfWord")
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
        Pen pen9 = new Pen(4,3.0, testColor);
        pen9.doSomethingElse();
        Assert.assertEquals(testColor,output.toString().trim());
    }
}

