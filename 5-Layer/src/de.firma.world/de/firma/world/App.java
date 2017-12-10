package de.firma.world;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.ModuleLayer;
import java.lang.reflect.Method;
import java.lang.Module;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
    
	private static final String MODULE_NAME = "de.firma.greetings";

	private static final String MODULE_CLASS = "de.firma.greetings.App";

    public static void main(String[] args) {
    	String[] modulePaths = {".\\mod-version-1",".\\mod-version-2"};
    	
		// fuer jeden Modulpfad wird ein neuer ModuleLayer erzeugt
        List<ModuleLayer> layers = Arrays.stream(modulePaths)
                     .map(App::createModuleLayer)
                     .collect(Collectors.toList());
		
		// alle Module sind nun in der gleichen JVM geladen und befinden sich auf verschiedenen ModuleLayern
		// Module werden nun durchlaufen und es wird von jeder Klasse de.firma.greetings.App die
		//  main-Methode aufgerufen
		layers.stream().forEach(App::lookUpAndStartModule);
    }

    /**
     * Erzeugt einen ModuleLayer fuer das Modul de.firma.greetings, welches auf dem uebergebene Modulpfad gefunden werden kann.
	 * Der Bootlayer wird das Parent von dem neu erzeugten ModuleLayer, welches den Zugriff auf Module des Bootlayers erlaubt.
	 
     * @param modulePath path wo das Modul zu finden ist
     */
    private static ModuleLayer createModuleLayer(String modulePath) {
        ModuleFinder finder = ModuleFinder.of(Paths.get(modulePath));
        ModuleLayer parent = ModuleLayer.boot();
        Configuration newCfg = parent.configuration().resolve(finder, ModuleFinder.of(), Set.of(MODULE_NAME));
        return parent.defineModulesWithOneLoader(newCfg, ClassLoader.getSystemClassLoader());
    }
    
	/**
	 * Suchen des Moduls de.firma.greetings auf dem uebergebenen ModuleLayer und Laden der Klasse App.
     * Ausgabe der Modulversion und Ausführung der main-Methode der Klasse App.
	 */
    private static void lookUpAndStartModule(ModuleLayer layer) {
    	System.out.println("-----------------------------------------------");
    	Optional<Module> optMod = layer.findModule(MODULE_NAME);
        if (optMod.isPresent()) {
        	try {
	            Module mod = optMod.get();
	            ModuleDescriptor modDescriptor = mod.getDescriptor();
	            System.out.println(modDescriptor.toNameAndVersion());
	            
	        	Class<?> testBClass = mod.getClassLoader().loadClass(MODULE_CLASS);
	    		Constructor<?> constructor = testBClass.getDeclaredConstructor();
	    		constructor.setAccessible(true);
	    		Object testB = constructor.newInstance();
	
	    		Method mainMethod = testB.getClass().getMethod("main", String[].class);
	    		String[] params = null; 
	    		System.out.println(mainMethod.invoke(null,(Object) params));
        	} catch (Exception e) {
        		System.out.println(e);
        	}
        }
    }
}