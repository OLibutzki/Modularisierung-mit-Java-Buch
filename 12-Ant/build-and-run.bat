@echo.
@echo ---------------------------------------------------------------
@echo Kompilierung des Moduls
@echo ---------------------------------------------------------------
call ant build

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo ---------------------------------------------------------------
java -p modules -m de.firma.ant/de.firma.ant.App