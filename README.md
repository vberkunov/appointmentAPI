Some instructions to start the project:
1.You should create database. In my case it is PostgeSQL. 
Thereafter go to file application.properties in folder resources and fill in the fields: 

spring.datasource.url=jdbc:postgresql://localhost/HERE_SHOULD_BE_NAME_OF_DB
spring.datasource.username= HERE_POSTGRES_USERNAME
spring.datasource.password= HERE_PASSWORD

2.To test receiving an email, you should go to https://mailtrap.io/ and get here password and username. 
They also need to be written in the file application.properties.

Example:

spring.mail.username=
spring.mail.password=

3.To check API start spring boot app and follow the link http://localhost:8080/swagger-ui.html#/
4. Student's and teacher's requests are protected by JWT token, so for begin you need signup and signin to get token.
5. When you received the token, click on the lock next to the request and enter : Bearer YOUR_TOKEN
