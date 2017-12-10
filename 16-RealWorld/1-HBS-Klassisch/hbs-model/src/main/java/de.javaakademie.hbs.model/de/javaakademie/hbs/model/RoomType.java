package de.javaakademie.hbs.model;

/**
 * RoomType.
 * 
 * @author Guido.Oelmann
 */
public enum RoomType {

	STANDARD("Standard", "Tolles Standard-Zimmer."), COMFORT("Komfort", "Prima Komfort-Zimmer."), SUITE("Suite",
			"Luxuriöse Suite.");

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