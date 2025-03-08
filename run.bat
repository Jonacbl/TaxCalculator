@echo /* run started */
@echo ------------------------------------------

@javac -d .\bin -sourcepath .\src .\src\Main.java .\src\utils\*.java
@java -cp .\bin Main

@echo ------------------------------------------
@echo /* run finished */

@pause