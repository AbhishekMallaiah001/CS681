package edu.umb.cs681.hw8;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {

    public RunnablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend);
        if (from >= 2 && to >= from) {
            this.from = from;
            this.to = to;
        } else {
            throw new RuntimeException(
                    "Invalid values for variables 'from' and 'to'");
        }
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {

            if (divisor > 2 && divisor % 2 == 0) {
                divisor++;
                continue;
            }
            if (dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            } else {
                if (divisor == 2) {
                    divisor++;
                } else {
                    divisor += 2;
                }
            }
        }
    }

    public void run() {
        generatePrimeFactors();
        System.out.println("Current Thread ID " + Thread.currentThread().getId() + " generated " + factors);
    }
}