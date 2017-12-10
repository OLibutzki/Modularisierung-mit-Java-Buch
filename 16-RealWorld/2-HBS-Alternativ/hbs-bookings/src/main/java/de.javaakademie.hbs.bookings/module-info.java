module de.javaakademie.hbs.bookings {
	requires de.javaakademie.hbs.api;
	provides de.javaakademie.hbs.api.HotelService with de.javaakademie.hbs.bookings.service.BookingService;

	requires gson;
	requires java.sql;
	opens de.javaakademie.hbs.bookings.model to gson;	
}