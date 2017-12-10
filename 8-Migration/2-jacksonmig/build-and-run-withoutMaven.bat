cls
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilieren der Module
@echo (Abhängigkeiten müssen in /lib liegen !!!!!!!!!!!!)
@echo ---------------------------------------------------------------
javac -p lib -d classes --module-source-path src\main\java src\main\java\de.javaakademie.jackson\*.java src\main\java\de.javaakademie.jackson\de\javaakademie\jackson\*.java src\main\java\de.javaakademie.jackson\de\javaakademie\jackson\model\*.java 


@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module
@echo ---------------------------------------------------------------
jar --create --file modules\de.javaakademie.jackson.jar --main-class de.javaakademie.jackson.App -C classes\de.javaakademie.jackson .


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo (Abhängige Non-Modular JARS werden alle als Automatic Modules behandelt)
@echo ---------------------------------------------------------------
java -p modules;lib -m de.javaakademie.jackson/de.javaakademie.jackson.App


