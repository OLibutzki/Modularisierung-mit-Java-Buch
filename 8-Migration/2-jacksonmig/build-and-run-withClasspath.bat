@echo ---------------------------------------------------------------
@echo Kompilieren und Verpacken der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo (jackson-databind-2.8.8.1.jar wird als Automatic Module behandelt 
@echo und alle anderen Abhängigkeiten werden über den Klassenpfad geladen 
@echo und befinden sich im Unnamed Module)
@echo ---------------------------------------------------------------
copy .\lib\jackson-databind-2.8.8.1.jar .\modules
java -classpath "lib/*" -p modules -m de.javaakademie.jackson/de.javaakademie.jackson.App