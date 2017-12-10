cls
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilieren der Module
@echo (Abhängigkeiten müssen in /lib liegen)
@echo ---------------------------------------------------------------
javac -p lib -d classes --module-source-path src\main\java src\main\java\de.javaakademie.guests\*.java src\main\java\de.javaakademie.guests\de\javaakademie\guests\*.java src\main\java\de.javaakademie.guests\de\javaakademie\guests\model\*.java 


@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module
@echo ---------------------------------------------------------------
jar --create --file modules\de.javaakademie.guests.jar --main-class de.javaakademie.guests.App -C classes\de.javaakademie.guests .


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo (Abhängige Non-Modular JARS werden alle als Automatic Modules behandelt)
@echo ---------------------------------------------------------------
java -p modules;lib --add-opens java.base/java.time=gson -m de.javaakademie.guests/de.javaakademie.guests.App



