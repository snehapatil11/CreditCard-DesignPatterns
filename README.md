# Individual Project: Credit Card Record Reader



## Steps to Build and Run the Project on windows

1. Download and unpack the latest version of Maven ( https://maven.apache.org/download.cgi )
2. Add 'MAVEN_HOME' and 'M2_HOME' Environment Variables pointing to extracted maven folder
3. Install JDK and Add ‘JAVA_HOME’ Environment Variable
4. Verify maven : `mvn -version`
5. Clone the git repository and execute the below commands at the project root directory.
6. Compile and install project : `mvn compile` , `mvn install`
7. Run Project : `mvn exec:java -Dexec.mainClass=CreditCardRecordReader -Dexec.args="Sample.csv test.csv"`
