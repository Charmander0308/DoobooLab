FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/dooboolab.jar
COPY "${JAR_FILE}" app.jar
ENTRYPOINT ["java"]
CMD ["-jar","app.jar"]
LABEL authors="yshan"
