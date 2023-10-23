# Pre-requisites
- JDK 17 
- Maven
- Spring Boot
- Docker

# Build and Deploy:
- Unzip product-orders.zip
  - Navigate to root folder: 
    * Edit docker-compose.yaml and set the postgres db username, password and url (_Sample parameters are shown below:_)
                  _SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/productorders_,
                  _SPRING_DATASOURCE_USERNAME=postgres_,
                  _SPRING_DATASOURCE_PASSWORD=postgres_
* Run the below the commands:
  - command :_mvn clean install_
    - Validate If automated test cases ran successfully:
    
  - command: _docker build -t purchase-orders ._ 
    - Validate if you see similar output as below:
    - ![img.png](img.png)
  -  command: _docker-compose up_
    - Validate if you see similar output as below:
    - ![img_2.png](img_2.png)
# Test:
   - Access the swagger through below url:
            http://localhost:8080/swagger-ui/index.html
     - Sample request for Products - Add service
     ![img_3.png](img_3.png)
     - Sample request for Orders - Add service
     - ![img_4.png](img_4.png)
     - Sampke request for Order Items - Add service
     - ![img_5.png](img_5.png)