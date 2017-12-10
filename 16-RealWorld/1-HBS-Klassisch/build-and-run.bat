@echo ---------------------------------------------------------------
@echo Kompilieren der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes 
@echo ---------------------------------------------------------------
java -p modules -m de.javaakademie.hbs.gui/de.javaakademie.hbs.gui.App