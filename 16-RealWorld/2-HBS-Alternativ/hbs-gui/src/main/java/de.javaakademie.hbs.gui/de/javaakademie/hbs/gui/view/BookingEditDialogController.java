package de.javaakademie.hbs.gui.view;

import de.javaakademie.hbs.gui.model.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

/**
 * Dialog to edit details of a booking.
 * 
 * @author Guido.Oelmann
 */
public class BookingEditDialogController extends AbstractDialog {

	@FXML
	private DatePicker fromField;
	@FXML
	private DatePicker toField;
	@FXML
	private ChoiceBox guestField;
	@FXML
	private ChoiceBox roomField;

	private Booking booking;
	private boolean newBooking = true;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the booking to be edited in the dialog.
	 * 
	 * @param booking
	 */
	@Override
	public void setItem(Object item) {
		if (item == null) {
			return;
		}
		this.booking = (Booking) item;
		this.newBooking = false;
		// TODO
		// firstNameField.setText(guest.getFirstName());
		// lastNameField.setText(guest.getLastName());
		// streetField.setText(guest.getStreet());
		// postalCodeField.setText(Integer.toString(guest.getPostalCode()));
		// cityField.setText(guest.getCity());
		// birthdayField.setText(DateUtil.format(guest.getBirthday()));
		// birthdayField.setPromptText("dd.mm.yyyy");
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (newBooking) {
			booking = new Booking();
		}
		// TODO
		// guest.setFirstName(firstNameField.getText());
		// guest.setLastName(lastNameField.getText());
		// guest.setStreet(streetField.getText());
		// guest.setPostalCode(Integer.parseInt(postalCodeField.getText()));
		// guest.setCity(cityField.getText());
		// guest.setBirthday(DateUtil.parse(birthdayField.getText()));

		if (newBooking) {
			app.getHotelDataService().persist(booking);
		} else {
			app.getHotelDataService().update(booking);
		}
		okClicked = true;
		dialogStage.close();
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}