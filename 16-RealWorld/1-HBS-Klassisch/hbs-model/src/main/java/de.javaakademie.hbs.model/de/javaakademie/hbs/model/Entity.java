package de.javaakademie.hbs.model;

/**
 * Entity.
 * 
 * @author Guido.Oelmann
 */
public abstract class Entity {

	protected String id;

	public Entity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
