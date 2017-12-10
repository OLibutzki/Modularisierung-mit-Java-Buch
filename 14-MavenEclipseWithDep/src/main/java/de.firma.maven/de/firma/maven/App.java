package de.firma.maven;

import java.lang.module.ModuleDescriptor;
import java.lang.ModuleLayer;
import java.lang.Module;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class App {

	public static void main(String[] args) {
		System.out.println("Hello " + StringUtils.upperCase("Maven"));
		Set<Module> modules = ModuleLayer.boot().modules();
		modules.stream().filter(module -> (module.getName().contains("de.firma") || module.getName().contains("commons"))).forEach(module -> {
			ModuleDescriptor descriptor = module.getDescriptor();
			if (descriptor.isAutomatic()) {
				System.out.println(module.getName() + " (AUTOMATIC)");
			} else {
				System.out.println(module.getName());
			}
		});
		
	}

}
