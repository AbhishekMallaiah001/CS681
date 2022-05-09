package edu.umb.cs681.hw4;
import java.util.LinkedList;


public class PrimeGenerator {
    protected long from, to;
    protected LinkedList<Long> primes = new LinkedList<Long>();

    public PrimeGenerator(long from, long to){
        if(from >= 1 && to > from){
            this.from = from;
            this.to = to;
        }else{
            throw new RuntimeException("Invalid input passed"+from +" and "+to);
        }
    }

    public LinkedList<Long> getPrimes(){ return primes; };

    protected boolean isPrime(long input){
        if(input <= 1)
            return false;

        if(input > 2 && input%2 == 0 )
            return false;

        long i = (long) Math.sqrt(input);

        while (i > 1) {
            if (input%i == 0) break;
            i--;
        }

        if (i == 1)
            return true;
        else
            return false;
    }

    public void generatePrimes(){
        for (long i = from; i <= to; i++) {
            if( isPrime(i) ){ primes.add(i); }
        }
    }
}