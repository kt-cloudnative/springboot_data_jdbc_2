FROM  maven:3.8.4-openjdk-17 AS MAVEN_BUILD


RUN mkdir -p build
WORKDIR /build


COPY pom.xml ./
COPY src ./src                             
COPY mvnw ./         
COPY . ./
#RUN chmod 777 ./mvnw clean package -Dmaven.test.skip=true

#COPY .mvn .mvn                                               
#COPY mvnw .                                                  
#COPY pom.xml .                                               
#COPY src src                                                 

RUN ./mvnw -B package

COPY --from=MAVEN_BUILD /build/target/*.jar app.jar

COPY /build/target/*.jar app.jar

ENV TZ Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV SPRING_PROFILES_ACTIVE dev

ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -XshowSettings:vm"
ENV JAVA_OPTS="${JAVA_OPTS} -XX:+UseG1GC -XX:+UnlockDiagnosticVMOptions -XX:+G1SummarizeConcMark -XX:InitiatingHeapOccupancyPercent=35 -XX:G1ConcRefinementThreads=20"

#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar  app.jar "]
ENTRYPOINT ["sh", "-c", "java -jar  app.jar "]
