package solution;

public class WorkerThread extends Thread {
	private int row;
	private int col;
	private double[][] A;
	private double[][] B;
	private double[][] C;

	public WorkerThread(int row, int col, double[][] A, double[][] B, double[][] C) {
		this.row = row;
		this.col = col;
		this.A = A;
		this.B = B;
		this.C = C;
	}

	public void run() {

		double s = 0;
		for (int i = 0; i < A[0].length; i++) {
			s += A[row][i] * B[i][col];
		}

		C[row][col] = s;

	}
}