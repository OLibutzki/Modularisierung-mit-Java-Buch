module de.javaakademie.guests {
	requires spark.core;
	
	requires java.sql;
	requires gson;
	opens de.javaakademie.guests.model to gson;
	opens de.javaakademie.guests.rest to gson;
}