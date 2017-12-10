@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules

@echo.
@echo ---------------------------------------------------------------
@echo Kompilierung von modauto
@echo ---------------------------------------------------------------
javac -d classes\modauto modauto\src\main\java\de.firma.modauto\de\firma\modauto\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken von modaauto und setzen von Automatic-Module-Name in die Manifest-Datei
@echo ---------------------------------------------------------------
jar --create --file modules\modauto.jar --manifest=manifestModAuto.txt -C classes\modauto . 

@echo.
@echo ---------------------------------------------------------------
@echo Kompilierung des Moduls modmain
@echo ---------------------------------------------------------------
javac -p modules -d classes --module-source-path modmain\src\main\java modmain\src\main\java\de.firma.modmain\*.java modmain\src\main\java\de.firma.modmain\de\firma\modmain\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken des Moduls modmain in ein einzelnes JAR
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.modmain.jar --main-class de.firma.modmain.App -C classes\de.firma.modmain .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.modmain