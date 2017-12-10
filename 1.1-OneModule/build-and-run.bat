@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilierung der Sourcen
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src src\de.firma.modmain\*.java src\de.firma.modmain\de\firma\modmain\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo ---------------------------------------------------------------
java -p classes -m de.firma.modmain/de.firma.modmain.App

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken des Moduls in ein einzelnes JAR
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.modmain.jar --main-class de.firma.modmain.App -C classes\de.firma.modmain .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.modmain