package de.firma.blackbox.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.firma.blackbox.App;

public class MathTest {

	@Test
	public void testMax(){
		System.out.println("max(3,6,8,2,5) = " + App.getMax(3,6,8,2,5));
		assertEquals((int) App.getMax(3,6,8,2,5), 8);
	}

}
