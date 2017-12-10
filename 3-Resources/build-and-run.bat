@echo ---------------------------------------------------------------
@echo Kompilieren und verpacken der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.ref.main/de.firma.ref.main.App