import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void test_Feet_Inches_ToFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals("2.0 FEET", q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET).toString());
    }

    @Test
    void test_Feet_Inches_ToInch() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals("24.0 INCH", q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH).toString());
    }

    @Test
    void test_Feet_Inches_ToYard() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals("0.6666666666666666 YARD",
                q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD).toString());
    }

    @Test
    void test_Yard_Feet_ToYard() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals("2.0 YARD", q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD).toString());
    }

    @Test
    void test_NegativeValues() {
        var q1 = new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(-2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals("36.0 INCH",
                q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH).toString());
    }

    @Test
    void test_NullTargetUnit() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(q2, null));
    }

    @Test
    void test_Commutativity_WithTargetUnit() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD);
        var r2 = q2.add(q1, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(r1.toString(), r2.toString());
    }
}