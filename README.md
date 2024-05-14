# Personal Expense Tracker

## Technologies Used:
- Java 11
- Spring Boot
- Hibernate
- MySQL
- Git
- Vue

## Description of the Idea
An application which enables users to quickly and easily log their expenses over time as well as review and analyse the collected data.

## Main Functionalities
- Register
- Login
- Logout
- Add new expense
- View expense
- Edit expense
- Delete expense
- Rename category
- View all expenses - filters for period and category
- Analyse spending - some data visualization

## REST Endpoints
- GET    /             - show landing page / dashboard 
		       (depending on authentication status)

- POST   /register     - register
- POST   /login        - log in
- GET    /logout       - log out

- POST   /expenses     - add new expense
- GET    /expenses     - view all expenses
- GET    /expense/{id} - view expense
- PUT    /expense/{id} - edit expense
- DELETE /expense/{id} - delete expense

- PATCH  /category/{id}  - change category name

## Database Structure

users
- id (BIGINT PK NN)
- name (VARCHAR NN) 
- email (VARCHAR NN UQ)
- password (VARCHAR NN)

categories
- id (BIGINT PK NN)
- title (VARCHAR NN UQ)

expenses
- id (BIGINT PK NN)
- date (DATE)
- description (VARCHAR)
- amount (DOUBLE NN)
- category_id (BIGINT FK NN)
- user_id (BIGINT FK NN)


