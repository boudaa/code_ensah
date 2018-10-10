package com.ensah;
public class CodeSource implements Livrable {

	private String code;

	private String langage;

	public CodeSource(String code, String langage) {
		this.code = code;
		this.langage = langage;
	}

	public void print() {

		System.out.println("Code source");

		System.out.println(code);

	}

}
