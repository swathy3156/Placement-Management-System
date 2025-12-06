Placement Management System

Overview
This is a Java-based Placement Management System that allows managing student registrations, company registrations, student applications to companies, marking selections, and generating simple reports. All data is stored in MySQL so it persists even when the program is closed.

Features

Register Students: Add new students with their details.

Register Companies: Add new companies with job roles, eligibility criteria, and number of openings.

Apply for Companies: Students can apply for companies they are eligible for.

Mark Student Selection: Companies can mark students as selected.

View Applications: Check which companies a student has applied to and the status of applications.

View Selected Students: Check which students have been selected for a particular company.

Java Files

MySQLDatabaseManager.java: Handles the connection to MySQL.

PlacementSystem.java: Contains the core logic for registering students and companies, applying, marking selection, and generating reports.

Main.java: Example program to run and test the system.

Setup Instructions

Install MySQL server on your computer.

Open MySQL client or Workbench and run the following commands to create the database and tables

Create the database
CREATE DATABASE placement_system;

Use the database
USE placement_system;

Create students table
CREATE TABLE students (
student_id VARCHAR(50) PRIMARY KEY,
name VARCHAR(100),
department VARCHAR(50),
year INT
);

Create companies table
CREATE TABLE companies (
company_id VARCHAR(50) PRIMARY KEY,
name VARCHAR(100),
role VARCHAR(100),
eligibility_criteria VARCHAR(100),
openings INT
);

Create applications table
CREATE TABLE applications (
student_id VARCHAR(50),
company_id VARCHAR(50),
application_date BIGINT,
status VARCHAR(20),
PRIMARY KEY(student_id, company_id),
FOREIGN KEY(student_id) REFERENCES students(student_id),
FOREIGN KEY(company_id) REFERENCES companies(company_id)
);

Update MySQLDatabaseManager.java with your MySQL username and password.

Compiling and Running the Project

Place all Java files in the src folder.

If not using Maven, add the MySQL Connector jar to your build path.

Compile from src folder

For Windows
javac -cp ".;../lib/mysql-connector-java-8.1.0.jar" *.java

For Linux or Mac
javac -cp ".:../lib/mysql-connector-java-8.1.0.jar" *.java

Run the program

For Windows
java -cp ".;../lib/mysql-connector-java-8.1.0.jar" Main

For Linux or Mac
java -cp ".:../lib/mysql-connector-java-8.1.0.jar" Main

Example Usage

Register students Alice and Bob

Register companies TechCorp and ElectroInc

Alice applies for TechCorp

Bob applies for ElectroInc

Mark Alice as selected for TechCorp

View applications of Alice

View students selected for TechCorp

Notes

Make sure MySQL server is running before executing the program.

Student, company, and application data are stored permanently in MySQL.

The system can easily be extended with reports or additional features.