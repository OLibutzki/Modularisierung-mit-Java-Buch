package de.javaakademie.hbs.gui.view;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

import de.javaakademie.hbs.gui.App;
import de.javaakademie.hbs.gui.ImageHelper;
import de.javaakademie.hbs.gui.model.Booking;
import de.javaakademie.hbs.gui.model.Guest;
import de.javaakademie.hbs.gui.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 * MainController.
 * 
 * @author Guido.Oelmann
 */
public class MainController implements Initializable {

	private final static String IMAGE_BOOKINGS = "/resources/images/calendar.png";
	private final static String IMAGE_GUESTS = "/resources/images/guests.png";
	private final static String IMAGE_ROOMS = "/resources/images/bed.png";
	private final static String IMAGE_ADD = "/resources/images/add.png";
	private final static String IMAGE_REMOVE = "/resources/images/remove.png";
	private final static String BUTTON_TEXT_BOOKINGS = "Buchungen";
	private final static String BUTTON_TEXT_GUESTS = "Gäste";
	private final static String BUTTON_TEXT_ROOMS = "Zimmer";

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@FXML
	private ToolBar toolBar;

	private Class<?> viewSelected;

	@FXML
	private ListView<Object> listView;

	@FXML
	private VBox details;

	private Button bookingsButton;
	private Button guestsButton;
	private Button roomsButton;

	@FXML
	private Button deleteButton;

	@FXML
	private Button addButton;

	// Reference to the main application.
	private App app;

	public void initialize(URL location, ResourceBundle resources) {
		// System.exit(0);
		initToolbar();
		initActionButtons();
		initListView();
	}

	@FXML
	private void handleDeleteButtonAction(ActionEvent event) {
		Object listItem = listView.getSelectionModel().getSelectedItem();
		// removing rooms not allowed
		if (listItem instanceof Room) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Hinweis");
			alert.setHeaderText("Es können keine Zimmer entfernt werden.");
			// alert.setContentText(s);
			alert.show();
			return;
		}
		if (listItem != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Frage");
			alert.setHeaderText("Eintrag löschen?");
			// String s = "Confirm to delete entry!";
			// alert.setContentText(s);
			Optional<ButtonType> result = alert.showAndWait();
			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
				if (listItem != null) {
					app.getHotelDataService().remove(listItem);
				}
				refreshView();
			}
		}
	}

	@FXML
	private void handleAddButtonAction(ActionEvent event) {
		// adding rooms not allowed
		if (viewSelected == Room.class) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Hinweis");
			alert.setHeaderText("Es können keine neuen Zimmer hinzugefügt werden.");
			// alert.setContentText(s);
			alert.show();
			return;
		}
		boolean okClicked = false;
		if (viewSelected == Guest.class) {
			okClicked = app.showEditDialog(null, Guest.class);
		} else if (viewSelected == Booking.class) {
			okClicked = app.showEditDialog(null, Booking.class);
		}
		if (okClicked) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Neuer Eintrag hinzugefügt!");
			// alert.setContentText("");
			alert.show();
			refreshView();
		}
	}

	private void clearListView() {
		if (listView.getItems() != null) {
			listView.getItems().removeAll(listView.getSelectionModel().getSelectedItems());
			listView.getItems().clear();
			listView.getItems().removeAll();
			listView.setItems(null);
			listView.getSelectionModel().clearSelection();
			listView.getSelectionModel().clearSelection();
			listView.refresh();
		}
	}

	private void refreshView() {
		if (viewSelected == Booking.class) {
			bookingsButton.fire();
		} else if (viewSelected == Guest.class) {
			guestsButton.fire();
		} else if (viewSelected == Room.class) {
			roomsButton.fire();
		}
	}

	private void initToolbar() {
		ImageView bookingImage = ImageHelper.getImage(MainController.class.getResourceAsStream(IMAGE_BOOKINGS));
		ImageView guestImage = ImageHelper.getImage(MainController.class.getResourceAsStream(IMAGE_GUESTS));
		ImageView roomImage = ImageHelper.getImage(MainController.class.getResourceAsStream(IMAGE_ROOMS));
		bookingImage.setFitWidth(45);
		guestImage.setFitWidth(45);
		roomImage.setFitWidth(45);

		bookingsButton = new Button(BUTTON_TEXT_BOOKINGS, bookingImage);
		bookingsButton.getStyleClass().add("toolbarButton");
		bookingsButton.setContentDisplay(ContentDisplay.TOP);
		bookingsButton.setMinHeight(75);
		bookingsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewSelected = Booking.class;
				clearListView();
				Collection<Booking> bookingList = app.getHotelDataService().getAll(Booking.class);
				ObservableList<Object> observableBookingList = FXCollections
						.observableList(new ArrayList<>(bookingList));
				listView.setItems(observableBookingList);
				listView.refresh();
				details.getChildren().clear();
			}
		});
		bookingsButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				bookingsButton.setContentDisplay(ContentDisplay.TOP);
			}
		});

		guestsButton = new Button(BUTTON_TEXT_GUESTS, guestImage);
		guestsButton.getStyleClass().add("toolbarButton");
		guestsButton.setContentDisplay(ContentDisplay.TOP);
		guestsButton.setMinHeight(75);
		guestsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewSelected = Guest.class;
				clearListView();
				Collection<Guest> sessionList = app.getHotelDataService().getAll(Guest.class);
				ObservableList<Object> observableSessionsList = FXCollections
						.observableList(new ArrayList<>(sessionList));
				listView.setItems(observableSessionsList);
				details.getChildren().clear();
			}
		});
		guestsButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				guestsButton.setContentDisplay(ContentDisplay.TOP);
			}
		});

		roomsButton = new Button(BUTTON_TEXT_ROOMS, roomImage);
		roomsButton.getStyleClass().add("toolbarButton");
		roomsButton.setContentDisplay(ContentDisplay.TOP);
		roomsButton.setMinHeight(75);
		roomsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				viewSelected = Room.class;
				clearListView();
				Collection<Room> roomList = app.getHotelDataService().getAll(Room.class);
				ObservableList<Object> observableRoomsList = FXCollections.observableList(new ArrayList<>(roomList));
				listView.setItems(observableRoomsList);
				details.getChildren().clear();
			}
		});
		roomsButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				roomsButton.setContentDisplay(ContentDisplay.TOP);
			}
		});

		VBox menuButtons = new VBox(15);
		menuButtons.getChildren().addAll(bookingsButton, guestsButton, roomsButton);
		toolBar.getItems().add(menuButtons);
	}

	private void initActionButtons() {
		ImageView addImage = ImageHelper.getImage(MainController.class.getResourceAsStream(IMAGE_ADD));
		addImage.setFitHeight(20);
		addImage.setPreserveRatio(true);
		addButton.setGraphic(addImage);

		ImageView removeImage = ImageHelper.getImage(MainController.class.getResourceAsStream(IMAGE_REMOVE));
		removeImage.setFitHeight(20);
		removeImage.setPreserveRatio(true);
		deleteButton.setGraphic(removeImage);
	}

	private void initListView() {
		listView.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Object listItem = listView.getSelectionModel().getSelectedItem();
				if (listItem instanceof Booking) {
					Booking booking = (Booking) listItem;
					setBookingDetails(booking);
				} else if (listItem instanceof Guest) {
					Guest guest = (Guest) listItem;
					setGuestDetails(guest);
				} else if (listItem instanceof Room) {
					Room room = (Room) listItem;
					setRoomDetails(room);
				}
			}
		});

		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					Object listItem = listView.getSelectionModel().getSelectedItem();
					if (listItem != null) {
						boolean okClicked = app.showEditDialog(listItem);
						if (okClicked) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information");
							alert.setHeaderText("Änderungen wurden durchgeführt!");
							// alert.setContentText("");
							alert.show();
							refreshView();
						}
					}
				}
			}
		});

		listView.setCellFactory(new Callback<ListView<Object>, ListCell<Object>>() {
			@Override
			public ListCell<Object> call(ListView<Object> p) {
				ListCell<Object> cell = new ListCell<Object>() {
					HBox cell = new HBox();
					Label firstText = new Label();
					Pane emptyPane = new Pane();
					Label secondText = new Label();

					@Override
					protected void updateItem(Object listItem, boolean bln) {
						super.updateItem(listItem, bln);
						setText(null);
						setGraphic(null);
						cell.getChildren().clear();
						if (listItem != null) {
							if (listItem instanceof Booking) {
								Booking booking = (Booking) listItem;
								String beginReservation = booking.getBeginDate().format(formatter).toString();
								String endReservation = booking.getEndDate().format(formatter).toString();
								setText(beginReservation + " - " + endReservation);
							} else if (listItem instanceof Guest) {
								Guest guest = (Guest) listItem;
								setText(guest.getFirstName() + " " + guest.getLastName());
							} else if (listItem instanceof Room) {
								Room room = (Room) listItem;
								firstText = new Label(room.getType().getName());
								secondText = new Label(" (E:" + room.getFloor() + ", Nr:" + room.getNumber() + ")");
								cell.getChildren().addAll(firstText, emptyPane, secondText);
								HBox.setHgrow(emptyPane, Priority.ALWAYS);
								setGraphic(cell);
							}
						}
					}
				};
				return cell;
			}
		});
		listView.getSelectionModel().clearSelection();
	}

	private void setBookingDetails(Booking booking) {
		details.getChildren().clear();

		String beginReservation = booking.getBeginDate().format(formatter).toString();
		String endReservation = booking.getEndDate().format(formatter).toString();

		Label bookingTitle = new Label("Buchung");
		bookingTitle.setFont(Font.font("Verdana", 14));
		bookingTitle.setTextAlignment(TextAlignment.CENTER);
		bookingTitle.setAlignment(Pos.CENTER);

		Label bookingDetails = new Label(beginReservation + " - " + endReservation);
		bookingDetails.setFont(Font.font("Verdana", 12));
		bookingDetails.setTextAlignment(TextAlignment.CENTER);
		bookingDetails.setAlignment(Pos.CENTER);

		details.setPadding(new Insets(25));
		details.setAlignment(Pos.TOP_CENTER);
		details.getChildren().addAll(bookingTitle, bookingDetails);
	}

	private void setGuestDetails(Guest guest) {
		details.getChildren().clear();

		Label guestName = new Label(guest.getFirstName() + " " + guest.getLastName());
		guestName.setFont(Font.font("Verdana", 14));
		guestName.setTextAlignment(TextAlignment.CENTER);
		guestName.setAlignment(Pos.CENTER);

		String adr = guest.getStreet() + "\n" + guest.getPostalCode() + " " + guest.getCity();
		Label address = new Label(adr);
		address.setWrapText(true);
		address.setTextAlignment(TextAlignment.LEFT);
		address.setPadding(new Insets(25));

		details.setPadding(new Insets(25));
		details.setAlignment(Pos.TOP_CENTER);
		details.getChildren().addAll(guestName, address);
	}

	private void setRoomDetails(Room room) {
		details.getChildren().clear();

		ImageView roomImage = ImageHelper.getImage(MainController.class.getResourceAsStream(room.getPicture()));
		Label pictureLabel = new Label("", roomImage);

		Label roomTypeLabel = new Label(room.getType().getName());
		roomTypeLabel.setFont(Font.font("Verdana", 14));
		roomTypeLabel.setTextAlignment(TextAlignment.CENTER);
		roomTypeLabel.setAlignment(Pos.CENTER);

		Label roomDetails = new Label(
				"Preis: " + room.getPrice() + ", Etage: " + room.getFloor() + ", Nummer: " + room.getNumber());
		roomDetails.setFont(Font.font("Verdana", 12));
		roomDetails.setTextAlignment(TextAlignment.CENTER);
		roomDetails.setAlignment(Pos.CENTER);

		Label roomDescription = new Label(room.getType().getDescription());
		roomDescription.setWrapText(true);
		roomDescription.setPadding(new Insets(25));

		details.setPadding(new Insets(25));
		details.setAlignment(Pos.TOP_CENTER);
		details.getChildren().addAll(pictureLabel, roomTypeLabel, roomDetails, roomDescription);
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

}