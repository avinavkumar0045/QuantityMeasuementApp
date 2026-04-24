import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition_FeetPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals("3.0 FEET", q1.add(q2).toString());
    }

    @Test
    void testAddition_FeetPlusInch() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals("2.0 FEET", q1.add(q2).toString());
    }

    @Test
    void testAddition_InchPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals("24.0 INCH", q1.add(q2).toString());
    }

    @Test
    void testAddition_YardPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals("2.0 YARD", q1.add(q2).toString());
    }

    @Test
    void testAddition_Zero() {
        var q1 = new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(0.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals("5.0 FEET", q1.add(q2).toString());
    }

    @Test
    void testAddition_Negative() {
        var q1 = new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(-2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals("3.0 FEET", q1.add(q2).toString());
    }

    @Test
    void testAddition_Commutativity() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(q1.add(q2).toString(), q2.add(q1).toString());
    }

    @Test
    void testAddition_Null() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }
}