package de.javaakademie.hbs.api;

import java.util.Collection;
import java.util.Optional;

/**
 * HotelService.
 * 
 * @author Guido.Oelmann
 */
public interface HotelService<T> {

	public Collection<T> getAll();

	public Optional<?> get(String id);

	public void update(T item);

	public Optional<?> persist(T item);

	public void remove(T item);
}
