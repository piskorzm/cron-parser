# cron-parser
Given a CRON expression the application parses and prints the generated schedule.

### Setup
The application uses Gradle wrapper to build the application.
Java installation and "PATH" variable is required.
To set up your project open a terminal and run the following commands:
```
user@cron-parser: ./gradlew build   
user@cron-parser: ./gradlew test   
```

### Usage
Once the application is built you can find it in build/libs/cron-parser-1.0.jar
To run it use the java -jar command and pass CRON expression as an argument.
Here is an example result:
```
user@cron-parser/builds/libs: java -jar cron-parser-1.0.jar "1-20 4-20 4/4 2,4,9 ? test-command"
minute         1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
hour           4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
day of month   4 8 12 16 20 24 28
month          2 4 9
command        test-command 
```
