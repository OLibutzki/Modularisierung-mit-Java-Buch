package de.javaakademie.hbs.rooms.service;

import java.util.Collection;
import java.util.Optional;

import com.google.gson.Gson;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.api.annotation.Rooms;
import de.javaakademie.hbs.rooms.model.Room;
import de.javaakademie.hbs.rooms.persistence.RoomDAO;

/**
 * RoomService.
 * 
 * @author Guido.Oelmann
 */
@Rooms
public class RoomService implements HotelService {

	private RoomDAO roomDAO;

	private Gson gson;

	public RoomService() {
		this.roomDAO = new RoomDAO();
		this.gson = new Gson();
	}

	public String getAll() {
		Collection<Room> rooms = roomDAO.getEntities();
		if (rooms.size() > 0) {
			return gson.toJson(rooms);
		} else {
			return "";
		}
	}

	public String get(String id) {
		Optional<Room> roomOpt = roomDAO.get(id);
		if (roomOpt.isPresent()) {
			return gson.toJson(roomOpt.get());
		}
		return "";
	}

	public void update(String room) {
		Room roomObj = gson.fromJson(room, Room.class);
		roomDAO.update(roomObj);
	}

	public String persist(String room) {
		Room roomObj = gson.fromJson(room, Room.class);
		Optional<Room> roomOpt = roomDAO.persist(roomObj);
		if (roomOpt.isPresent()) {
			return gson.toJson(roomOpt.get());
		} else {
			return "";
		}
	}

	public void remove(String room) {
		Room roomObj = gson.fromJson(room, Room.class);
		roomDAO.remove(roomObj);
	}

}
