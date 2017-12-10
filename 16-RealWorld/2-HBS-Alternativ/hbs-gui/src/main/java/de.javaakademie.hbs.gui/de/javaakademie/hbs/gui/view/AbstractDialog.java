package de.javaakademie.hbs.gui.view;

import de.javaakademie.hbs.gui.App;
import javafx.stage.Stage;

public abstract class AbstractDialog {

	protected Stage dialogStage;

	protected boolean okClicked = false;

	// Reference to the main application.
	protected App app;

	public AbstractDialog() {
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return this.okClicked;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public void setItem(Object item) {
	}

}
