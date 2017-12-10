package de.javaakademie.hbs.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import de.javaakademie.hbs.model.Entity;

/**
 * HotelDAO.
 * 
 * @author Guido.Oelmann
 */
public abstract class HotelDAO<T extends Entity> {

	private final HashMap<String, T> entities = new HashMap<>();

	/**
	 * Get all known entities
	 *
	 * @return Set of entities
	 */
	public Collection<T> getEntities() {
		return entities.values();
	}

	/**
	 * Persist the given entity
	 *
	 * @param entity
	 *            T to store
	 * @return T seeded with new ID
	 */
	public Optional<T> persist(T entity) {

		String id = UUID.randomUUID().toString();
		entity.setId(id);

		this.entities.put(id, entity);

		return Optional.of(entity);
	}

	/**
	 * Remove the T for the given ID. Fails silently if the ID is not found.
	 *
	 * @param id
	 *            Valid ID
	 */
	public void remove(T entity) {
		this.entities.remove(entity.getId());
	}

	/**
	 * Update a entities if found
	 *
	 * @param entities
	 *            T to update
	 * @return The found T or null
	 */
	public T update(T entities) {

		if (!this.entities.keySet().contains(entities.getId()))
			throw new IllegalArgumentException("T not found " + entities.getId());

		return this.entities.put(entities.getId(), entities);
	}

	/**
	 * Get the entity that matches the specified ID, or empty.
	 *
	 * @param id
	 *            Valid ID
	 * @return Optional T
	 */
	public Optional<T> get(String id) {

		if (this.entities.containsKey(id))
			return Optional.of(this.entities.get(id));

		return Optional.empty();
	}

}