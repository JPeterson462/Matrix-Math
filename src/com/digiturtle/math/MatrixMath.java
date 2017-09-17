package com.digiturtle.math;

public class MatrixMath {

	/** Given matrices A, B, and C such that AB = C, solve for B */
	public static void solveForB(MatrixContainer matrixA, MatrixContainer matrixB, MatrixContainer matrixC) {
		double a = matrixA.getA();
		double b = matrixA.getB();
		double c = matrixA.getC();
		double d = matrixA.getD();
		double e = matrixA.getE();
		double f = matrixA.getF();
		double m = matrixC.getA();
		double n = matrixC.getB();
		double o = matrixC.getC();
		double p = matrixC.getD();
		double q = matrixC.getE();
		double r = matrixC.getF();
		// m = ag + ch, n = bg + dh, o = ai + cj, p = bi + dj, q = ak + cl + e, r = bk + dl + f
		// Solve for g, h, i, j, k, l
		double invA = 1.0 / a, invDABC = 1.0 / (d * a - b * c);
		double h = (n * a - b * m) * invDABC;
		double g = (m - c * h) * invA;
		double j = (p * a - b * o) * invDABC;
		double i = (o - c * j) * invA;
		double l = (a * (r - f) - b * (q - e)) * invDABC;
		double k = (q - e - c * l) * invA;
		// Store the result
		matrixB.set((float) g, (float) h, (float) i, (float) j, (float) k, (float) l);
	}
	
}
