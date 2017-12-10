@if exist distribution rd /S /Q distribution
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilierung der Sourcen
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src src\de.firma.modmain\*.java src\de.firma.modmain\de\firma\modmain\*.java 
REM kompiliert alle Abhaengigkeiten, Paket internal muss extra angegeben werden
REM paketa wurde bereits im vorherigen Befehl mitkompiliert
javac -d classes --module-source-path src src\de.firma.moda\*.java src\de.firma.moda\de\firma\moda\paketa\*.java src\de.firma.moda\de\firma\moda\internal\*.java 
javac -d classes --module-source-path src src\de.firma.modb\*.java src\de.firma.modb\de\firma\modb\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo ---------------------------------------------------------------
java -p classes --add-modules de.firma.modb  -m de.firma.modmain/de.firma.modmain.App

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module in einzelne JARs
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.modmain.jar --main-class de.firma.modmain.App -C classes\de.firma.modmain .
jar --create --file modules\de.firma.moda.jar -C classes\de.firma.moda .
jar --create --file modules\de.firma.modb.jar -C classes\de.firma.modb .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules --add-modules de.firma.modb -m de.firma.modmain

@echo.
@echo ---------------------------------------------------------------
@echo Abhaengigkeiten auflisten (Welche Module haengen wovon ab)
@echo ---------------------------------------------------------------
jdeps -s modules/*.jar

