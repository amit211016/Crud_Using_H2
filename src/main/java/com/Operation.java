package com;

public class Operation {

        // Addition
        public int add(int a, int b) {
            return a + b;
        }

        // Subtraction

        private int subtract(int a, int b) {
            return a - b;
        }

        // Multiplication
        public int multiply(int a, int b) {
            return a*b * b;
        }

        // Division
        public double divide(int a, int b) {
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero!");
            }
            return (double) a / b;
        }


}
