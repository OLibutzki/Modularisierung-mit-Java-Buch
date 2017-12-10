@if exist distribution rd /S /Q distribution
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilieren und verpacken des de.firma.spi-Moduls
@echo ---------------------------------------------------------------
javac -d classes\de.firma.spi src\de.firma.spi\*.java src\de.firma.spi\de\firma\spi\*.java
jar --create --file=modules\de.firma.spi.jar -C classes\de.firma.spi .

@echo.
@echo ---------------------------------------------------------------
@echo Kompilieren und verpacken des de.firma.provider-Moduls
@echo ---------------------------------------------------------------
javac -p modules -d classes\de.firma.provider src\de.firma.provider\*.java src\de.firma.provider\de\firma\provider\*.java
jar --create --file=modules\de.firma.provider.jar -C classes\de.firma.provider .

@echo.
@echo ---------------------------------------------------------------
@echo Kompilieren und verpacken des de.firma.consumer-Moduls
@echo ---------------------------------------------------------------
javac -p modules -d classes\de.firma.consumer src\de.firma.consumer\*.java src\de.firma.consumer\de\firma\consumer\*.java
jar --create --file=modules\de.firma.consumer.jar --main-class=de.firma.consumer.ServiceConsumer -C classes\de.firma.consumer .


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.consumer

@echo.
@echo ---------------------------------------------------------------
@echo Abhaengigkeiten auflisten (Welche Module haengen wovon ab)
@echo ---------------------------------------------------------------
jdeps -s modules/*.jar

@echo.
@echo ---------------------------------------------------------------
@echo Bauen der benoetigten Java-Runtime inkl. der eigenen Module
@echo ---------------------------------------------------------------
jlink -p "%JAVA_HOME%\jmods;modules" --add-modules de.firma.consumer,de.firma.provider --output distribution --strip-debug --compress=2

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes aus der erstellten Java-Runtime heraus 
@echo ---------------------------------------------------------------
distribution\bin\java -m de.firma.consumer

@echo.
@echo ---------------------------------------------------------------
@echo Alle enthaltenen Module der individuell erzeugten Java-Runtime
@echo ---------------------------------------------------------------
distribution\bin\java --list-modules


