package test.digiturtle.math;

import org.joml.Matrix3f;

import com.digiturtle.math.MatrixContainer;
import com.digiturtle.math.MatrixMath;

public class Tests {

	@FunctionalInterface
	interface Test {
		public boolean test();
	}
	
	// Current error is ~1e-6
	public static void main(String[] args) {
		Test[] tests = {
			Tests::test1,
			Tests::test2,
			Tests::test3,
			Tests::test4,
			Tests::test5,
			Tests::test6
		};
		boolean valid = true;
		for (int i = 0; i < tests.length; i++) {
			boolean test = tests[i].test();
			if (!test) {
				System.out.println("Failed: Test " + (i + 1));
			}
			valid = valid && test;
		}
		if (valid) {
			System.out.println("Success!");
		}
	}
	
	public static boolean test(MatrixContainer a, MatrixContainer b, MatrixContainer c) {
		MatrixMath.solveForB(a, b, c);
		Matrix3f matA = toMatrix(a), matB = toMatrix(b), matC = toMatrix(c);
		Matrix3f result = new Matrix3f();
		matA.mul(matB, result);
		MatrixContainer m1 = new JOMLMatrixContainer(matC);
		MatrixContainer m2 = new JOMLMatrixContainer(result);
		if (m1.equals(m2)) {
			return true;
		} else {
			System.out.println(matC);
			System.out.println(result);
			return false;
		}
	}
	
	private static Matrix3f toMatrix(MatrixContainer container) {
		float a = container.getA(), b = container.getB(), c = container.getC(),
				d = container.getD(), e = container.getE(), f = container.getF(); 
		return new Matrix3f(a, b, 0, c, d, 0, e, f, 1);
	}
	
	public static boolean test1() {
		MatrixContainer a = new JOMLMatrixContainer();
		MatrixContainer b = new JOMLMatrixContainer();
		MatrixContainer c = new JOMLMatrixContainer();
		a.set(1, 0, 0, 1, 0, 0);
		c.set(1, 0, 0, 1, 0, 0);
		return test(a, b, c);
	}
	
	public static boolean test2() {
		JOMLMatrixContainer a = new JOMLMatrixContainer();
		JOMLMatrixContainer b = new JOMLMatrixContainer();
		JOMLMatrixContainer c = new JOMLMatrixContainer();
		a.matrix.scale(0.5f);
		return test(a, b, c);
	}
	
	public static boolean test3() {
		JOMLMatrixContainer a = new JOMLMatrixContainer();
		JOMLMatrixContainer b = new JOMLMatrixContainer();
		JOMLMatrixContainer c = new JOMLMatrixContainer();
		a.matrix.rotateX((float) Math.PI);
		return test(a, b, c);
	}
	
	public static boolean test4() {
		JOMLMatrixContainer a = new JOMLMatrixContainer();
		JOMLMatrixContainer b = new JOMLMatrixContainer();
		JOMLMatrixContainer c = new JOMLMatrixContainer();
		a.matrix.rotateX((float) Math.PI).scale(7);
		return test(a, b, c);
	}
	
	public static boolean test5() {
		JOMLMatrixContainer a = new JOMLMatrixContainer();
		JOMLMatrixContainer b = new JOMLMatrixContainer();
		JOMLMatrixContainer c = new JOMLMatrixContainer();
		a.matrix.identity().rotateX((float) Math.PI * (float) Math.random()).scale(12).rotateY((float) Math.PI * (float) Math.random()).rotateZ((float) Math.PI * (float) Math.random());
		return test(a, b, c);
	}
	
	public static boolean test6() {
		int n = 100;
		JOMLMatrixContainer a = new JOMLMatrixContainer();
		JOMLMatrixContainer b = new JOMLMatrixContainer();
		JOMLMatrixContainer c = new JOMLMatrixContainer();
		a.matrix.rotateX((float) Math.PI);
		a.matrix.scale(12);
		a.matrix.rotateY((float) Math.PI);
		a.matrix.rotateZ((float) Math.PI);
		Matrix3f matB = new Matrix3f();
		long t0 = System.nanoTime();
		for (int i = 0; i < n; i++)
			MatrixMath.solveForB(a, b, c);
		long t1 = System.nanoTime();
		for (int i = 0; i < n; i++) {
			a.matrix.invert(matB);
			matB.mul(c.matrix);
		}
		long t2 = System.nanoTime();
		System.out.println((t1 - t0)/n + "ns");
		System.out.println((t2 - t1)/n + "ns");
		return true;
	}
	
	static class JOMLMatrixContainer implements MatrixContainer {

		Matrix3f matrix = new Matrix3f();
		
		public JOMLMatrixContainer() {
			
		}
		
		public JOMLMatrixContainer(Matrix3f matrix) {
			this.matrix.set(matrix);
		}
		
		@Override
		public void set(float a, float b, float c, float d, float e, float f) {
			matrix.set(new float[]{ a, b, 0, c, d, 0, e, f, 1 });
		}

		@Override
		public float getA() {
			return matrix.m00;
		}

		@Override
		public float getB() {
			return matrix.m01;
		}

		@Override
		public float getC() {
			return matrix.m10;
		}

		@Override
		public float getD() {
			return matrix.m11;
		}

		@Override
		public float getE() {
			return matrix.m20;
		}

		@Override
		public float getF() {
			return matrix.m21;
		}
		
		public boolean equals(Object o) {
			return o instanceof MatrixContainer && equals((MatrixContainer) o);
		}
		
		public float getError(MatrixContainer other) {
			float error = Math.abs(getA() - other.getA()) + Math.abs(getB() - other.getB()) + Math.abs(getC() - other.getC()) +
					Math.abs(getD() - other.getD()) + Math.abs(getE() - other.getE()) + Math.abs(getF() - other.getF());
			return error;
		}
		
		public boolean equals(MatrixContainer other) {
			float error = getError(other);
			System.out.println(error);
			return error < 1e-7f;
		}
			
	}

}
