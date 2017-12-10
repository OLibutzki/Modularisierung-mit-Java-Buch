package de.firma.moda.internal;

class Internal {
    
	public String test(String call) {
        return "Internal.test aufgerufen: " + call;
    }

    private String testPrivate(String call) {
        return "Internal.testPrivate aufgerufen: " + call;
    }

}