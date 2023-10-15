package edu.hw2;

public final class Task1 {

    private Task1() {
    }

    public sealed interface Expr permits Expr.Addition, Expr.Constant, Expr.Exponent, Expr.Multiplication, Expr.Negate {
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

}
