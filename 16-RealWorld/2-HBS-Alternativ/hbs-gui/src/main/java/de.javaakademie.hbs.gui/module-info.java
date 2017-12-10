module de.javaakademie.hbs.gui {
	exports de.javaakademie.hbs.gui to javafx.graphics,javafx.fxml;
	exports de.javaakademie.hbs.gui.view to javafx.fxml;
	opens de.javaakademie.hbs.gui.view to javafx.fxml;
    requires de.javaakademie.hbs.api;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    uses de.javaakademie.hbs.api.HotelService;    

	requires java.sql;
	requires gson;
	opens de.javaakademie.hbs.gui.model to gson;
}