cls
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules

@echo.
@echo ---------------------------------------------------------------
@echo Kompilierung des Moduls
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src src\de.firma.ant\*.java src\de.firma.ant\de\firma\ant\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken des Moduls
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.ant.jar -C classes\de.firma.ant . 

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo ---------------------------------------------------------------
java -p modules -m de.firma.ant/de.firma.ant.App