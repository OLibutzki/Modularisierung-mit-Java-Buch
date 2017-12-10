package de.javaakademie.hbs.gui;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

import com.google.gson.Gson;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.api.annotation.Bookings;
import de.javaakademie.hbs.api.annotation.Guests;
import de.javaakademie.hbs.api.annotation.Rooms;
import de.javaakademie.hbs.gui.model.Booking;
import de.javaakademie.hbs.gui.model.Guest;
import de.javaakademie.hbs.gui.model.Room;

public class HotelDataService {

	private Map<Class<?>, HotelService> services = new HashMap<>();

	private Gson gson;

	public HotelDataService() {
		this.gson = new Gson();
		ServiceLoader.load(HotelService.class).stream().forEach(provider -> {
			Class<? extends Annotation> annotation = provider.type().getAnnotations()[0].annotationType();
			services.put(annotation, provider.get());
		});
	}

	private HotelService getService(Class<?> modelObj) {
		HotelService hotelService = null;
		if (modelObj == Booking.class) {
			hotelService = getServiceByAnnotation(Bookings.class);
		} else if (modelObj == Guest.class) {
			hotelService = getServiceByAnnotation(Guests.class);
		} else if (modelObj == Room.class) {
			hotelService = getServiceByAnnotation(Rooms.class);
		}
		return hotelService;
	}

	private HotelService getServiceByAnnotation(Class<?> annotation) {
		HotelService service = services.get(annotation);
		if (service != null) {
			return service;
		} else {
			System.out.println(annotation.getName() + "Service not found.");
			return null;
		}
	}

	public <T> List<T> getAll(Class<T> clazz) {
		if (clazz == null) {
			System.out.println("HotelDataService.getAll() : Parameter was null.");
			return Collections.emptyList();
		}
		HotelService hotelService = getService(clazz);
		if (hotelService != null) {
			String json = hotelService.getAll();
			if (json != null && !json.isEmpty()) {
				// https://stackoverflow.com/questions/20773850/gson-typetoken-with-dynamic-arraylist-item-type
				// https://static.javadoc.io/com.google.code.gson/gson/1.7.2/com/google/gson/internal/$Gson$Types.html
				Type itemListType = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null,
						ArrayList.class, clazz);
				ArrayList<T> objs = gson.fromJson(json, itemListType);
				return objs;
			}
		}
		return Collections.emptyList();
	}

	public <T> Optional<T> get(String id, Class<T> clazz) {
		if (id == null || clazz == null) {
			System.out.println("HotelDataService.get() : Parameter was null.");
			return Optional.empty();
		}
		HotelService hotelService = getService(clazz);
		if (hotelService != null) {
			String json = hotelService.get(id);
			if (json != null && !json.isEmpty()) {
				T obj = gson.fromJson(json, clazz);
				return Optional.of(obj);
			}
		}
		return Optional.empty();
	}

	public void update(Object obj) {
		if (obj == null) {
			System.out.println("HotelDataService.update() : Parameter was null.");
			return;
		}
		HotelService hotelService = getService(obj.getClass());
		if (hotelService != null) {
			String json = gson.toJson(obj);
			hotelService.update(json);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> Optional<T> persist(T obj) {
		if (obj == null) {
			System.out.println("HotelDataService.persist() : Parameter was null.");
			return Optional.empty();
		}
		HotelService hotelService = getService(obj.getClass());
		if (hotelService != null) {
			String json = gson.toJson(obj);
			String jsonPersisted = hotelService.persist(json);
			if (jsonPersisted != null && !jsonPersisted.isEmpty()) {
				T objPers = (T) gson.fromJson(jsonPersisted, obj.getClass());
				return Optional.of(objPers);
			}
		}
		return Optional.empty();
	}

	public void remove(Object obj) {
		if (obj == null) {
			System.out.println("HotelDataService.remove() : Parameter was null.");
			return;
		}
		HotelService hotelService = getService(obj.getClass());
		if (hotelService != null) {
			String json = gson.toJson(obj);
			hotelService.remove(json);
		}
	}

}
