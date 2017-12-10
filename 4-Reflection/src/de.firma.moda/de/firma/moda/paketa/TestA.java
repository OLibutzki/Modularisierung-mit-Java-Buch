package de.firma.moda.paketa;

public class TestA {
   
	public String test(String call) {
        return "TestA.test aufgerufen: " + call;
    }
	
    private String testPrivate(String call) {
        return "TestA.testPrivate aufgerufen: " + call;
    }
   
}
