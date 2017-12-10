@if exist distribution rd /S /Q distribution
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules

@echo.
@echo ---------------------------------------------------------------
@echo Kompilierung der Module
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src src\de.firma.server\*.java src\de.firma.server\de\firma\server\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module in einzelne JARs
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.server.jar --main-class de.firma.server.HttpServer -C classes\de.firma.server .
jar --create --file modules\de.firma.protocol.jar -C classes\de.firma.protocol .

@echo.
@echo ---------------------------------------------------------------
@echo Bauen der benoetigten Java-Runtime inkl. der Module
@echo ---------------------------------------------------------------
jlink -p "%JAVA_HOME%\jmods;modules" --add-modules de.firma.server --output distribution --strip-debug --compress=2

