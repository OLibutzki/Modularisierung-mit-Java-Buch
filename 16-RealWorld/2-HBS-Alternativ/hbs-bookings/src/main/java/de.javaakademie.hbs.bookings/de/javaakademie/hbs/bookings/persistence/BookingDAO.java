package de.javaakademie.hbs.bookings.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import de.javaakademie.hbs.bookings.model.Booking;

/**
 * BookingDAO.
 * 
 * @author Guido.Oelmann
 */
public class BookingDAO {

	private final HashMap<String, Booking> bookings = new HashMap<>();

	public Collection<Booking> getEntities() {
		return bookings.values();
	}

	public Optional<Booking> persist(Booking booking) {
		String id = UUID.randomUUID().toString();
		booking.setId(id);
		this.bookings.put(id, booking);

		return Optional.of(booking);
	}

	public void remove(Booking booking) {
		this.bookings.remove(booking.getId());
	}

	public Booking update(Booking booking) {
		if (!this.bookings.keySet().contains(booking.getId())) {
			throw new IllegalArgumentException("Booking not found " + booking.getId());
		}
		return this.bookings.put(booking.getId(), booking);
	}

	public Optional<Booking> get(String id) {
		if (this.bookings.containsKey(id)) {
			return Optional.of(this.bookings.get(id));
		}
		return Optional.empty();
	}

}
