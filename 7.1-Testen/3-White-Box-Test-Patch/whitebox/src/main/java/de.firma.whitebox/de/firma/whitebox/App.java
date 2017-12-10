package de.firma.whitebox;

import org.apache.commons.lang3.SystemUtils;

import de.firma.whitebox.internal.MathUtils;

public class App {
    public static void main(String[] args) {
    	System.out.println("User Dir: " + SystemUtils.getUserDir());
    	System.out.println("User Home: " + SystemUtils.getUserHome());
    	
    	System.out.println("max(3,6,8,2,5) = " + MathUtils.max(3,6,8,2,5));
    }
	
	public static double getMax(double... array) {
	    return MathUtils.max(array);
	}
}
