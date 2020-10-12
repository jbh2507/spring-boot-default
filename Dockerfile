FROM maven:3.6.3-jdk-11

WORKDIR /windprofiler
RUN mkdir /windprofiler/target
COPY src /windprofiler/src
COPY pom.xml /windprofiler
COPY mvnw /windprofiler
COPY mvnw.cmd /windprofiler

RUN mvn package -DskipTests

EXPOSE 8000

ENTRYPOINT ["java","-jar","target/windprofilerweb-0.0.1-SNAPSHOT.jar"]