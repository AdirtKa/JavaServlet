# Use the official Tomcat base image
FROM tomcat:10.1.33-jdk17

# Set the working directory in the container
WORKDIR /usr/local/tomcat

# Copy the WAR file from the target folder of your project into the webapps folder of Tomcat
COPY target/JavaServlet-1.0-SNAPSHOT.war webapps/JavaServlet.war

# Expose the Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
