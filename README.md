# Master Spring Boot 2

doc: https://docs.spring.io/spring-boot/docs/current/reference/html/

github: https://github.com/spring-projects/spring-boot

# System requirement 

1. java8
1. maven 3.5.3
1. mysql 5.6
1. tomcat 8.5 (if you build with .war)

# Using .jar vs .war

Traditionally we will have separate web container like tomcat to deploy `.war` file to but Spring boot provide building with "fat jar" that embedded predefine tomcat, so we can `java -jar` to start server

When using `.war`, we should have `ServletInitializer extends SpringBootServletInitializer`

To control what final file format build to, we can control in `pom.xml` properties `<packaging>jar</packaging>`

ref: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file

# Set up local dev env

1. `cp -R src\main\resources\config.dev src\main\resources\config.local`
1. `mvn install`
1. `mvn clean package -Dflyway.configFiles=target\classes\flyway-config.properties flyway:migrate -P local`
1. `mvn clean install -P local spring-boot:run`

# Debug

In local env, default will run the application in debug model as defined in `pom.xml` with port 5005.

If want run manually, try below

`mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"`

# Database Versioning

We use flyway to do db versioning, as we want more control, we do not directly intergate flyway to spring boot but using flyway maven plugin

# ORM layer


## command

flyway provide different commands to help use to controller db migration, 

pattern: `mvn clean package -Dflyway.configFiles=target\classes\flyway-config.properties flyway:${flyway_action} -P ${env}`

most commonly used:

`mvn clean package -Dflyway.configFiles=target\classes\flyway-config.properties flyway:info -P local`

`mvn clean package -Dflyway.configFiles=target\classes\flyway-config.properties flyway:migrate -P local`

# Testing

We only force on integration test

Test case run in different db

To avoid maven test phase in every build life cycle, `<maven.test.skip>true</maven.test.skip>` is added to pom.xml to skip test phase

mvn command for test should always add `-P testing` parameter

Test log is skipped by configing `src/test/resources/logback-test.xml`

## Steps to run test 
1. update db.url in `config.testing/application.properties` and `config.testing/flyway-config.properties`
1. create test db `mvn clean package -Dflyway.configFiles=target\classes\flyway-config.properties flyway:migrate -P testing`
1. run `mvn test -Dmaven.test.skip=false -P testing`

# Health check

We using spring-actuator to provide health check endpoint

`curl localhost:8888/actuator/health`

ref: https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html


# Swagger2 for API doc

Swagger2 default enable for profile `local,dev`

after server start go `http://localhost:8888/swagger-ui.html`

# SSL

create key: `keytool -genkey -alias selfsigned_localhost_ssl -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore local_ssl.p12 -validity 3650 -keypass local_ssl -storepass local_ssl`

place to resources/

uncomment below in application.properties

```
#server.ssl.enabled=true
#http.port=8888
#server.port=8444
#server.ssl.key-store=classpath:local_ssl.p12
#server.ssl.key-store-password=local_ssl
#server.ssl.key-store-type=PKCS12
#server.ssl.key-password=local_ssl
```

# Spring-boot admin

There is very good monitoring console using actuator endpoints to visualize data 

http://codecentric.github.io/spring-boot-admin/2.0.3/#getting-started

# Hotswap

Make use of `spring-boot-devtools`, when start with `mvn clean install -P local spring-boot:run`, we can call `mvn install` in another console, the app will restart

In Intellij, we need start the project with run configuration `spring-boot-play [spring-boot:run]`, create you own if not exist, then follow https://stackoverflow.com/a/12744431/5824101 to enable auto compile 

# Project Design

# Spring File Integration

configure FileIntegrationConfig to set up channel, channel chain now is
 
 `inFileChannel->processFileChannel->${businessLogicChannel}->doneFileChannel`
 
tutorial: https://dzone.com/articles/message-processing-spring

doc: https://docs.spring.io/spring-integration/reference/html/files.html

# RestTemplate

add all customize to `RestClientConfig` using RestTemplateBuilder to build the template.

for the client store business logic, add new class to path `com.example.springbootplay.httpclient`

default we have LogRequestDetailHttpRequestInterceptor for all http request out bound. If need special handing, need add the Interceptor when injecting RestTemplate bean

# Config properties

https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html