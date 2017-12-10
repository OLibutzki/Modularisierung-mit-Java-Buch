@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilieren der Module
@echo ---------------------------------------------------------------
javac -d classes -p classes --module-source-path . de.firma.hello\*.java  de.firma.hello\de\firma\hello\*.java 


@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.hello.jar --main-class de.firma.hello.App -C classes\de.firma.hello .


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.hello



