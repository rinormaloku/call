FROM openjdk:8-jdk-alpine
ENV HTTPBIN_URL http://localhost:8000
ADD target/call-0.0.1.jar /
EXPOSE 8080
CMD ["java", "-jar", "call-0.0.1.jar"]
