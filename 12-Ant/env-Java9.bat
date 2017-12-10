set ANT_HOME=C:\Program Files\apache-ant-1.10.1

set JAVA_HOME=C:\Program Files\Java\jdk-9
PATH=%ANT_HOME%\bin;%JAVA_HOME%\bin;%PATH%

echo JAVA_HOME=%JAVA_HOME%
java -version
javac -version