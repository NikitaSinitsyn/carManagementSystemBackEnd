### Car Management System

This project is a Car Management System developed using Spring Boot, MySQL, and various other technologies.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## Introduction

The Car Management System is a robust platform developed to streamline the complexities of managing a fleet of vehicles efficiently.
Whether it's a small-scale car rental service or a large corporate fleet, this system offers a comprehensive solution to handle various aspects of car management seamlessly.

From adding new vehicles to tracking their maintenance schedules, managing insurance policies, overseeing repairs, and monitoring employee interactions, this system provides a centralized hub for all car-related activities. 
Its intuitive interface empowers users with the tools they need to make informed decisions and maintain optimal fleet performance.

With features like automated notifications for expiring insurance and maintenance dates, the system ensures proactive management, reducing the risk of oversight and maximizing operational uptime.
By leveraging modern technologies and best practices, the Car Management System offers reliability, scalability, and adaptability to meet the evolving needs of any car-centric business.

## Features
- Create, update, delete, and view information about cars
- Employee management
- Manage car Casko, Vignette, Civil Insurance, Technical Inspection, Repairs, and Tires
- Notification system that automatically checks information about expiring dates and sends emails
## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok
- MapStruct
- JavaMail API

## Getting Started

### Prerequisites

Before running the project, make sure you have the following installed:

- Java Development Kit (JDK)
- MySQL
- Maven

### Installation
Clone the repository:
git clone https://github.com/yourusername/car-management-system.git

Navigate to the project directory:
cd car-management-system

Configure MySQL:

Create a MySQL database named car_management.
Update the application.properties file with your MySQL credentials.

Build the project:
mvn clean install

Run the application:
mvn spring-boot:run

### Usage:
Access the application at: http://localhost:8080
Use the provided API endpoints to interact with the system programmatically.

### Contributing:
Contributions are welcome! Feel free to submit pull requests or open issues for any bugs, feature requests, or suggestions.
