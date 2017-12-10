@if exist classes rd /S /Q classes
@if exist mainmodule rd /S /Q mainmodule
@if exist mod-version-1 rd /S /Q mod-version-1
@if exist mod-version-2 rd /S /Q mod-version-2
@mkdir mainmodule
@mkdir mod-version-1
@mkdir mod-version-2


@echo ---------------------------------------------------------------
@echo Kompilierung der Sourcen
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src src\de.firma.world\*.java src\de.firma.world\de\firma\world\*.java 
javac -d classes --module-source-path src src\de.firma.greetings\*.java src\de.firma.greetings\de\firma\greetings\*.java 

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module in einzelne JARs
@echo ---------------------------------------------------------------
jar --create --file mainmodule\de.firma.world.jar --main-class de.firma.world.App -C classes\de.firma.world .
jar --create --file mod-version-1\de.firma.greetings-1.0.jar --module-version 1.0 -C classes\de.firma.greetings .
jar --create --file mod-version-2\de.firma.greetings-2.0.jar --module-version 2.0 -C classes\de.firma.greetings .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p mainmodule -m de.firma.world


