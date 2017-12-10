package de.javaakademie.hbs.gui;

import java.util.Optional;
import java.util.ServiceLoader;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.api.annotation.Bookings;
import de.javaakademie.hbs.api.annotation.Guests;
import de.javaakademie.hbs.api.annotation.Rooms;
import de.javaakademie.hbs.model.Booking;
import de.javaakademie.hbs.model.Guest;
import de.javaakademie.hbs.model.Room;

/**
 * ServiceFactory.
 * 
 * @author Guido.Oelmann
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ServiceFactory2 {

	private ServiceLoader<HotelService> services = ServiceLoader.load(HotelService.class);

	private HotelService<?> getServiceByAnnotation(Class annotation) throws ClassNotFoundException {
		Optional<HotelService> service = services.stream()
				.filter(provider -> provider.type().isAnnotationPresent(annotation)).map(ServiceLoader.Provider::get)
				.findFirst();
		services.reload();
		if (service.isPresent()) {
			return service.get();
		} else {
			throw new ClassNotFoundException(annotation.getName() + "Service not found.");
		}
	}

	public HotelService<Room> getRoomService() throws ClassNotFoundException {
		return (HotelService<Room>) getServiceByAnnotation(Rooms.class);
	}

	public HotelService<Guest> getGuestService() throws ClassNotFoundException {
		return (HotelService<Guest>) getServiceByAnnotation(Guests.class);
	}

	public HotelService<Booking> getBookingService() throws ClassNotFoundException {
		return (HotelService<Booking>) getServiceByAnnotation(Bookings.class);
	}

}
