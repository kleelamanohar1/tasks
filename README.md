# Pre-requisites
- JDK 17+ 
- Maven 3.7+ q


# Build and Deploy:
- Clone the repository
  - Run command : '_git clone https://github.com/kleelamanohar1/tasks.git_'
    - validate if a folder tasks would be created
* Run the below the commands:
  - Run command : '_mvn clean install_'
    - Validate If automated test cases ran successfully and build was successful by seeing below message 
      - 'Tests run: 13, Failures: 0, Errors: 0, Skipped: 0'
      - BUILD SUCCESS
  - Run command : '_mvn spring-boot:run_'
    - Validate if the message 'Started MainApplication in .. seconds (process running for ..)' is displayed
  
# Test:
   - Access the swagger through below url:
            http://localhost:8080/swagger-ui/index.html
     - Sample request for Tasks - Add service
            {
              "completed": false,
                "createdDate": "2023-10-23T13:46:19.888Z",
                "completedDate": "2023-10-23T13:46:19.888Z",
                "title": "Run_Events",
                "description": "Task to run events processing"
            }
    