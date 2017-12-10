@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilieren des Moduls de.firma.maven
@echo (Abhaengige Modul commons.lang3 muss im /lib-Verzeichnis liegen)
@echo ---------------------------------------------------------------
javac -d classes -p lib --module-source-path src\main\java src\main\java\de.firma.maven\*.java src\main\java\de.firma.maven\de\firma\maven\*.java 


@echo.
@echo ---------------------------------------------------------------
@echo Verpacken des de.firma.maven-Moduls
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.maven.jar --main-class de.firma.maven.App -C classes\de.firma.maven .


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules;lib -m de.firma.maven/de.firma.maven.App

@echo.
@echo ---------------------------------------------------------------
@echo Abhaengigkeiten auflisten (Welche Module haengen wovon ab)
@echo ---------------------------------------------------------------
jdeps -s --module-path lib modules/*.jar 

