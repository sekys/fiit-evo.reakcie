import org.apache.commons.math3.primes.Primes;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.Random;

/**
 * Created by Seky on 4. 3. 2015.
 * Predstavuje skupinu jedincov.
 */
public final class Populacia {
    private double[] cisla;
    private int max_value;

    public Populacia(Random rnd, int max_value, int size) {
        cisla = new double[size];
        this.max_value = max_value;
        for (int i = 0; i < size; i++) {
            cisla[i] = (Math.abs(rnd.nextInt()) % max_value + 2);
        }
    }

    public void reakcia(Random rnd) {
        int a = Math.abs(rnd.nextInt()) %  cisla.length;
        int b = Math.abs(rnd.nextInt()) %  cisla.length;
        if(a == b) {
            return;
        }

        int ia = (int) cisla[a];
        int ib = (int) cisla[b];
        if(ia != ib && ia % ib == 0)  {
            cisla[a] = ia / ib;
            return;
        }
        if(ia != ib && ib % ia == 0)  {
            cisla[b] = ib / ia;
            return;
        }
    }

    public double getAirthmeticMean(){
        return StatUtils.mean(cisla);
    }

    public double stdDev() {
        return new StandardDeviation().evaluate(cisla);
    }

    private boolean contains(int i) {
        for(int a=0; a < cisla.length; a++) {
            if(((int) cisla[a]) == i) {
                return true;
            }
        }
        return false;
    }

    public int countPrimeNumbers() {
        int pocet = 0;
        for(int a=0; a < cisla.length; a++) {
            if(Primes.isPrime(((int) cisla[a]))) {
                pocet++;
            }
        }
        return pocet;
    }

    public int primeNumbers() {
        int pocet = 0;
        for(int i=2; i < max_value; i++) {
            if(Primes.isPrime(i) && contains(i)) {
                pocet++;
            }
        }
        return pocet;
    }
}
