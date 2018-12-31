package race_conditions_example2;


//voir le commentaire dans la méthode incrementX
public class RaceConditionExample {

	private int x = 0;

	public static void main(String[] args) {
		
		
		RaceConditionExample bank = new RaceConditionExample();
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

		private RaceConditionExample banque;

		public TransferRunnable(RaceConditionExample banque) {
			this.banque = banque;
		}

		public void run() {
			while (true) {

				banque.incrementX();
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/// 
	/// Analyser le code et essayer de comprendre pourquoi le programme s'arrete à cause de cette méthode 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void incrementX()

	{
		int var = x;
	
		x += 1;
		
		////////////////////////////////////////////////////////////////////////////////////
		// J'ai juste ajouter ces instructions pour avoir plus de chance d'avoir x=var
		// on supprimant ces instructions je n'obtient pas x=var , ce resultat est aléatoire
		// et peut donc ne pas se produire sur vos machine
		StringBuffer sb = new StringBuffer();
		sb.append("");
		////////////////////////////////////////////////////////////////////////////////////
		
		
		//Normalement  x est toujours supérieur strictement à var, pourtant ce teste est true dans quelques cas !
		if (var == x) {

			System.out.println("Arret de l'execution !!!!! bien que " + "Valeur de var =" + var + "  et x= " + x);
			System.exit(0);
		}
		
		
	}

}
