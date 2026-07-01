# VetApp Project

A Java 8 desktop application for managing a veterinary office. This project was built as a capstone project and uses a MySQL database through XAMPP.

## Features

* Manage veterinarian records
* Manage customer records
* Store pet information
* Track pet species, type, age, and weight
* Schedule veterinary appointments
* Connect appointment records to customers and pets
* MySQL database schema included

## Tech Stack

* Java 8
* MySQL
* XAMPP
* SQL
* Desktop application architecture

## Database Overview

The database includes the following main tables:

* `vet`
* `customer`
* `pet_info`
* `calander_appointment`

Relationships include:

* A customer can have pets
* A pet belongs to a customer
* An appointment connects a customer and pet to a visit date/time

## Project Structure

```text
vetApp_Project/
├── vetApp/
├── vet office.sql
└── README.md
```

## Getting Started

### Requirements

* Java 8
* XAMPP
* MySQL
* IDE such as NetBeans, Eclipse, or IntelliJ IDEA

### Setup

1. Clone the repository:

```bash
git clone https://github.com/Mjn92/vetApp_Project.git
```

2. Open XAMPP and start MySQL.

3. Import the database:

```text
vet office.sql
```

4. Open the `vetApp` project folder in your Java IDE.

5. Update any database connection settings if needed.

6. Run the application.

## What I Learned

This project helped me practice:

* Java desktop application development
* SQL database design
* MySQL table relationships
* Foreign keys and relational data
* Connecting an application to a local database
* Building a complete capstone-style software project

## Portfolio Notes

This project shows my ability to build a full database-backed application using Java and MySQL. It demonstrates basic CRUD-style data management, database relationships, and local development using XAMPP.

## Future Improvements

* Rename database fields for cleaner spelling and consistency
* Add screenshots of the application
* Add login/authentication
* Add input validation
* Add search and filtering
* Add appointment editing and cancellation
* Improve UI layout
* Add export/report features
* Add unit tests
* Package the app for easier installation
