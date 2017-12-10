package de.javaakademie.hbs.rooms.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import de.javaakademie.hbs.rooms.model.Room;

/**
 * RoomDAO.
 * 
 * @author Guido.Oelmann
 */
public class RoomDAO {

	private final HashMap<String, Room> rooms = new HashMap<>();

	public Collection<Room> getEntities() {
		return rooms.values();
	}

	public Optional<Room> persist(Room room) {
		String id = UUID.randomUUID().toString();
		room.setId(id);
		this.rooms.put(id, room);

		return Optional.of(room);
	}

	public void remove(Room room) {
		this.rooms.remove(room.getId());
	}

	public Room update(Room room) {
		if (!this.rooms.keySet().contains(room.getId())) {
			throw new IllegalArgumentException("Room not found " + room.getId());
		}
		return this.rooms.put(room.getId(), room);
	}

	public Optional<Room> get(String id) {
		if (this.rooms.containsKey(id)) {
			return Optional.of(this.rooms.get(id));
		}
		return Optional.empty();
	}

}