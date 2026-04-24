import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testYardToFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testYardToInches() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testCentimeterToInch() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var q2 = new QuantityMeasurementApp.Quantity(0.393701, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testDifferentValues() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testTransitiveProperty() {
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARD);
        var feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var inch = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}