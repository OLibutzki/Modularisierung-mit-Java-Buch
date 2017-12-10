package de.javaakademie.hbs.services;

import de.javaakademie.hbs.api.annotation.Guests;
import de.javaakademie.hbs.model.Guest;
import de.javaakademie.hbs.persistence.GuestDAO;

/**
 * GuestService.
 * 
 * @author Guido.Oelmann
 */
@Guests
public class GuestService extends AbstractService<GuestDAO, Guest> {

	public GuestService() {
		this.hotelDAO = new GuestDAO();
	}

}
