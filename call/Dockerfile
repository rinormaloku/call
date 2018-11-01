FROM openjdk:8-jdk-alpine
# Environment Variable that defines the endpoint of sentiment-analysis python api.
ENV HTTPBIN_URL http://localhost:8000
ADD target/call-0.0.1.jar /
EXPOSE 8080
CMD ["java", "-jar", "call-0.0.1.jar"]