@if exist distribution rd /S /Q distribution
@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@if exist patches rd /S /Q patches
@if exist patches-jar rd /S /Q patches-jar
@mkdir modules
@mkdir patches-jar

@echo ---------------------------------------------------------------
@echo Kompilierung des Patchs
@echo ---------------------------------------------------------------
javac -d patches\de.firma.moda-patch src\de.firma.moda-patch\de\firma\moda\*.java 

@echo ---------------------------------------------------------------
@echo Kompilierung der Module
@echo ---------------------------------------------------------------
javac -d classes --module-source-path src src\de.firma.modmain\*.java src\de.firma.modmain\de\firma\modmain\*.java 

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken des Patchs und der Module in einzelne JARs
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.modmain-1.0.jar --module-version 1.0 --main-class de.firma.modmain.App -C classes\de.firma.modmain .
jar --create --file modules\de.firma.moda-1.0.jar --module-version 1.0 -C classes\de.firma.moda .
jar --create --file patches-jar\de.firma.moda-patch.jar -C patches\de.firma.moda-patch .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.modmain

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes inkl. Patch mit Klasse
@echo ---------------------------------------------------------------
java --patch-module de.firma.moda=patches/de.firma.moda-patch -p modules -m de.firma.modmain

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes inkl. Patch aus non modular jar
@echo ---------------------------------------------------------------
java --patch-module de.firma.moda=patches-jar/de.firma.moda-patch.jar -p modules -m de.firma.modmain


@echo.
@echo ---------------------------------------------------------------
@echo Patchen des Moduls während der Kompilierung und neu verpacken
@echo ---------------------------------------------------------------
javac --patch-module de.firma.moda=patches/de.firma.moda-patch -d classes --module-source-path src src\de.firma.moda\*.java src\de.firma.moda\de\firma\moda\*.java 
jar --create --file modules\de.firma.moda-1.0.jar --module-version 1.0 -C classes\de.firma.moda .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.modmain
