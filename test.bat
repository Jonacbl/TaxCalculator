@javac -d .\bin -sourcepath .\src .\src\Main.java .\src\test\Test.java .\src\utils\*.java
@java -cp .\bin\ test.Test
@pause