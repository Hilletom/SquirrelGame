package Test;



import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class XYTest {
    private XY Test;



    @Before

    public void setup() {

        Test = new XY(1,1);

    }



    @Test
    public void testPlus() {

        assertEquals(new XY(2,2), Test.plus(Test));

    }



    @Test

    public void testMinus() {

        assertEquals(new XY(0, 0), Test.minus(Test));

    }



    @Test

    public void testTimes() {

        int factor = 5;

        assertEquals(new XY(factor, factor), Test.times(factor));

    }



    @Test

    public void testLength() {

        assertEquals(Math.sqrt(2), Test.length(), 1/Double.MAX_VALUE);

    }



    @Test

    public void testDistanceFrom() {

        XY test1 = new XY(1, 0);

        assertEquals(1, Test.distanceFrom(test1), 1/Double.MAX_VALUE);



        XY test2 = new XY(1, 2);

        assertEquals(1, Test.distanceFrom(test2), 1/Double.MAX_VALUE);



        XY test3 = new XY(0, 1);

        assertEquals(1, Test.distanceFrom(test3), 1/Double.MAX_VALUE);



        XY test4 = new XY(2, 1);

        assertEquals(1, Test.distanceFrom(test4), 1/Double.MAX_VALUE);



        XY test5 = new XY(4, 5);

        assertEquals(5, Test.distanceFrom(test5), 1/Double.MAX_VALUE);

    }



    @Test

    public void testHashCode() {

        assertEquals(993, Test.hashCode());

    }



    @Test

    public void testEquals() {

        assertEquals(Test, Test);



        XY test1 = new XY(2, 2);

        assertNotEquals(Test, test1);



        XY test2 = new XY(Test.x, Test.y);

        assertEquals(Test, test2);

    }



    @Test

    public void testToString() {

        String string = Test.x + " " + Test.y;

        assertEquals(string, Test.toString());

    }
}
