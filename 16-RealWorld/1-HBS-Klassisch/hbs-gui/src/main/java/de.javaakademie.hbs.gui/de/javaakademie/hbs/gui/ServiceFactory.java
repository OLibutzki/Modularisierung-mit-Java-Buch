package de.javaakademie.hbs.gui;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.api.annotation.Bookings;
import de.javaakademie.hbs.api.annotation.Guests;
import de.javaakademie.hbs.api.annotation.Rooms;
import de.javaakademie.hbs.model.Booking;
import de.javaakademie.hbs.model.Guest;
import de.javaakademie.hbs.model.Room;

/**
 * Alternative ServiceFactory.
 * 
 * @author Guido.Oelmann
 */
@SuppressWarnings("unchecked")
public class ServiceFactory {

	private Map<Class<?>, HotelService<?>> services = new HashMap<>();

	public ServiceFactory() {
		ServiceLoader.load(HotelService.class).stream().forEach(provider -> {
			Class<? extends Annotation> annotation = provider.type().getAnnotations()[0].annotationType();
			services.put(annotation, provider.get());
		});
	}

	private HotelService<?> getService(Class<?> annotation) throws ClassNotFoundException {
		HotelService<?> service = services.get(annotation);
		if (service != null) {
			return service;
		} else {
			throw new ClassNotFoundException(annotation.getName() + "Service not found.");
		}
	}

	public HotelService<Room> getRoomService() throws ClassNotFoundException {
		return (HotelService<Room>) getService(Rooms.class);
	}

	public HotelService<Guest> getGuestService() throws ClassNotFoundException {
		return (HotelService<Guest>) getService(Guests.class);
	}

	public HotelService<Booking> getBookingService() throws ClassNotFoundException {
		return (HotelService<Booking>) getService(Bookings.class);
	}

}
