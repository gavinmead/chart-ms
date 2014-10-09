FROM gavinmead/javadev:0.1

#Install phantomjs
RUN apt-get install -y phantomjs

ADD ./src /app/src
ADD pom.xml /app/pom.xml

WORKDIR /app

RUN mvn clean package -DskipTests

ENTRYPOINT java -jar target/chart-ms-service-1.0.0-SNAPSHOT.jar --server.port=8081
