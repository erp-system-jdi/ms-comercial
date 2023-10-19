FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-arm64

COPY target/*.jar app.jar

EXPOSE 8081

CMD [ "java", "-jar", "/app.jar" ]
