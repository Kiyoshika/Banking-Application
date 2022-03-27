# Banking-Application
Sample banking application to refresh my Java Spring skills.

This is a mock application to create various accounts (checking, saving, etc.), create (fake) debit cards, simulate transactions/deposits and sending money to other bank users.

### Current Features
* self-written authentication page for registering/logging in

### Upcoming Features
* view fake balance
* register fake debit cards
* generate fake transactions and deposits
* creation of multiple accounts (checking, savings, etc.)
* send/receive money to other bank account users

# Backend
Backend written in Java using Spring.

A PostgreSQL database is used to store the data.

### Setting up databases
For the live application, a database named `bankingapp` must be created.

For running tests, a database named `bankingapp_test` must be created.

### Setting up database credentials
I'm using environment variables for the application properties when configuring database access.

Set the following environment variables for server to connect to database:
* `export SPRING_DB_USER=...`
* `export SPRING_DB_PASS=...`

# Frontend
Frontend is simply HTML/CSS and vanilla JavaScript. Don't feel like bothering with any fancy frameworks.
