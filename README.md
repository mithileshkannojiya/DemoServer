# Service_Virtualization_using_Wiremock

# Introduction
This project demonstrate the usage of **wiremock** as a stand-alone tool to create virtual services which can we used as an alternative way in testing and development process in case of unavailability of actual microservices or reducing the cost of project where hitting the actual end-points requires some cost.

## Service Virtualization :
Service virtualization is the process of creating replicas of systems that new applications depend onto   test   how   well   the   application   and   systems   integrate.   It   is   primarily   used   for   integrating applications   that   depend   on   cloud   and   service-oriented   architectures, or   applications   that communicate with third-party data and application program interface (APIs). Examples of these systems include customer relationship management (CRM) services such as Salesforce Service Cloud, enterprise resource planning (ERP) services such as SAP ECC, and internal systems that are still in development. 

## Requirement of service virtualization:
Service Virtualization eliminates constraints by creating simulations of needed systems and making them available throughout the software development lifecycle. Developers, testers, and performance teams work in parallel. The result is faster delivery, lower costs, and higher quality of innovative new software applications. 

## How does service virtualization work?
Service virtualization tools monitor traffic between the dependent system and the application. They use log data to build a model that can replicate the dependent system's responses and behavior, using inputs such as SQL statements for databases and Extensible Markup Language (XML) messages for web services. As developers test the new application, the virtualized service produces the same responses that the real one would. 

## Benefits of service virtualization Service 
virtualization benefits are most acute in rapid deployment and continuous delivery scenarios. It allows teams to rapidly iterate on test results throughout the development process, which can be a boon to DevOps and Agile teams. Service virtualization helps these teams prevent bugs during development, build better systems with fewer defects and share responsibility for testing the quality of the product across departments.


# Wiremock :
WireMock is a tool for mocking HTTP-based APIs that runs in the unit tests, on the desktop, or in the test environment. We can also say it is a simulator for HTTP-based APIs, considered a service virtualization tool or a mock server. It enables us to stay productive when an API we depend on
```
▪ Doesn’t exist 
▪ Isn’t complete 
▪ Costly to access
```
It supports the testing of Edge cases and failure modes. It’s fast so reduces build time significantly. In simple terms, Wiremock is a mocking setup for integration testing. It is mainly used during the development and more significantly during the Integration testing while a system or service talks to one or multiple external or internal dependencies/services.

## Features of WireMock
1.	Stubbing: It is a technique that allows configuring the HTTP response that is returned by the WireMock server when it receives a specific HTTP request. You can stub HTTP requests with WireMock by using the static givenThat() method of the WireMock class.
2.	Verification: The WireMock server registers all requests it receives in memory until it is reset. And that makes it possible to verify that a request matching a specific pattern was received, and also to fetch the requests’ details.
3.	Record-playback of interactions: WireMock can create stub mappings from requests it has received. Combined with its proxying feature this allows you to “record” stub mappings from interaction with existing APIs.
4.	Injection of faults and delays: One of the main reasons it’s beneficial to use web service fakes when testing is to inject faulty behavior that might be difficult to get the real service to produce on-demand.
5.	Simulation of Stateful Behavior: Most web services tend to have some state, which changes as you and others interact with them. So, it’s pretty useful to be able to simulate this when you’ve swapped a real service for a test double.
6.	Can be used as
◦ JVM library in unit testing.
◦ Run as a standalone process either on the same host or remote server or on the cloud.
7.	All of WireMock’s features are easily accessible via its REST (JSON) interface and its’ Java API.

Maven Dependency for WireMock
For Java 8:
```
<dependency>
    <groupId>com.github.tomakehurst</groupId>
    <artifactId>wiremock-jre8</artifactId>
    <version>2.33.2</version>
    <scope>test</scope>
</dependency>
```
For Java 8 standalone:
```
<dependency>
    <groupId>com.github.tomakehurst</groupId>
    <artifactId>wiremock-jre8-standalone</artifactId>
    <version>2.33.2</version>
    <scope>test</scope>
</dependency>
```


## Getting started
## How to Use Wiremock
Download wiremock standalone jar from the wirmock official web page.
https://wiremock.org/

To run wiremock standalone jar, execute the following command

```
Java -jar wiremock-jre8-standalone-2.34.0.jar
```


If mappings and files are already created and want to start wiremock with these resources
```
•	In windows copy mapping and _files folders in same directory where wiremock jar file is there
•	In Linux execute the following command to start wiremock with these resources
Java -jar wiremock-jre8-standalone-2.34.0.jar --load-resources-from-classpath 'resources'
```
To check whether mappings are working or not open any browser paste the following url and search it will show all the mappings (stubs) there.
http://localhost:8080/__admin/mappings


## Authors and acknowledgment
1. Mithilesh Kannojiya

## License
For open source projects, say how it is licensed.

## Project status

