package de.javaakademie.hbs.bookings.model;

/**
 * RoomType.
 * 
 * @author Guido.Oelmann
 */
public enum RoomType {
	STANDARD("",""), COMFORT("",""), SUITE("","");
	private String name;
	private String description;
	private RoomType(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}