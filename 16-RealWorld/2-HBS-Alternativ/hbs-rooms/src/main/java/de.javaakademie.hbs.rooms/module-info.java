module de.javaakademie.hbs.rooms {
	requires de.javaakademie.hbs.api;
	provides de.javaakademie.hbs.api.HotelService with de.javaakademie.hbs.rooms.service.RoomService;
	
	requires gson;
	requires java.sql;
	opens de.javaakademie.hbs.rooms.model to gson;
}