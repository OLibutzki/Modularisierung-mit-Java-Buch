@echo ---------------------------------------------------------------
@echo Bauen aller Sourcen mit Maven
@echo ---------------------------------------------------------------
call mvn clean install

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -cp lib/* -p modules -m de.firma.whitebox/de.firma.whitebox.App

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes inkl. Patch aus non modular jar
@echo ---------------------------------------------------------------
java --patch-module de.firma.whitebox=patches/whitebox-patch.jar -p modules -m de.firma.whitebox/de.firma.whitebox.App
