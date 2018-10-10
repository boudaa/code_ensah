package com.test;

public class A implements  I{
	
	public void afficher() {
		System.out.println("je suis un A");
	}
	
	
	public static void main(String[] args) {
		
		I i =  Factory.getInstance("com.test.A");
		i.afficher();
		
		
	}

}
