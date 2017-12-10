package de.firma.modmain;

import de.firma.moda.paketa.TestA;
import java.lang.reflect.Constructor;
import java.lang.ModuleLayer;
import java.lang.reflect.Method;
import java.lang.Module;
import java.util.Optional;

public class App {
   
	public static void main(String[] args) throws Exception {
		
		TestA testA = new TestA();

		// direkter Aufruf: public Methode
		System.out.println(testA.test("direkter Aufruf"));

		// Aufruf per Reflection: public Methode
		Method m = testA.getClass().getMethod("test", String.class);
		System.out.println(m.invoke(testA, "Aufruf per Reflection"));

		// Aufruf per Reflection: private Methode
		m = testA.getClass().getDeclaredMethod("testPrivate", String.class);
		m.setAccessible(true);
		System.out.println(m.invoke(testA, "Aufruf per Reflection"));

		
        // Modul de.firma.modb wurde in den Bootlayer geladen, aber es
		// existiert ueber den Moduldeskriptor von de.firma.modmain 
		// keine Import-Beziehung
		// Modul im Bootlayer suchen
        Optional<Module> optModule = ModuleLayer.boot().findModule("de.firma.modb");
        if (optModule.isPresent()) {
			System.out.println("Modul de.firma.modb gefunden");
			Module modb = optModule.get();

			Class<?> testBClass = modb.getClassLoader().loadClass("de.firma.modb.TestB");
			Constructor<?> constructor = testBClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object testB = constructor.newInstance();

			// Aufruf per Reflection: public Methode
			m = testB.getClass().getMethod("test", String.class);
			m.setAccessible(true);
			System.out.println(m.invoke(testB, "Aufruf per Reflection"));

			// Aufruf per Reflection: private Methode
			m = testB.getClass().getDeclaredMethod("testPrivate", String.class);
			m.setAccessible(true);
			System.out.println(m.invoke(testB, "Aufruf per Reflection"));
		} else {
			System.out.println("Modul de.firma.modb nicht gefunden. Muss mit '--add-modules de.firma.modb' hinzugefuegt werden");
		}
	}

}
