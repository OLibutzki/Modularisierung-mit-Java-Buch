module de.javaakademie.hbs.services {
	exports de.javaakademie.hbs.services;
	requires de.javaakademie.hbs.model;
	requires de.javaakademie.hbs.api;
	requires de.javaakademie.hbs.persistence;
	provides de.javaakademie.hbs.api.HotelService with de.javaakademie.hbs.services.RoomService,de.javaakademie.hbs.services.GuestService,de.javaakademie.hbs.services.BookingService;
}