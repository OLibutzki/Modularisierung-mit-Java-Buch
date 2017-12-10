module de.javaakademie.hbs.guests {
	requires de.javaakademie.hbs.api;
	provides de.javaakademie.hbs.api.HotelService with de.javaakademie.hbs.guests.service.GuestService;

	requires gson;
	requires java.sql;
	opens de.javaakademie.hbs.guests.model to gson;
}