@echo ---------------------------------------------------------------
@echo Kompilieren und Verpacken der Module mit Maven
@echo ---------------------------------------------------------------
call mvn clean install

@echo ---------------------------------------------------------------
@echo Starten der Anwendung
@echo ---------------------------------------------------------------
java -cp lib/*;target/jackson.jar de.javaakademie.jackson.App



