package edu.umb.cs681.hw6;

import java.util.LinkedList;

public class PrimeFactorizer {
    protected long dividend, from, to;
    protected LinkedList<Long> factors = new LinkedList<Long>();

    public PrimeFactorizer(long dividend){
        if(dividend >= 2){
            this.dividend = dividend;
            this.from = 2;
            this.to = dividend;
        }else{
            throw new RuntimeException("dividend must be >= 2. dividend==" + dividend);
        }
    }

    public long getDividend() { return dividend; }
    public long getFrom(){ return from; }
    public long getTo(){ return to; }
    public LinkedList<Long> getPrimeFactors(){ return factors; }

    public void generatePrimeFactors() {
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            if(dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            }else {
                if(divisor==2){ divisor++; }
                else{ divisor += 2; }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Prime Factors of 8: ");
        PrimeFactorizer fac = new PrimeFactorizer(8);
        fac.generatePrimeFactors();
        System.out.println(fac.getPrimeFactors());

        System.out.print("Prime Factors of 13: ");
        fac = new PrimeFactorizer(13);
        fac.generatePrimeFactors();
        System.out.println(fac.getPrimeFactors());

        System.out.print("Prime Factors of 123: ");
        fac = new PrimeFactorizer(123);
        fac.generatePrimeFactors();
        System.out.println(fac.getPrimeFactors());

        System.out.print("Prime Factors of 200: ");
        fac = new PrimeFactorizer(200);
        fac.generatePrimeFactors();
        System.out.println(fac.getPrimeFactors());

        System.out.print("Prime Factors of 500: ");
        fac = new PrimeFactorizer(500);
        fac.generatePrimeFactors();
        System.out.println(fac.getPrimeFactors());

        System.out.print("Prime factors of 4096: ");
        fac = new PrimeFactorizer(4096);
        fac.generatePrimeFactors();
        System.out.println(fac.getPrimeFactors());

        System.out.print("Prime factors of 3333: ");
        fac = new PrimeFactorizer(3333);
        fac.generatePrimeFactors();
        System.out.println(fac.getPrimeFactors());
    }
}