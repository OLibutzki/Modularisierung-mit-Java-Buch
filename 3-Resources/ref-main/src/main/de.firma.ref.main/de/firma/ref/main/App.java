package de.firma.ref.main;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

public class App {

	public App() {
	}

	public static void main(String[] args) throws Exception {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Zugriff auf die Ressourcen innerhalb des gleichen Moduls über URLs");
		System.out.println("-------------------------------------------------------------------------------");
		accessResourceInsideModuleWithURLs();

		System.out.println("");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Zugriff auf die Ressourcen innerhalb des gleichen Moduls");
		System.out.println("-------------------------------------------------------------------------------");
		accessResourceInsideModule();

		System.out.println("");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Zugriffe auf die Ressourcen eines anderen Moduls");
		System.out.println("-------------------------------------------------------------------------------");
		accessResourceOutsideModule();
	}

	private static void accessResourceInsideModuleWithURLs() throws Exception {
		// funktioniert
		URL url = App.class.getResource("App.class");

		// fuktioniert nicht, weil App und Object in verschiedenen Modulen liegt
		url = App.class.getResource("/java/lang/Object.class");

		// funktioniert, weil Object und Class beide im gleichen Modul java.base liegen
		url = Object.class.getResource("/java/lang/Module.class");

		// funktioniert
		String targetResource = "/META-INF/test.properties";
		url = App.class.getResource(targetResource);
		InputStream inputStream = url.openStream();
		printProperties(targetResource, inputStream);

		// funktioniert
		targetResource = "/de/firma/ref/main/resources/test.properties";
		url = App.class.getResource(targetResource);
		inputStream = url.openStream();
		printProperties(targetResource, inputStream);

		// funktioniert
		targetResource = "META-INF/test.properties";
		url = App.class.getClassLoader().getResource(targetResource);
		inputStream = url.openStream();
		printProperties(targetResource, inputStream);

	}

	/**
	 * Zugriff auf Ressourcen innnerhalb des Moduls.
	 */
	private static void accessResourceInsideModule() throws Exception {
		String targetResource = "META-INF/test.properties";
		InputStream inputStream = App.class.getModule().getResourceAsStream(targetResource);
		printProperties(targetResource, inputStream);

		targetResource = "de/firma/ref/main/resources/test.properties";
		inputStream = App.class.getModule().getResourceAsStream(targetResource);
		printProperties(targetResource, inputStream);

		targetResource = "META-INF/test.properties";
		inputStream = App.class.getClassLoader().getResourceAsStream(targetResource);
		printProperties(targetResource, inputStream);
	}

	/**
	 * Zugriff auf Ressourcen moduluebergreifend.
	 */
	private static void accessResourceOutsideModule() throws Exception {
		ModuleLayer bootLayer = ModuleLayer.boot();
		Optional<Module> moduleOpt = bootLayer.findModule("de.firma.ref.resources");
		if (moduleOpt.isPresent()) {
			Module module = moduleOpt.get();

			String targetResource = "META-INF/test.properties";
			InputStream inputStream = module.getResourceAsStream(targetResource);
			printProperties(targetResource, inputStream);

			// paket nur exported -> kein Zugriff auf .properties
			// aber Zugriff auf .class moeglich
			targetResource = "de/firma/ref/resources/exported/test.properties";
			inputStream = module.getResourceAsStream(targetResource);
			printProperties(targetResource, inputStream);
			inputStream = module.getResourceAsStream("de/firma/ref/resources/exported/TestExports.class");

			// funktioniert
			// opens -> Zugriff moeglich
			targetResource = "de/firma/ref/resources/opened/test.properties";
			inputStream = module.getResourceAsStream(targetResource);
			printProperties(targetResource, inputStream);
			inputStream = module.getResourceAsStream("de/firma/ref/resources/opened/TestOpens.class");

			// Paket weder exported noch opened
			// aber Zugriff auf .class moeglich
			inputStream = module.getResourceAsStream("de/firma/ref/resources/test/TestRoot.class");
		}
	}

	private static void printProperties(String targetResource, InputStream inputStream) throws IOException {
		if (inputStream == null) {
			System.out.println("App.class -> " + targetResource + ": kein Zugriff");
			return;
		}
		Properties properties = new Properties();
		properties.load(inputStream);
		String woher = properties.getProperty("result");
		System.out.println("App.class -> " + targetResource + ": " + woher);
	}
}
