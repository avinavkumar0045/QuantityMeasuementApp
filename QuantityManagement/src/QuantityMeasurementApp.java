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
    }

    // ===== VALUE OBJECT =====
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // =========================
        // UC6 (default unit = first operand)
        // =========================
        public Quantity add(Quantity other) {
            return add(other, this.unit);
        }

        // =========================
        // UC7 (explicit target unit)
        // =========================
        public Quantity add(Quantity other, LengthUnit targetUnit) {

            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Arguments cannot be null");
            }

            double sumInFeet = this.toFeet() + other.toFeet();

            double resultValue = targetUnit.fromFeet(sumInFeet);

            return new Quantity(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Quantity)) return false;

            Quantity other = (Quantity) obj;

            return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ===== STATIC API (OPTIONAL BUT EXPECTED) =====
    public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
        return q1.add(q2, targetUnit);
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println(q1.add(q2, LengthUnit.FEET));   // 2.0 FEET
        System.out.println(q1.add(q2, LengthUnit.INCH));   // 24.0 INCH
        System.out.println(q1.add(q2, LengthUnit.YARD));   // ~0.667 YARD
    }
}