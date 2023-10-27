package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double a) implements Expr {
        @Override
        public double evaluate() {
            return a;
        }
    }

    record Negate(Expr a) implements Expr {
        @Override
        public double evaluate() {
            return -a.evaluate();
        }
    }

    record Exponent(Expr b, double n) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(b.evaluate(), n);
        }
    }

    record Addition(Expr a, Expr b) implements Expr {
        @Override
        public double evaluate() {
            return a.evaluate() + b.evaluate();
        }
    }

    record Multiplication(Expr a, Expr b) implements Expr {
        @Override
        public double evaluate() {
            return a.evaluate() * b.evaluate();
        }
    }
}

