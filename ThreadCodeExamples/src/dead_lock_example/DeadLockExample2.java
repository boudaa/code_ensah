package dead_lock_example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample2 {

	private Lock lock = new ReentrantLock();

	private Condition soldeSuffisantCondition = lock.newCondition();

	private Compte[] banque = new Compte[] { new Compte(0, 200), new Compte(1, 300) };

	public static void main(String[] args) {
		DeadLockExample2 bank = new DeadLockExample2();
		Thread th1 = new Thread(bank.new TransferRunnable(bank, 0, 1, 300));
		Thread th2 = new Thread(bank.new TransferRunnable(bank, 1, 0, 400));

		th1.start();
		th2.start();

	}

	class TransferRunnable implements Runnable {

		private DeadLockExample2 banque;
		int from, to;
		double montant;

		public TransferRunnable(DeadLockExample2 banque, int pFrom, int pTo, double pMontant) {
			this.banque = banque;
			from = pFrom;
			to = pTo;
			montant = pMontant;
		}

		public void run() {

			banque.transfer(from, to, montant);

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
				System.out.println("On passe en attente pour 3 secondes");
				
				soldeSuffisantCondition.await(3, TimeUnit.SECONDS);
				
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
