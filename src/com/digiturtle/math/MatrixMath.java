package com.digiturtle.math;

public class MatrixMath {

	/** Given matrices A, B, and C such that AB = C, solve for B */
	public static void solveForB(MatrixContainer matrixA, MatrixContainer matrixB, MatrixContainer matrixC) {
		float a = matrixA.getA();
		float b = matrixA.getB();
		float c = matrixA.getC();
		float d = matrixA.getD();
		float e = matrixA.getE();
		float f = matrixA.getF();
		float m = matrixC.getA();
		float n = matrixC.getB();
		float o = matrixC.getC();
		float p = matrixC.getD();
		float q = matrixC.getE();
		float r = matrixC.getF();
		// m = ag + ch, n = bg + dh, o = ai + cj, p = bi + dj, q = ak + cl + e, r = bk + dl + f
		// Solve for g, h, i, j, k, l
		float invA = 1f / a, invDABC = 1f / (d * a - b * c);
		float h = (n * a - b * m) * invDABC;
		float g = (m - c * h) * invA;
		float j = (p * a - b * o) * invDABC;
		float i = (o - c * j) * invA;
		float l = (r * (a - f) - b * (q - e)) * invDABC;
		float k = (q - e - c * l) * invA;
		// Store the result
		matrixB.set(g, h, i, j, k, l);
	}
	
}
