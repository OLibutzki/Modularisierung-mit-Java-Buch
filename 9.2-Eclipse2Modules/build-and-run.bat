@if exist classes rd /S /Q classes
@if exist modules rd /S /Q modules
@mkdir modules


@echo ---------------------------------------------------------------
@echo Kompilieren der Module
@echo ---------------------------------------------------------------
javac -d classes --module-source-path Greetings Greetings\de.firma.greetings\*.java   Greetings\de.firma.greetings\de\firma\greetings\*.java 
javac -d classes -p classes --module-source-path HelloWorld HelloWorld\de.firma.hello\*.java  HelloWorld\de.firma.hello\de\firma\hello\*.java 


@echo.
@echo ---------------------------------------------------------------
@echo Verpacken der Module
@echo ---------------------------------------------------------------
jar --create --file modules\de.firma.hello.jar --main-class de.firma.hello.App -C classes\de.firma.hello .
jar --create --file modules\de.firma.greetings.jar -C classes\de.firma.greetings .


@echo.
@echo ---------------------------------------------------------------
@echo Starten des Programmes vom verpackten Modul aus 
@echo ---------------------------------------------------------------
java -p modules -m de.firma.hello



