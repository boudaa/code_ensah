package lock_condition_examples;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample1 {

	private Lock lock = new ReentrantLock();

	private Condition soldeSuffisantCondition = lock.newCondition();

	private Compte[] banque = new Compte[] { new Compte(0, 2000), new Compte(1, 2000000), new Compte(2, 2000) };

	public static void main(String[] args) {
		ConditionExample1 bank = new ConditionExample1();
		Thread th1 = new Thread(bank.new TransferRunnable(bank, 0, 1));
		Thread th2 = new Thread(bank.new TransferRunnable(bank, 1, 0));

		th1.start();
		th2.start();

	}

	class TransferRunnable implements Runnable {

		private ConditionExample1 banque;
		int from, to;

		public TransferRunnable(ConditionExample1 banque, int pFrom, int pTo) {
			this.banque = banque;
			from = pFrom;
			to = pTo;
		}

		public void run() {

			banque.transfer(from, to, 4000);

		}
	}

	public void transfer(int from, int to, double montant)

	{
		lock.lock();

		System.out.println(" Le thread en exec = " + Thread.currentThread().getName());

		// on teste si le solde est suffisant

		while (banque[from].getSolde() < montant) {

			System.out.println(Thread.currentThread().getName() + " : solde insuffisant");

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// on passe à l'attente
			try {
				System.out.println("On passe en attente");
				soldeSuffisantCondition.await();
				System.out.println(" Le thread  " + Thread.currentThread().getName() + " s'est réveiller");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			banque[from].setSolde(banque[from].getSolde() - montant);
			banque[to].setSolde(banque[to].getSolde() + montant);

			System.out.println("Transfert fait ; alors notifier les autres threads");
			System.out.println("Les comptes  " + banque[from] + " et " + banque[to]);
			// notifier les autres threads qui sont en attente de cette condition
			soldeSuffisantCondition.signalAll();

		} finally {
			lock.unlock();
		}

	}

}
