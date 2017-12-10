package de.firma.modb;

public class TestB {
   
	public String test(String call) {
        return "TestB.test aufgerufen: " + call;
    }
	
    private String testPrivate(String call) {
        return "TestB.testPrivate aufgerufen: " + call;
    }
   
}
