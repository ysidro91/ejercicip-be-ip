FROM maven as builder

COPY . /app

RUN cd /app && mvn install -DskipTests

FROM openjdk

COPY --from=builder /app/target/ejercicio-be-ip-1.0.0.jar /opt/backend.jar

CMD java -jar /opt/backend.jar