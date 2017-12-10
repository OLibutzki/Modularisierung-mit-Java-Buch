package test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.firma.whitebox.internal.MathUtils;

public class MathTest {

	@Test
	public void testMax(){
		System.out.println("max(3,6,8,2,5) = " + MathUtils.max(3,6,8,2,5));
		assertEquals((int) MathUtils.max(3,6,8,2,5), 8);
	}

}
