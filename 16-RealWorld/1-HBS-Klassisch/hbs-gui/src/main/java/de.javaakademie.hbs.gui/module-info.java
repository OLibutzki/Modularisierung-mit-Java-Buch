module de.javaakademie.hbs.gui {
	exports de.javaakademie.hbs.gui to javafx.graphics,javafx.fxml;
	opens de.javaakademie.hbs.gui to javafx.fxml;
    requires de.javaakademie.hbs.api;
    requires de.javaakademie.hbs.model;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    uses de.javaakademie.hbs.api.HotelService;    
}