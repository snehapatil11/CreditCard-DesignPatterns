# Individual Project: Credit Card Record Reader

![Class Diagram](https://user-images.githubusercontent.com/27798889/80417302-6041a280-888a-11ea-8ece-af15465606af.jpg)

## [Part2 Design Description](https://github.com/gopinathsjsu/individual-project-snehapatil11/tree/master/Part2_Design)

## Steps to Build and Run the Project on windows

1. Download and unpack the latest version of [Maven](https://maven.apache.org/download.cgi)
2. Add 'MAVEN_HOME' and 'M2_HOME' Environment Variables pointing to extracted maven folder
3. Install JDK and Add 'JAVA_HOME' Environment Variable
4. Verify maven : `mvn -version`
5. Clone the git repository and execute the below commands at the project root directory.
6. Compile and install project : `mvn compile` , `mvn install`
7. Copy the input files at the project root directory.
8. Execute Run command : `mvn exec:java -Dexec.mainClass=CreditCardRecordReader -Dexec.args="Sample.csv test.csv"`
9. Console output log has been attached [here](https://github.com/gopinathsjsu/individual-project-snehapatil11/blob/master/OutputLog.txt)
