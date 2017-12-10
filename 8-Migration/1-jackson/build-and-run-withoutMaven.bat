@echo ---------------------------------------------------------------
@echo Kompilieren der Klassen nach /classes
@echo (Abhängigkeiten müssen in /lib liegen !!!!!!!!!!!!)
@echo ---------------------------------------------------------------
javac -classpath "lib/*" -d classes src\main\java\de\javaakademie\jackson\model\*.java src\main\java\de\javaakademie\jackson\*.java

@echo ---------------------------------------------------------------
@echo Verpacken der Klassen in ein JAR
@echo ---------------------------------------------------------------
jar cfe target/jackson.jar de.javaakademie.jackson.App -C classes . 

@echo ---------------------------------------------------------------
@echo Starten der Anwendung
@echo ---------------------------------------------------------------
java -cp lib/*;target/jackson.jar de.javaakademie.jackson.App
