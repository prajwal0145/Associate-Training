# Blogging Platform RESTful Web Service

This project is a RESTful web service for a blogging platform, built using Spring Boot. It allows users to create accounts, write blog posts, and leave comments on posts. The application is configured with different profiles for development (`dev`) and production (`prod`) environments.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Setup and Installation](#setup-and-installation)
3. [API Endpoints](#api-endpoints)
4. [Environment Profiles](#environment-profiles)
5. [How to Run the Application](#how-to-run-the-application)
6. [Accessing the Database](#accessing-the-database)

## Prerequisites

Before you begin, ensure you have the following installed:
* **Java Development Kit (JDK)**: Version 17 or later.
* **Apache Maven**: For dependency management and building the project.
* **An IDE**: IntelliJ IDEA is recommended.
* **An API Client**: Postman or Insomnia for testing the endpoints.
* **(Optional for Production)**: A running instance of PostgreSQL.

## Setup and Installation

1. **Clone the repository:**

```bash
git clone <your-repository-url>

Open in your IDE: Open the project as a Maven project in IntelliJ IDEA. The IDE will automatically resolve and download all the necessary dependencies listed in the pom.xml file.


API Endpoints
The following endpoints are available:
Users

Create a User

POST /users
Creates a new user account.
Request Body:

{
  "username": "johndoe",
  "password": "password123",
  "email": "johndoe@example.com"
}



Posts

Create a Post

POST /posts
Creates a new blog post. The author must exist first.
Request Body:

{
  "title": "My First Blog Post",
  "content": "This is the content of my very first post!",
  "author": {
    "id": 1 
  }
}



Get All Posts

GET /posts
Retrieves a paginated list of all posts.
Query Parameters (Optional):

authorId (long): Filter posts by the author's ID.
page (int): The page number to retrieve (default: 0).
size (int): The number of posts per page (default: 10).


Example: GET /posts?authorId=1&page=0&size=5


Get a Single Post

GET /posts/{id}
Retrieves a specific post by its ID.



Comments

Create a Comment

POST /posts/comments
Adds a new comment to a specific post. The author of the comment must exist.
Request Body:


json{
  "content": "This is a really insightful post!",
  "author": {
    "id": 1
  }
}




Environment Profiles
This application uses Spring Profiles to manage different configurations for development and production.
dev Profile (Default)
This is the default profile, designed for local development and testing.

Port: 8081
Database: H2 In-Memory Database (data is erased on every application restart).
Activation: Active by default. No special configuration is needed to run in this mode.

prod Profile
This profile is intended for a live, production deployment.

Port: 8080
Database: MySQL (persistent data).
Configuration: You must configure your MySQL connection details in the src/main/resources/application-prod.properties file.
Activation: Must be activated explicitly when running the application.

How to Run the Application
Running with the dev Profile (Default)
Simply run the BloggingPlatformApplication.java main class from your IDE. The application will start on http://localhost:8081.
Running with the prod Profile
Using IntelliJ IDEA:

Click on the project name near the Run/Debug buttons and select "Edit Configurations...".
In the "Active profiles" field, enter prod.
Click Apply and then Run. The application will start on http://localhost:8080.

Using Maven from the Command Line:
bashmvn spring-boot:run -Dspring-boot.run.profiles=prod
Accessing the Database
When running in the dev profile, you can access the H2 in-memory database console to view the tables and data.

Ensure the application is running in the dev profile.
Open your web browser and navigate to: http://localhost:8081/h2-console
Use the following credentials to log in:

JDBC URL: jdbc:h2:mem:devdb
User Name: sa
Password: password