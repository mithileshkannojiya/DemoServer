# DemoServer
This project is a Spring Boot application that exposes several web services. It uses an H2 in-memory database to store the input data provided via XML. The web services allow clients to send XML payloads, which are then processed and saved to the H2 database.

## Features

- **Endpoints**: Provides multiple web services for different functionalities.
- **XML-based Web Services**: Accepts XML input either through direct requests or file uploads.
- **H2 Database**: Stores the input data in memory.


## How to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/mithileshkannojiya/DemoServer.git

2. **Navigate to the project directory:**

   ```bash
      cd DemoServer
3. **Run the application:**

   ```bash
      mvn spring-boot:run

The server will start at http://localhost:8080.

## H2 Database
**You can access the H2 database console at**

   ```bash
      http://localhost:8080/h2-console
   ```
## Default connection parameters:
   ```bash
      JDBC URL: jdbc:h2:mem:testdb
      Username: sa
      Password: (leave it blank)
   ```
### API Endpoints
1. 
   ```bash
     /order/save
      Method: POST
   ```
####   Description: 
Accepts an XML payload or file and saves the data in the H2 database.
####   Example Request:
   ```bash
            <order>
              <id>123</id>
              <name>Sample Order</name>
              <quantity>10</quantity>
            </order>
   ```
2.
   ```bash
   /order/get/{id}
   Method: GET
   ```
#### Description: 
Retrieves the order details for the specified id.

3. 
```bash
   /order/delete/{id}
   Method: DELETE
```
#### Description: 
   Deletes the order with the specified id from the database.
###   Project Structure

   ```bash
src/
├── main/
│   ├── java/
│   │   ├── com.I2R.DemoServer/
│   │   │   ├── Controller/
│   │   │   │   └── OrderController.java
│   │   │   ├── Dto/
│   │   │   │   └── OrderParserDto.java
│   │   │   └── Entity/
│   │   │   │   └── OrderData.java
│   │   │   └── Repository/
│   │   │   │   └── OrderDataRepository.java
│   │   │   └── ServerConfiguration/
│   │   │   │   └── ServerConfiguration.java
│   │   │   └── Services/
│   │   │   │   └── OrderService.java
│   └── resources/
│       ├── application.properties
│       └── data.sql (optional)
└── test/
    └── java/
        └── com.I2R.DemoServer/
```

Client sends an XML request via POST.
Server processes the XML input through an OrderController.
The input data is mapped to an OrderData entity.
Data is saved in the H2 database.
Data can be retrieved or deleted using GET and DELETE requests.
