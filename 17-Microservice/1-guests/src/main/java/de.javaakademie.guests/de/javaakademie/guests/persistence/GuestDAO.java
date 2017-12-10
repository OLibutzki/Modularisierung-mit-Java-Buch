package de.javaakademie.guests.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import de.javaakademie.guests.model.Guest;

/**
 * GuestDAO.
 * 
 * @author Guido.Oelmann
 */
public class GuestDAO {

	private final HashMap<String, Guest> guests = new HashMap<>();

	public GuestDAO() {
		persist(new Guest("Guido ", "Oelmann"));
		persist(new Guest("Rosanne ", "Oelmann"));
	}

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

	public Optional<Guest> update(Guest guest) {
		if (!this.guests.containsKey(guest.getId())) {
			return Optional.empty();
		}
		this.guests.put(guest.getId(), guest);
		return Optional.of(this.guests.get(guest.getId()));
	}

	public Optional<Guest> get(String id) {
		if (this.guests.containsKey(id)) {
			return Optional.of(this.guests.get(id));
		}
		return Optional.empty();
	}

}