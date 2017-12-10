package de.javaakademie.hbs.services;

import de.javaakademie.hbs.api.annotation.Bookings;
import de.javaakademie.hbs.model.Booking;
import de.javaakademie.hbs.persistence.BookingDAO;

/**
 * BookingService.
 * 
 * @author Guido.Oelmann
 */
@Bookings
public class BookingService extends AbstractService<BookingDAO, Booking> {

	public BookingService() {
		this.hotelDAO = new BookingDAO();
	}

}
