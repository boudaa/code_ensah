package race_conditions_example1;

public class Compte {

	int id;
	private double solde;

	public Compte(int id, double solde) {
		this.id = id;
		this.solde = solde;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", solde=" + solde + "]";
	}

}