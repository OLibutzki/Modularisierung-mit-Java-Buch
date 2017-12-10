@echo ---------------------------------------------------------------
@echo Kompilieren und Verpacken der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo (Abhängige Non-Modular JARS werden alle als Automatic Modules behandelt)
@echo ---------------------------------------------------------------
java -p modules;lib -m de.javaakademie.jackson/de.javaakademie.jackson.App



