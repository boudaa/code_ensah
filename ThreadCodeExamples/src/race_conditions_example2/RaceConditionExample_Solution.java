package race_conditions_example2;

public class RaceConditionExample_Solution {

	private volatile int x = 0;

	public static void main(String[] args) {
		RaceConditionExample_Solution bank = new RaceConditionExample_Solution();
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

		private RaceConditionExample_Solution banque;

		public TransferRunnable(RaceConditionExample_Solution banque) {
			this.banque = banque;
		}

		public void run() {
			while (true) {

				banque.incrementX(2);
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void incrementX(int v)

	{
		int var = x;

		x += v;

		StringBuffer sb = new StringBuffer();
		sb.append("");

		
		if (var == x) {

			System.out.println("Arret de l'execution !!!!! bien que " + "Valeur de var =" + var + "  et x= " + x);
			System.exit(0);
		}

	}

}
