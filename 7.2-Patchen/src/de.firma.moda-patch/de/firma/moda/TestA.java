package de.firma.moda;

public class TestA {
   
	public static String getInfo() {
      return "Klasse gepatched und " + TestB.getInfo();
   }
   
}
