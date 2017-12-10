package de.firma.modmain;

import de.firma.moda.TestA;
import de.firma.modc.TestC;

public class App {
   
	public static void main(String[] args) {
		System.out.println("Name des importierten Moduls A: " + TestA.getName());
		System.out.println("Name des transitiv erreichbaren Moduls C: " + TestC.getName());
   }

}
