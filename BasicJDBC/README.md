# Basic JDBC Learning Project

This project demonstrates fundamental JDBC operations using Oracle Database.

## Prerequisites
- JDK 11 or higher
- Oracle Database (Oracle XE recommended for learning)
- ojdbc11.jar (Oracle JDBC Driver)

## Project Structure
```
BasicJDBC/
├── lib/
│   └── ojdbc11.jar          # Oracle JDBC Driver
├── src/
│   └── com/jdbc/basic/
│       ├── OracleConnectionDemo.java    # Basic connection example
│       └── CRUDOperations.java          # Create, Read, Update, Delete examples
└── bin/                      # Compiled classes (generated)
```

## Setup Instructions

### 1. Download ojdbc11.jar
Download the Oracle JDBC driver from:
- [Oracle Maven Repository](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)
- [Maven Central](https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11)

Place the JAR file in the `lib/` directory.

### 2. Import in Eclipse
1. File → Import → Existing Projects into Workspace
2. Select this project directory
3. The .classpath file will automatically configure the ojdbc11.jar dependency

### 3. Configure Database Connection
Update the connection parameters in the Java files:
```java
private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
private static final String USER = "your_username";
private static final String PASS = "your_password";
```

## Running the Examples

### OracleConnectionDemo
Simple connection test that queries the current database date:
```bash
java -cp bin:lib/ojdbc11.jar com.jdbc.basic.OracleConnectionDemo
```

### CRUDOperations
Demonstrates all basic database operations:
```bash
java -cp bin:lib/ojdbc11.jar com.jdbc.basic.CRUDOperations
```

## Learning Objectives
- Establish JDBC connection to Oracle Database
- Execute SQL queries using Statement
- Process ResultSet data
- Perform CRUD operations
- Proper resource management and exception handling

## References
- [Oracle JDBC Documentation](https://docs.oracle.com/en/database/oracle/oracle-database/21/jjdbc/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
