package com.loony;

// Function -> f(x1,x2,x3)= (x^2) + (2*y^2) + (2*z^2) + (2*x*y) + (2*y*z)
// Gradient -> [2x+2y, 4y+2x+2z, 4z+2y]
// gradient magnitude equals to derivative
public class GradientDescent {
    double objFunc(double[] x) {
        return (x[0] * x[0]) + (2 * x[1] * x[1]) + (2 * x[2] * x[2] + (2 * x[0] * x[1]) + (2 * x[1] * x[2]));
    }

    double magnitude(double[] x) {
        return Math.sqrt(x[0] * x[0] + x[1] * x[1] * x[2] * x[2]);
    }

    // derivation is simplified because of focus on algorithm
    double[] calculateGradient(double[] x) {
        double[] gradient = new double[3];
        gradient[0] = 2 * x[0] + 2 * x[1];
        gradient[1] = 4 * x[1] + 2 * x[0] + 2 * x[2];
        gradient[2] = 4 * x[2] + 2 * x[1];
        return gradient;
    }

    void algorithm() {
        // epsilon is not zero because data type is double
        // If you want to zero error, have to use more precise data structure
        double epsilon = 0.01, alpha = 0.1, prevMagnitude = 1, beta;
        int iteration = 0;
        double[] x = {1, 1, 1};
        double[] d = new double[3];
        double[] g = calculateGradient(x);

        while (magnitude(g) > epsilon) {
            if (iteration != 0) {
                beta = ((magnitude(g) / prevMagnitude)) * ((magnitude(g) / prevMagnitude));
                d[0] = -g[0] + beta * d[0];
                d[1] = -g[1] + beta * d[1];
                d[2] = -g[2] + beta * d[2];
            } else {
                d[0] = -g[0];
                d[1] = -g[1];
                d[2] = -g[2];
            }

            prevMagnitude = magnitude(g);

            x[0] = x[0] + alpha * d[0];
            x[1] = x[1] + alpha * d[1];
            x[2] = x[2] + alpha * d[2];

            g = calculateGradient(x);
            iteration++;
        }

        System.out.println("Minimum Value: " + objFunc(x));
        System.out.println("Minimum X: (" + x[0] + ", " + x[1] + ", " + x[2] + ")");
        System.out.println("Iteration: " + iteration);
    }
}
