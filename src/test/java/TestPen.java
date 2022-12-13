/*public class TestPen {
    private final int inkContainerValue = 1000;
    // размер букв, которые пишутся ручкой
    private final double sizeLetter = 1.0;
    // цвет ручки
    private final String color = "BLUE";
    Pen pen = new Pen(inkContainerValue, sizeLetter, color);

    @Test
    public void testWork() {
        pen.isWork();
        System.out.println(pen.isWork());

    }


}*/
public class TestPen {
    public static void main(String[] args) {
        int [] numbers = new int[5];
        numbers [1] = 5;
        numbers [2] = 10;
        int sum = 0;
        for (int i:numbers) {
            sum = i*10;
            System.out.println(sum);
        }
    }
}

