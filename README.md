# Twitter-API-Mgmt-Gateway

Simple Twitter API Gateway Function

* Application developed as a Java Web Application using Spring Framework and Twitter4j API.

Client Interface:
* Twitter Login
* Write Tweet
* Get Timeline
* Get Friends List

Pre-Requisite to Run App:

1. Twitter Application created.

2. Apache Tomcat.

3. A browser that supports iframes.

Instructions to Run:

1. Open ActionServlet.java present in Twitter-API-Mgmt-Gateway/APIMgmtGW/src/java/com/arihant15/

2. Enter the Consumer Key and Consumer Secret present in line 33 and 34.

3. In Twitter Application that you have created, Provide call back URL as: http://public-ip:tomcat-port/Application-Name/callback.arihant15

	eg: http://192.162.1.128:8080/APIMgmtGW/callback.arihant15

4. Run the project.
