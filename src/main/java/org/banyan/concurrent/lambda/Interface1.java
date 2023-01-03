package org.banyan.concurrent.lambda;

public class Interface1 {

    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(positive(a));
        }

        static int positive(int a) {
            return a > 0 ? a : 0;
        }
    }

    public static void main(String[] args) {
        Formula formula1 = new Formula() {
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        double v1 = formula1.calculate(10);
        formula1.sqrt(-23);
        Formula.positive(-4);
        System.err.println(v1);
    }

}