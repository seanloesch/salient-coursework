package CS145;

import java.math.BigInteger;
import java.util.Objects;

public class BigFraction {
    private BigInteger num;
    private BigInteger den;

    public BigFraction() {
        num = BigInteger.ZERO;
        den = BigInteger.ONE;
    }

    public BigFraction(BigInteger n, BigInteger d) {
        num = n;
        den = d;
    }

    public BigInteger getNum() {
        return this.num;
    }

    public BigInteger getDen() {
        return this.den;
    }

    public BigFraction add(BigFraction other) {
        BigFraction thisClone = new BigFraction(this.num, this.den);

        BigInteger denLCM = (thisClone.den.multiply(other.den)).divide(gcd1(thisClone.den, other.den));
        BigFraction thisMultiplier = new BigFraction(denLCM.divide(thisClone.den), denLCM.divide(thisClone.den));
        BigInteger otherNumMultiplier = denLCM.divide(other.den);
        thisClone = thisClone.multiply(thisMultiplier);

        // Adding other numerator
        return new BigFraction(thisClone.num.add(other.num.multiply(otherNumMultiplier)), denLCM);
    }

    public BigFraction subtract(BigFraction other) {
        BigFraction thisClone = new BigFraction(this.num, this.den);

        BigInteger denLCM = (thisClone.den.multiply(other.den)).divide(gcd1(thisClone.den, other.den));
        BigFraction thisMultiplier = new BigFraction(denLCM.divide(thisClone.den), denLCM.divide(thisClone.den));
        BigInteger otherNumMultiplier = denLCM.divide(other.den);
        thisClone = thisClone.multiply(thisMultiplier);

        return new BigFraction(thisClone.num.subtract(other.num.multiply(otherNumMultiplier)), denLCM);
    }

    public BigFraction multiply(BigFraction other) {
        BigFraction thisClone = new BigFraction(this.num, this.den);

        thisClone.num = thisClone.num.multiply(other.num);
        thisClone.den = thisClone.den.multiply(other.den);
        return thisClone;
    }

    public BigFraction divide(BigFraction other) {
        BigFraction thisClone = new BigFraction(this.num, this.den);

        thisClone.num = thisClone.num.multiply(other.den);
        thisClone.den = thisClone.den.multiply(other.num);
        return thisClone;
    }

    public BigFraction simplify() {
        BigInteger gcd = this.gcd2();
        this.num = this.num.divide(gcd);
        this.den = this.den.divide(gcd);
        return this;
    }

    public BigInteger gcd1(BigInteger a, BigInteger b) {
        a = a.abs();
        b = b.abs();
        // Euclidean algorithm
        if (a.compareTo(b) > 0) {
            BigInteger temp = b;
            b = a;
            a = temp;
        }
        if (a.equals(BigInteger.ZERO)) {
            return b;
        } else {
            return gcd1(b.mod(a), a);
        }
    }

    public BigInteger gcd2() {
        return gcd1(this.num, this.den);
    }

    @Override
    public String toString() {
        return this.num + "/" + this.den;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigFraction that = (BigFraction) o;
        return Objects.equals(num, that.num) && Objects.equals(den, that.den);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }

    public int compareTo(BigFraction other) {
        BigFraction thisClone = this;

        // Grabbing denominators of both fractions and finding their greatest common denominator
        BigInteger thisDen = thisClone.den;
        BigInteger otherDen = other.den;
        BigInteger lcm = thisDen.multiply(otherDen).divide(gcd1(thisDen, otherDen));

        // Finding what to multiply each fraction by to make denominators equal according to LCM
        BigInteger thisM = lcm.divide(thisDen);
        BigInteger otherM = lcm.divide(otherDen);

        BigFraction thisMultiplier = new BigFraction(thisM, thisM);
        BigFraction otherMultiplier = new BigFraction(otherM, otherM);

        // Multiplying each fraction so that they can be compared without worrying about denominator
        thisClone.multiply(thisMultiplier);
        other.multiply(otherMultiplier);

        if (this.den.equals(BigInteger.ZERO) || other.den.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Cannot compare invalid BigFractions");
        } else if (thisClone.num.subtract(other.num).compareTo(BigInteger.ZERO) >= 0) {
            return 1;
        } else if (thisClone.num.compareTo(other.num) == 0 && thisClone.den.compareTo(other.den) == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
