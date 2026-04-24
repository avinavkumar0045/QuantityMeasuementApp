public class QuantityMeasurementApp {

    // ===== ENUM =====
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }

        public double getFactor() {
            return toFeetFactor;
        }
    }

    // ===== Quantity Class =====
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ===== Instance Conversion =====
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double feetValue = this.toFeet();
            double converted = targetUnit.fromFeet(feetValue);

            return new Quantity(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            double epsilon = 1e-6;
            return Math.abs(this.toFeet() - other.toFeet()) < epsilon;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ===== Static Conversion API =====
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        // Normalize → feet → target
        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    // ===== Demo Methods (Overloading) =====

    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {
        return convert(value, from, to);
    }

    public static Quantity demonstrateLengthConversion(Quantity q,
                                                       LengthUnit to) {
        return q.convertTo(to);
    }

    // ===== Main =====
    public static void main(String[] args) {

        System.out.println(convert(1.0, LengthUnit.FEET, LengthUnit.INCH)); // 12.0
        System.out.println(convert(3.0, LengthUnit.YARD, LengthUnit.FEET)); // 9.0
        System.out.println(convert(36.0, LengthUnit.INCH, LengthUnit.YARD)); // 1.0
        System.out.println(convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH)); // ~0.393701
    }
}