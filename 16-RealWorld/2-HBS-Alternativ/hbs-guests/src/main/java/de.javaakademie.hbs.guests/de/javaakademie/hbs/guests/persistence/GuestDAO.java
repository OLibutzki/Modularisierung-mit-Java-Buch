package de.javaakademie.hbs.guests.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import de.javaakademie.hbs.guests.model.Guest;

/**
 * GuestDAO.
 * 
 * @author Guido.Oelmann
 */
public class GuestDAO {

	private final HashMap<String, Guest> guests = new HashMap<>();

	public Collection<Guest> getEntities() {
		return guests.values();
	}

	public Optional<Guest> persist(Guest guest) {
		String id = UUID.randomUUID().toString();
		guest.setId(id);
		this.guests.put(id, guest);

		return Optional.of(guest);
	}

	public void remove(Guest guest) {
		this.guests.remove(guest.getId());
	}

	public Guest update(Guest guest) {
		if (!this.guests.keySet().contains(guest.getId())) {
			throw new IllegalArgumentException("Guest not found " + guest.getId());
		}
		return this.guests.put(guest.getId(), guest);
	}

	public Optional<Guest> get(String id) {
		if (this.guests.containsKey(id)) {
			return Optional.of(this.guests.get(id));
		}
		return Optional.empty();
	}

}