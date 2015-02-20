TOMCAT_HOME="/Users/sanjay/Desktop/apache-tomcat-7.0.53/"
mvn clean install
mv target/PhoneBookWebApp-1.0-SNAPSHOT.war $TOMCAT_HOME/webapps/phonebook.war
sh $TOMCAT_HOME/bin/startup.sh
echo "Launch the application at: http://localhost:8080/phonebook"
