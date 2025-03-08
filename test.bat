@javac -cp .\lib\junit-4.13.2.jar;.\lib\hamcrest-core-1.3.jar -d .\bin .\src\test\TaxInterfaceTest.java .\src\utils\*.java
@java -cp .\bin;.\lib\junit-4.13.2.jar;.\lib\hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.TaxInterfaceTest
@pause