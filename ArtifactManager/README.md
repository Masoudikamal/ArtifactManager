# ArtifactManager

A simple Java console application for storing and retrieving information about historical artifacts.  
The project demonstrates object-oriented programming principles (inheritance, polymorphism, encapsulation) and database interaction via JDBC.

## Features
- Import data about artifacts from text files
- Store and retrieve records from a MySQL database
- Menu-driven console interface
- Object-oriented design with abstract base class and subclasses

## Requirements
- Java 21 (works with 17+)
- MySQL
- JDBC driver

## Setup
1. Create a MySQL database (e.g. `artifactdb`).
2. Import the schema from `src/funn.sql`.
3. Copy `files/db.properties.example` to `files/db.properties` and set your username/password.
4. Compile and run:
```bash
javac -d bin src/*.java
java -cp bin HovedProgram
