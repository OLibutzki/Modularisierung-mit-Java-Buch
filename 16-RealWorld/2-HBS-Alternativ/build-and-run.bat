@echo ---------------------------------------------------------------
@echo Kompilieren der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes 
@echo ---------------------------------------------------------------
java -p modules;lib --add-opens java.base/java.time=gson -m de.javaakademie.hbs.gui/de.javaakademie.hbs.gui.App