package de.javaakademie.hbs.api;

/**
 * HotelService.
 * 
 * @author Guido.Oelmann
 */
public interface HotelService {

	public String getAll();

	public String get(String id);

	public void update(String item);

	public String persist(String item);

	public void remove(String item);
}
