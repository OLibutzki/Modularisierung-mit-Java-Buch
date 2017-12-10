package de.javaakademie.hbs.guests.service;

import java.util.Collection;
import java.util.Optional;

import com.google.gson.Gson;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.api.annotation.Guests;
import de.javaakademie.hbs.guests.model.Guest;
import de.javaakademie.hbs.guests.persistence.GuestDAO;

/**
 * GuestService.
 * 
 * @author Guido.Oelmann
 */
@Guests
public class GuestService implements HotelService {

	private GuestDAO guestDAO;

	private Gson gson;

	public GuestService() {
		this.guestDAO = new GuestDAO();
		this.gson = new Gson();
	}

	public String getAll() {
		Collection<Guest> guests = guestDAO.getEntities();
		if (guests.size() > 0) {
			return gson.toJson(guests);
		} else {
			return "";
		}
	}

	public String get(String id) {
		Optional<Guest> guestOpt = guestDAO.get(id);
		if (guestOpt.isPresent()) {
			return gson.toJson(guestOpt.get());
		}
		return "";
	}

	public void update(String guest) {
		Guest guestObj = gson.fromJson(guest, Guest.class);
		guestDAO.update(guestObj);
	}

	public String persist(String guest) {
		Guest guestObj = gson.fromJson(guest, Guest.class);
		Optional<Guest> guestOpt = guestDAO.persist(guestObj);
		if (guestOpt.isPresent()) {
			return gson.toJson(guestOpt.get());
		} else {
			return "";
		}
	}

	public void remove(String guest) {
		Guest guestObj = gson.fromJson(guest, Guest.class);
		guestDAO.remove(guestObj);
	}
}
