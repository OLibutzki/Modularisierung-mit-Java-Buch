@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilieren des Moduls de.firma.maven
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src\main\java src\main\java\de.firma.maven\*.java src\main\java\de.firma.maven\de\firma\maven\*.java 


@echo.
@echo ---------------------------------------------------------------
@echo Verpacken des de.firma.maven-Moduls
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.maven.jar --main-class de.firma.maven.App -C classes\de.firma.maven .


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.maven/de.firma.maven.App


