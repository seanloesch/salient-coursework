package CS145;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Objects;

public class EgyptianFraction {
    private final ArrayList<BigFraction> fractions = new ArrayList<>();

    public EgyptianFraction() {
    }

    public EgyptianFraction(BigFraction f) {
        this();
        BigFraction zeroBigFraction = new BigFraction();
        BigInteger currentDen = new BigInteger("2");
        while (!(f.getNum().equals(BigInteger.ZERO))) {
            BigFraction current = new BigFraction(BigInteger.ONE, currentDen);
            if (f.subtract(current).compareTo(zeroBigFraction) > 0) {
                fractions.add(new BigFraction(BigInteger.ONE, currentDen));
                f = f.subtract(current).simplify();
            }
            currentDen = currentDen.add(BigInteger.ONE);
        }
    }

    public EgyptianFraction add(EgyptianFraction other) {
        BigFraction sum1 = new BigFraction();
        BigFraction sum2 = new BigFraction();
        for (BigFraction fraction : this.fractions) {
            sum1 = sum1.add(fraction);
        }
        for (BigFraction fraction : other.fractions) {
            sum2 = sum2.add(fraction);
        }

        return new EgyptianFraction(sum1.add(sum2));
    }

    public boolean equals(EgyptianFraction other) {
        BigFraction fraction1 = new BigFraction();
        BigFraction fraction2 = new BigFraction();
        for (BigFraction fraction : this.fractions) {
            fraction1 = fraction1.add(fraction);
        }
        for (BigFraction fraction : other.fractions) {
            fraction2 = fraction2.add(fraction);
        }

        return fraction1.compareTo(fraction2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fractions);
    }

    @Override
    public String toString() {
        String res = "";
        res += this.fractions.get(0);
        for (BigFraction fraction: this.fractions.subList(1, fractions.size())) {
            res += " + " + fraction;
        }
        return res;
    }
}
