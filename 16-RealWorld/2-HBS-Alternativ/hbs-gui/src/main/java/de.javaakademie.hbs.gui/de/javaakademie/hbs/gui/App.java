package de.javaakademie.hbs.gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

import de.javaakademie.hbs.gui.model.Booking;
import de.javaakademie.hbs.gui.model.Guest;
import de.javaakademie.hbs.gui.model.Room;
import de.javaakademie.hbs.gui.model.RoomType;
import de.javaakademie.hbs.gui.view.AbstractDialog;
import de.javaakademie.hbs.gui.view.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * App.
 * 
 * @author Guido.Oelmann
 */
public class App extends Application {

	private final static String APP_TITLE = "Hotel Booking System";

	private final static String LAYOUT = "resources/views/layout.fxml";

	private final static String CSS = "/resources/css/layout.css";

	private final static String GUEST_EDIT_DIALOG = "resources/views/guestDialog.fxml";

	private final static String BOOKING_EDIT_DIALOG = "resources/views/bookingDialog.fxml";

	private Stage primaryStage;

	private HotelDataService hotelDataService;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle(APP_TITLE);
		hotelDataService = new HotelDataService();
		initRootLayout();
		initTestData();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			InputStream layoutStream = App.class.getModule().getResourceAsStream(LAYOUT);
			FXMLLoader loader = new FXMLLoader();
			Parent rootLayout = loader.load(layoutStream);

			// Set in the main controller a reference to the main app.
			MainController controller = (MainController) loader.getController();
			controller.setApp(this);

			// Show the scene containing the root layout.
			primaryStage.setScene(new Scene(rootLayout));
			URL cssURL = App.class.getResource(CSS);
			primaryStage.getScene().getStylesheets().add(cssURL.toString());
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified booking or create a new one.
	 * If the user clicks OK, the changes are saved into the provided booking object
	 * and true is returned.
	 * 
	 * @param booking
	 *            the booking object to be edited or create
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showEditDialog(Object item, Class<?>... clazz) {
		try {
			String title = "";
			// Load the fxml file and create a new stage for the popup dialog.
			InputStream layoutStream = null;
			if (item instanceof Guest || clazz[0] == Guest.class) {
				layoutStream = App.class.getModule().getResourceAsStream(GUEST_EDIT_DIALOG);
				title = "Gast";
			} else if (item instanceof Booking || clazz[0] == Booking.class) {
				layoutStream = App.class.getModule().getResourceAsStream(BOOKING_EDIT_DIALOG);
				title = "Buchung";
			}
			FXMLLoader fxmlLoader = new FXMLLoader();
			AnchorPane page = (AnchorPane) fxmlLoader.load(layoutStream);

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setResizable(false);
			dialogStage.setTitle(title);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the guest into the controller.
			AbstractDialog controller = fxmlLoader.getController();
			controller.setDialogStage(dialogStage);
			controller.setItem(item);
			// Set in the main controller a reference to the main app.
			controller.setApp(this);
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public HotelDataService getHotelDataService() {
		return hotelDataService;
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return primary Stage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void initTestData() {
		// create room testdata
		Room standardRoom1 = new Room(1, 112, RoomType.STANDARD, 89.00, "/resources/images/standard.png");
		Room standardRoom2 = new Room(1, 114, RoomType.STANDARD, 89.00, "/resources/images/standard.png");
		Room comfortRoom = new Room(1, 115, RoomType.COMFORT, 180.00, "/resources/images/comfort.png");
		Room suite = new Room(2, 200, RoomType.SUITE, 520.00, "/resources/images/suite.png");
		hotelDataService.persist(standardRoom1);
		hotelDataService.persist(standardRoom2);
		hotelDataService.persist(comfortRoom);
		hotelDataService.persist(suite);

		// String street, Integer postalCode, String city, LocalDate birthday
		// create guest testdata "Wookiestr. 12\n12345 PolisMassa"
		Guest guest1 = new Guest("Luke", "Skywalker", "Wookiestr. 12", 12345, "PolisMassa", LocalDate.now());
		Guest guest2 = new Guest("Leia", "Organa", "Tiberianstr. 12", 34562, "Alderaan", LocalDate.now());
		hotelDataService.persist(guest1);
		hotelDataService.persist(guest2);

		// create booking testdata
		Booking booking1 = new Booking(LocalDate.now(), LocalDate.now().plusDays(1), guest1, standardRoom1);
		Booking booking2 = new Booking(LocalDate.now().plusDays(3), LocalDate.now().plusDays(10), guest2, suite);
		hotelDataService.persist(booking1);
		hotelDataService.persist(booking2);
	}
}
