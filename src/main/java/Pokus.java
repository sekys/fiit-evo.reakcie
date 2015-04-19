import org.apache.commons.math3.primes.Primes;

import java.util.Random;

/**
 * Created by Seky on 25. 2. 2015.
 */
public final class Pokus {
    private Random rnd = new Random();

    private void vykonajPokus(Integer id, int velkostPopulacie, int reakcii, int maxValue) {
        // Priprav prostredie
        XLS xls = new XLS("graph" + id + ".xls", velkostPopulacie, 2);
        Populacia pop = new Populacia(rnd, maxValue, velkostPopulacie);
        System.out.println(primeNumbers(maxValue));

        for (int reakcia = 0; reakcia < reakcii; reakcia++) {
            pop.reakcia(rnd);
            xls.setCell(reakcia, 0, pop.getAirthmeticMean());
            xls.setCell(reakcia, 1, pop.stdDev());
            xls.setCell(reakcia, 2, pop.countPrimeNumbers());
        }
        xls.write();
    }

    public int primeNumbers(int max_value) {
        int pocet = 0;
        for(int i=2; i < max_value; i++) {
            if(Primes.isPrime(i)) {
                pocet++;
            }
        }
        return pocet;
    }

    public void vykonajPokusy() {
        vykonajPokus(1, 300, 20000, 500);
    }

    public static void main(String[] args) {
        Pokus g = new Pokus();
        g.vykonajPokusy();
    }

}
