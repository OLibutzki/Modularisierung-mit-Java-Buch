package de.javaakademie.hbs.bookings.service;

import java.util.Collection;
import java.util.Optional;

import com.google.gson.Gson;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.api.annotation.Bookings;
import de.javaakademie.hbs.bookings.model.Booking;
import de.javaakademie.hbs.bookings.persistence.BookingDAO;

/**
 * BookingService.
 * 
 * @author Guido.Oelmann
 */
@Bookings
public class BookingService implements HotelService {

	private BookingDAO bookingDAO;

	private Gson gson;

	public BookingService() {
		this.bookingDAO = new BookingDAO();
		this.gson = new Gson();
	}

	public String getAll() {
		Collection<Booking> guests = bookingDAO.getEntities();
		if (guests.size() > 0) {
			return gson.toJson(guests);
		} else {
			return "";
		}
	}

	public String get(String id) {
		Optional<Booking> guestOpt = bookingDAO.get(id);
		if (guestOpt.isPresent()) {
			return gson.toJson(guestOpt.get());
		}
		return "";
	}

	public void update(String guest) {
		Booking guestObj = gson.fromJson(guest, Booking.class);
		bookingDAO.update(guestObj);
	}

	public String persist(String guest) {
		Booking guestObj = gson.fromJson(guest, Booking.class);
		Optional<Booking> guestOpt = bookingDAO.persist(guestObj);
		if (guestOpt.isPresent()) {
			return gson.toJson(guestOpt.get());
		} else {
			return "";
		}
	}

	public void remove(String guest) {
		Booking guestObj = gson.fromJson(guest, Booking.class);
		bookingDAO.remove(guestObj);
	}
}
