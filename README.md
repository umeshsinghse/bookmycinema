# bookmycinema
Book My Cinema Springboot Microservices Application

The Book My Cinema project is a Spring Boot implementation of the backend APIs for a ticket booking system similar to the popular platform "BookMyShow". It provides a set of RESTful APIs that enable client applications to interact with the ticket booking system and perform various operations.

## Features
* **Api Gateway:** All api is routing through spring cloud gateway.
* **Authentication servie:** Centralized authentication server with implmententation of JWT (Register,Tokent and Validate).
* **Movie Management:** Admin users can add, edit, and remove movie from the system.
* **Theater Management:** Admin users can add, allocate seats, edit, and remove Theaters from the system.
* **User Registration:** Users can create an account, log in, and manage their profile information.
* **Movie Management:** Admin users can add, edit, and remove movie from the system.
* **Theater Management:** Admin users can add, allocate seats, edit, and remove Theaters from the system.
* **Ticket Booking:** Users can browse through the available movie, select the desired event, and book tickets for it.
* **Seat Selection:** Users can choose their preferred seats from the available options for a selected event.
* **Booking History:** Users can view their booking history and check the details of their past bookings.
* **Email Notifications:** Users receive email notifications for successful bookings and important updates.
## Technologies Used
* **Java 17**
* **Spring Boot 3.2.8**
* **Spring MVC**
* **Spring Data JPA**
* **MySQL (as the database)**
* **Docker**
* **Kubernete**
* **Helm Charts**
* **Maven (for dependency management)**
* **SMTP Server (for sending email notifications)**



## Getting Started
To set up the project on your local machine, follow these steps:

1. Clone the repository: `git clone https://github.com/umeshsinghse/bookmycinema.git`
2. Navigate to the project directory: `cd bookmycinema`
3. Configure the database settings in `application.yml` file.
4. Build the project using Maven: `mvn clean install`
5. Build the Docker image " Docker build . imagename.{1.0}"
6. Tag the Docker image " Docker tag imagename.{1.0} imagename.{1.0}"
7. Push the Docker image " Docker push imagename.{1.0}"
8. Create the Helm Chart " helm create helm-bmc"
9. Install the helm charts " helm install helm-bmc-service helm-bmc"

## Application Sequence
api-gateway-server - http://localhost:9000
authenticaiton-service http://localhost:9001
api-loadbalancer-server http://localhost:8761
movie-service http://localhost:8080
show-service http://localhost:8082
theater-service http://localhost:8083
ticket-service http://localhost:8084

## Database Setup
This project uses MySQL as the database. Follow these steps to set up the database:
1. Install MySQL on your local machine.
2. Create a new database named bookmyshow.
3. Update the database configuration in `application.properties` file.
## API Documentation
The API documentation for this project can be found at `http://localhost:8080/swagger-ui.html`. It provides detailed information about each API, including request/response formats and parameters.