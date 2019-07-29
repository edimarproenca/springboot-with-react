# springboot-with-react


Java >= 8
Maven >= 3.5.0
Yarn >= 1.17.3
Node >= v12.6.0

Starting Api

#To Start Spring-Boot application go to Navigate to backend app folder and use the follow command

mvn spring-boot::run

if you need to use H2 BD select dev profile
mvn -Pdev spring-boot::run

or

define prod profile
mvn -Pprod spring-boot::run
configure your BD in application application-prod.properties


The app will be listening on localhost:8081


#To Start react application 

yarn start

The app will be listening on localhost:3000

