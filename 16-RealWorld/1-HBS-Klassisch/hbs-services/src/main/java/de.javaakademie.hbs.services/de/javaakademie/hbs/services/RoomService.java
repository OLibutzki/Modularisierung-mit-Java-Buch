package de.javaakademie.hbs.services;

import de.javaakademie.hbs.api.annotation.Rooms;
import de.javaakademie.hbs.model.Room;
import de.javaakademie.hbs.persistence.RoomDAO;

/**
 * RoomService.
 * 
 * @author Guido.Oelmann
 */
@Rooms
public class RoomService extends AbstractService<RoomDAO, Room> {

	public RoomService() {
		this.hotelDAO = new RoomDAO();
	}

}
