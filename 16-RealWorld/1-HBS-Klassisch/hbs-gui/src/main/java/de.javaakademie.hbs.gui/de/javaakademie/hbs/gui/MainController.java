package de.javaakademie.hbs.gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import de.javaakademie.hbs.api.HotelService;
import de.javaakademie.hbs.model.Booking;
import de.javaakademie.hbs.model.Guest;
import de.javaakademie.hbs.model.Room;
import de.javaakademie.hbs.model.RoomType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

	private ServiceFactory serviceFactory;
	private HotelService<Booking> bookingService;
	private HotelService<Guest> guestService;
	private HotelService<Room> roomService;

	public void initialize(URL location, ResourceBundle resources) {
		serviceFactory = new ServiceFactory();
		try {
			bookingService = serviceFactory.getBookingService();
			guestService = serviceFactory.getGuestService();
			roomService = serviceFactory.getRoomService();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		initTestData();
		initToolbar();
		initActionButtons();
		initListView();
	}

	@FXML
	private void handleDeleteButtonAction(ActionEvent event) {
		Object listItem = listView.getSelectionModel().getSelectedItem();
		if (listItem == null) {
			System.out.println("Nothing selected");
		} else if (listItem instanceof Booking) {
			Booking booking = (Booking) listItem;
			bookingService.remove(booking);
			bookingsButton.fire();
			System.out.println("Delete " + booking);
		} else if (listItem instanceof Guest) {
			Guest guest = (Guest) listItem;
			guestService.remove(guest);
			guestsButton.fire();
			System.out.println("Delete " + guest);
		} else if (listItem instanceof Room) {
			Room room = (Room) listItem;
			roomService.remove(room);
			roomsButton.fire();
			System.out.println("Delete " + room);
		}
	}

	@FXML
	private void handleAddButtonAction(ActionEvent event) {
		System.out.println("Add new Item");
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
				clearListView();
				Collection<Booking> speakerList = bookingService.getAll();
				ObservableList<Object> observableSpeakerList = FXCollections
						.observableList(new ArrayList<>(speakerList));
				listView.setItems(observableSpeakerList);
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
				clearListView();
				Collection<Guest> sessionList = guestService.getAll();
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
				clearListView();
				Collection<Room> roomList = roomService.getAll();
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
								setText(guest.getFirstname() + " " + guest.getLastname());
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

		Label guestName = new Label(guest.getFirstname() + " " + guest.getLastname());
		guestName.setFont(Font.font("Verdana", 14));
		guestName.setTextAlignment(TextAlignment.CENTER);
		guestName.setAlignment(Pos.CENTER);

		Label address = new Label(guest.getAddress());
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

	private void initTestData() {
		// create room testdata
		Room standardRoom1 = new Room(1, 112, RoomType.STANDARD, 89.00, "/resources/images/standard.png");
		Room standardRoom2 = new Room(1, 114, RoomType.STANDARD, 89.00, "/resources/images/standard.png");
		Room comfortRoom = new Room(1, 115, RoomType.COMFORT, 180.00, "/resources/images/comfort.png");
		Room suite = new Room(2, 200, RoomType.SUITE, 520.00, "/resources/images/suite.png");
		roomService.persist(standardRoom1);
		roomService.persist(standardRoom2);
		roomService.persist(comfortRoom);
		roomService.persist(suite);

		// create guest testdata
		Guest guest1 = new Guest("Luke", "Skywalker", "Wookiestr. 12\n12345 Polis Massa");
		Guest guest2 = new Guest("Leia", "Organa", "Tiberianstr. 12\n34562 Alderaan");
		guestService.persist(guest1);
		guestService.persist(guest2);

		// create booking testdata
		Booking booking1 = new Booking(LocalDate.now(), LocalDate.now().plusDays(1), guest1, standardRoom1);
		Booking booking2 = new Booking(LocalDate.now().plusDays(3), LocalDate.now().plusDays(10), guest2, suite);
		bookingService.persist(booking1);
		bookingService.persist(booking2);
	}

}