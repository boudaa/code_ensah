package com.ensah;
import java.util.ArrayList;
import java.util.List;

public class Jury {

	/**
	 * Les profs qui constituent le jury
	 */
	private List<Prof> profs = new ArrayList<Prof>();

	public Jury(List<Prof> profs) {
		this.profs = profs;
	}

}
