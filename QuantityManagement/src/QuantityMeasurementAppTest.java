import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    double EPS = 1e-6;

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPS);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPS);
    }

    @Test
    void testRoundTrip() {
        double val = 5.0;

        double converted = QuantityMeasurementApp.convert(
                QuantityMeasurementApp.convert(val,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(val, converted, EPS);
    }

    @Test
    void testZero() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPS);
    }

    @Test
    void testNegative() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPS);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(1.0, null,
                    QuantityMeasurementApp.LengthUnit.FEET);
        });
    }

    @Test
    void testNaN() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.NaN,
                    QuantityMeasurementApp.LengthUnit.FEET,
                    QuantityMeasurementApp.LengthUnit.INCH);
        });
    }
}