@echo ---------------------------------------------------------------
@echo Kompilieren der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo (Abhängige Non-Modular JARS werden alle als Automatic Modules behandelt)
@echo ---------------------------------------------------------------
java -p modules;lib --add-opens java.base/java.time=gson -m de.javaakademie.guests/de.javaakademie.guests.App