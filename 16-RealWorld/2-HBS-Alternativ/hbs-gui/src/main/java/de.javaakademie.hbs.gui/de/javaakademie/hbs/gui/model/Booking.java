package de.javaakademie.hbs.gui.model;

import java.time.LocalDate;

/**
 * Booking.
 * 
 * @author Guido.Oelmann
 */
public class Booking {

	private String id;

	private LocalDate beginDate, endDate;

	private Guest guest;

	private Room room;

	public Booking() {
	}

	public Booking(LocalDate beginDate, LocalDate endDate, Guest guest, Room room) {
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.guest = guest;
		this.room = room;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}