package race_conditions_example1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceConditionExample_Solution_With_Lock {

	private Lock lock = new ReentrantLock();

	private Compte[] banque = new Compte[] { new Compte(1, 2000), new Compte(2, 2000), new Compte(3, 2000) };

	public static void main(String[] args) {
		RaceConditionExample_Solution_With_Lock bank = new RaceConditionExample_Solution_With_Lock();
		Thread th1 = new Thread(bank.new TransferRunnable(bank));
		Thread th2 = new Thread(bank.new TransferRunnable(bank));
		Thread th3 = new Thread(bank.new TransferRunnable(bank));
		Thread th4 = new Thread(bank.new TransferRunnable(bank));
		Thread th5 = new Thread(bank.new TransferRunnable(bank));
		Thread th6 = new Thread(bank.new TransferRunnable(bank));
		Thread th7 = new Thread(bank.new TransferRunnable(bank));
		Thread th8 = new Thread(bank.new TransferRunnable(bank));

		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		th7.start();
		th8.start();
	}

	class TransferRunnable implements Runnable {

		private RaceConditionExample_Solution_With_Lock banque;

		public TransferRunnable(RaceConditionExample_Solution_With_Lock banque) {
			this.banque = banque;
		}

		public void run() {
			while (true) {

				banque.transfer(0, 1, 20);
				banque.transfer(1, 2, 20);
				banque.transfer(2, 0, 20);
			}
		}
	}

	public  void transfer(int from, int to, double amount)

	{
		lock.lock();

		try {
			StringBuffer sb = new StringBuffer();
			// ATTENTION : cette méthode n’est pas sûre lorsqu’elle est appelée
			// à partir de plusieurs threads
			sb.append(Thread.currentThread() + " : ");
			banque[from].setSolde(banque[from].getSolde() - amount);
			sb.append("  amount=" + amount + " from =" + from + " to =" + to);
			banque[to].setSolde(banque[to].getSolde() + amount);
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sb.append(" Total =" + getSoldeTotal() + "\n");
			System.out.println(sb);
		} finally {
			lock.unlock();
		}
	}

	public double getSoldeTotal() {
		double somme = 0;
		for (Compte it : banque) {
			somme += it.getSolde();
		}

		return somme;
	}

}
