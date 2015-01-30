package yape.math;


public class LongMatrix {

	private int m;
	private int n;
	private long[][] A;

	public LongMatrix(int n, int m) {
		this.m = m;
		this.n = n;
		A = new long[m][n];
	}

	public void set(int i, int j, long value) {
		A[i][j] = value;
	}

	public int getRowDimension() {
		return m;
	}

	public int getColumnDimension() {
		return n;
	}

	public static LongMatrix makeIdentity(int size) {
		LongMatrix result = new LongMatrix(size, size);
		for (int i = 0; i < size; i++) {
			result.A[i][i] = 1;
		}
		return result;
	}

	public long get(int i, int j) {
		return A[i][j];
	}

	public LongMatrix times(LongMatrix B) {
		if (B.m != n) {
			throw new IllegalArgumentException("Matrix inner dimensions must agree.");
		}
		LongMatrix X = new LongMatrix(m, B.n);
		long[][] C = X.getArray();
		long[] Bcolj = new long[n];
		for (int j = 0; j < B.n; j++) {
			// Believe it or not, it's faster to copy this column each time than it is to
			// refer directly to B.A
			for (int k = 0; k < n; k++) {
				Bcolj[k] = B.A[k][j];
			}
			for (int i = 0; i < m; i++) {
				long[] Arowi = A[i];
				long s = 0;
				for (int k = 0; k < n; k++) {
					s += Arowi[k] * Bcolj[k];
				}
				C[i][j] = s;
			}
		}
		return X;
	}

	public long[][] getArray() {
		return A;
	}
}
