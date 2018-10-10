package com.test;

public class Test {
	
	public static void main(String[] args) {
		
		I i =  Factory.getInstance("com.test.A");
		i.afficher();
		
		
	}

}
