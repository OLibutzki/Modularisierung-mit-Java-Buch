@if exist distribution rd /S /Q distribution
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilierung der Sourcen
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src src\de.firma.modmain\*.java src\de.firma.modmain\de\firma\modmain\*.java

REM Bei Nutzung von --module-source-path wird das abhaengige Modul moda direkt mit erzeugt, wodurch folgende Anweisung überfluessig ist
javac -d classes --module-source-path src src\de.firma.moda\*.java src\de.firma.moda\de\firma\moda\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo ---------------------------------------------------------------
java -p classes -m de.firma.modmain/de.firma.modmain.App

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module in einzelne JARs
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.modmain-1.0.jar --module-version 1.0 --main-class de.firma.modmain.App -C classes\de.firma.modmain .
jar --create --file modules\de.firma.moda-1.0.jar --module-version 1.0 -C classes\de.firma.moda .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.modmain

@echo.
@echo ---------------------------------------------------------------
@echo Abhaengigkeiten auflisten (Welche Module haengen wovon ab)
@echo ---------------------------------------------------------------
jdeps -s modules/*.jar

@echo.
@echo ---------------------------------------------------------------
@echo Bauen der benoetigten Java-Runtime inkl. der eigenen Module
@echo ---------------------------------------------------------------
jlink -p "%JAVA_HOME%\jmods;modules" --add-modules de.firma.modmain --output distribution --strip-debug --compress=2

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes aus der erstellten Java-Runtime heraus 
@echo ---------------------------------------------------------------
distribution\bin\java -m de.firma.modmain

@echo.
@echo ---------------------------------------------------------------
@echo Alle enthaltenen Module der individuell erzeugten Java-Runtime
@echo ---------------------------------------------------------------
distribution\bin\java --list-modules
