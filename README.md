# DemoServer

## Features

- **H2 Database**: Stores the input data in memory.
- **XML-based Web Services**: Accepts XML input either through direct requests or file uploads.

## How to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/mithileshkannojiya/DemoServer.git

Navigate to the project directory:

bash
Copy code
cd DemoServer
Run the application:

bash
Copy code
mvn spring-boot:run
The server will start at http://localhost:8080.

H2 Database
You can access the H2 database console at:

bash
Copy code
http://localhost:8080/h2-console
Default connection parameters:

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave it blank)
API Endpoints
1. /order/save
Method: POST
Description: Accepts an XML payload or file and saves the data in the H2 database.
Example Request:
xml
Copy code
<order>
  <id>123</id>
  <name>Sample Order</name>
  <quantity>10</quantity>
</order>
2. /order/get/{id}
Method: GET
Description: Retrieves the order details for the specified id.
3. /order/delete/{id}
Method: DELETE
Description: Deletes the order with the specified id from the database.
Project Structure
css
Copy code
src/
├── main/
│   ├── java/
│   │   ├── com.I2R.DemoServer/
│   │   │   ├── Controller/
│   │   │   │   └── OrderController.java
│   │   │   ├── Dto/
│   │   │   └── Entity/
│   │   │       └── OrderData.java
│   └── resources/
│       ├── application.properties
│       └── data.sql (optional)
└── test/
    └── java/
        └── com.I2R.DemoServer/
Interactive Diagram
This diagram visualizes the flow of XML input processing and its interaction with the H2 database:

Client sends an XML request via POST.
Server processes the XML input through an OrderController.
The input data is mapped to an OrderData entity.
Data is saved in the H2 database.
Data can be retrieved or deleted using GET and DELETE requests.
