package de.javaakademie.hbs.gui.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Room.
 * 
 * @author Guido.Oelmann
 */
public class Room {

	private String id;

	private int floor;

	private int number;

	private RoomType type;

	private String picture;

	private double price;

	private Map<Date, Long> daysReserved = new HashMap<Date, Long>();

	public Room() {
	}

	public Room(int floor, int number, RoomType type, double price) {
		this.floor = floor;
		this.number = number;
		this.type = type;
		this.price = price;
	}

	public Room(int floor, int number, RoomType type, double price, String picture) {
		this.floor = floor;
		this.number = number;
		this.type = type;
		this.price = price;
		this.picture = picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Map<Date, Long> getDays_reserved() {
		return daysReserved;
	}

	public void setDays_reserved(Map<Date, Long> daysReserved) {
		this.daysReserved = daysReserved;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}