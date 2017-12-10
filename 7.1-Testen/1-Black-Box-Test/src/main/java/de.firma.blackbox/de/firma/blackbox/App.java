package de.firma.blackbox;

import org.apache.commons.lang3.SystemUtils;

import de.firma.blackbox.internal.MathUtils;

public class App {
    public static void main(String[] args) {
    	System.out.println("max(3,6,8,2,5) = " + MathUtils.max(3,6,8,2,5));
    }
	
	public static double getMax(double... array) {
	    return MathUtils.max(array);
	}
}
