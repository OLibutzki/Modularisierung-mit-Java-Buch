package de.javaakademie.hbs.gui.view;

import de.javaakademie.hbs.gui.model.Guest;
import de.javaakademie.hbs.gui.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * Dialog to edit details of a guest.
 * 
 * @author Guido.Oelmann
 */
public class GuestEditDialogController extends AbstractDialog {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;

	private Guest guest;
	private boolean newGuest = true;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the guest to be edited in the dialog.
	 * 
	 * @param guest
	 */
	@Override
	public void setItem(Object item) {
		if (item == null) {
			return;
		}
		this.guest = (Guest) item;
		this.newGuest = false;

		firstNameField.setText(guest.getFirstName());
		lastNameField.setText(guest.getLastName());
		streetField.setText(guest.getStreet());
		postalCodeField.setText(Integer.toString(guest.getPostalCode()));
		cityField.setText(guest.getCity());
		birthdayField.setText(DateUtil.format(guest.getBirthday()));
		birthdayField.setPromptText("dd.mm.yyyy");
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			if (newGuest) {
				guest = new Guest();
			}
			guest.setFirstName(firstNameField.getText());
			guest.setLastName(lastNameField.getText());
			guest.setStreet(streetField.getText());
			guest.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			guest.setCity(cityField.getText());
			guest.setBirthday(DateUtil.parse(birthdayField.getText()));

			if (newGuest) {
				app.getHotelDataService().persist(guest);
			} else {
				app.getHotelDataService().update(guest);
			}
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "Kein gültiger erster Name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "Kein gültiger letzter Name!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "Keine gültige Straße!\n";
		}

		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "Keine gültige Postleitzahl!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gültige Postleitzahl (müssen Zahlen sein)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "Keine gültiger Stadt!\n";
		}

		if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
			errorMessage += "Kein gültiger Geburtstag!\n";
		} else {
			if (!DateUtil.validDate(birthdayField.getText())) {
				errorMessage += "Kein gültiger Geburtstag. Verwenden Sie das Format tt.mm.jjjj!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Ungültige Angaben");
			alert.setHeaderText("Bitte korrigieren Sie die folgenden Eingaben");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

}