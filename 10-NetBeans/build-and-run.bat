@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules

@echo ---------------------------------------------------------------
@echo Kompilierung der Sourcen
@echo ---------------------------------------------------------------
javac -d classes --module-source-path HelloWorld HelloWorld\src\*.java HelloWorld\src\helloworld\*.java

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes
@echo ---------------------------------------------------------------
java -p classes -m helloworld/helloworld.HelloWorld

@echo.
@echo ---------------------------------------------------------------
@echo Verpacken des Moduls in ein einzelnes JAR
@echo ---------------------------------------------------------------
jar --create --file modules\helloworld.jar --main-class helloworld.HelloWorld -C classes\helloworld .

@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m helloworld