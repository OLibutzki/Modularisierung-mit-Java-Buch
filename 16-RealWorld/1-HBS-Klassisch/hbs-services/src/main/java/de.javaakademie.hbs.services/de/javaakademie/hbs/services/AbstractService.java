package de.javaakademie.hbs.services;

import java.util.Collection;
import java.util.Optional;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.model.Entity;
import de.javaakademie.hbs.persistence.HotelDAO;

/**
 * AbstractService.
 * 
 * @author Guido.Oelmann
 */
public abstract class AbstractService<T extends HotelDAO<P>, P extends Entity> implements HotelService<P> {

	protected T hotelDAO;

	public AbstractService() {
	}

	public T getHotelDAO() {
		return hotelDAO;
	}

	public Collection<P> getAll() {
		return hotelDAO.getEntities();
	}

	@Override
	public Optional<?> get(String id) {
		return hotelDAO.get(id);
	}

	@Override
	public void update(P item) {
		hotelDAO.update(item);
	}

	@Override
	public Optional<?> persist(P item) {
		return hotelDAO.persist(item);
	}

	@Override
	public void remove(P item) {
		hotelDAO.remove(item);
	}

}
