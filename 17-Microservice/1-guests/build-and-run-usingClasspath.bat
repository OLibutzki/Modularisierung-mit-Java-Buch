@echo ---------------------------------------------------------------
@echo Kompilieren der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo (gson-2.8.0.jar und spark-core-2.6.0.jar werden als Automatic Module behandelt. 
@echo Alle anderen Abhängigkeiten von DRitt-Bibliotheken werden über den Klassenpfad 
@echo geladen und befinden sich im Unnamed Module)
@echo ---------------------------------------------------------------
copy .\lib\spark-core-2.6.0.jar .\modules
copy .\lib\gson-2.8.0.jar .\modules
java -classpath "lib/*" -p modules --add-opens java.base/java.time=gson -m de.javaakademie.guests/de.javaakademie.guests.App