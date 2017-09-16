package com.digiturtle.math;

public class DefaultMatrixContainer implements MatrixContainer {

	private float a, b, c, d, e, f;
	
	@Override
	public void set(float a, float b, float c, float d, float e, float f) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}

	@Override
	public float getA() {
		return a;
	}

	@Override
	public float getB() {
		return b;
	}

	@Override
	public float getC() {
		return c;
	}

	@Override
	public float getD() {
		return d;
	}

	@Override
	public float getE() {
		return e;
	}

	@Override
	public float getF() {
		return f;
	}

}
