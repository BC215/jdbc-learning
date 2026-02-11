# JDBC Learning Projects

A comprehensive collection of JDBC and MyBatis learning projects with Eclipse IDE support.

## 📚 Projects Overview

### 1. BasicJDBC
**Fundamental JDBC operations with Oracle Database**
- Database connection management
- Basic CRUD operations (Create, Read, Update, Delete)
- ResultSet processing
- Resource management best practices

**Technologies:** Java, JDBC, Oracle Database, ojdbc11.jar

[➡️ View BasicJDBC Project](./BasicJDBC/)

---

### 2. PreparedStatementDemo
**Advanced JDBC with security and transactions**
- PreparedStatement usage and parameter binding
- SQL injection prevention techniques
- Transaction management (commit/rollback)
- Batch processing for bulk operations

**Technologies:** Java, JDBC, Oracle Database, ojdbc11.jar

[➡️ View PreparedStatementDemo Project](./PreparedStatementDemo/)

---

### 3. MyBatisLearning
**MyBatis ORM Framework fundamentals**
- MyBatis configuration and setup
- SQL mapping with XML
- CRUD operations with MyBatis
- Entity and Mapper patterns
- Dynamic SQL capabilities

**Technologies:** Java, MyBatis 3.5.17, Oracle Database, ojdbc11.jar

[➡️ View MyBatisLearning Project](./MyBatisLearning/)

---

## 🛠️ Prerequisites

### Software Requirements
- **JDK**: Version 11 or higher
- **Oracle Database**: Oracle XE (Express Edition) recommended for learning
- **IDE**: Eclipse IDE for Java Developers (or any Java IDE)

### Required JAR Files
Each project uses a `lib/` folder containing necessary dependencies:

1. **ojdbc11.jar** - Oracle JDBC Driver
   - Download: [Oracle JDBC Downloads](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)
   - Maven: `com.oracle.database.jdbc:ojdbc11:21.9.0.0`

2. **mybatis-3.5.17.jar** - MyBatis Framework (MyBatisLearning project only)
   - Download: [MyBatis Releases](https://github.com/mybatis/mybatis-3/releases)
   - Maven: `org.mybatis:mybatis:3.5.17`

---

## 🚀 Getting Started

### Option 1: Eclipse IDE (Recommended)

1. **Clone this repository**
   ```bash
   git clone https://github.com/BC215/jdbc-learning.git
   cd jdbc-learning
   ```

2. **Download required JAR files**
   - Download ojdbc11.jar and mybatis-3.5.17.jar
   - Place them in respective project `lib/` folders (see lib/README.md in each project)

3. **Import into Eclipse**
   - File → Import → Existing Projects into Workspace
   - Select the repository root directory
   - Import all three projects
   - Eclipse will automatically configure classpath using `.classpath` files

4. **Configure database connection**
   - Update database URL, username, and password in each project's source files
   - Default connection: `jdbc:oracle:thin:@localhost:1521:xe`

5. **Run examples**
   - Right-click on any main class → Run As → Java Application

### Option 2: Command Line

Each project includes instructions for compiling and running from command line. See individual project README files.

---

## 📖 Learning Path

**Recommended order for beginners:**

1. **Start with BasicJDBC**
   - Understand JDBC fundamentals
   - Learn connection management
   - Practice basic CRUD operations

2. **Progress to PreparedStatementDemo**
   - Master PreparedStatement
   - Learn security best practices
   - Understand transaction management

3. **Advanced: MyBatisLearning**
   - Explore ORM concepts
   - Learn MyBatis framework
   - Build more maintainable data access layers

---

## 📂 Project Structure

```
jdbc-learning/
├── BasicJDBC/
│   ├── lib/                      # JAR dependencies
│   ├── src/                      # Java source files
│   ├── bin/                      # Compiled classes (generated)
│   ├── .project                  # Eclipse project file
│   ├── .classpath                # Eclipse classpath configuration
│   └── README.md                 # Project documentation
│
├── PreparedStatementDemo/
│   ├── lib/
│   ├── src/
│   ├── bin/
│   ├── .project
│   ├── .classpath
│   └── README.md
│
├── MyBatisLearning/
│   ├── lib/
│   ├── src/
│   │   └── main/
│   │       ├── java/             # Java source
│   │       └── resources/        # MyBatis config & mappers
│   ├── bin/
│   ├── .project
│   ├── .classpath
│   └── README.md
│
└── README.md                     # This file
```

---

## 🔑 Key Concepts Covered

### JDBC Fundamentals
- ✅ DriverManager and Connection
- ✅ Statement vs PreparedStatement
- ✅ ResultSet processing
- ✅ Exception handling
- ✅ Resource management (try-with-resources)

### Security
- ✅ SQL injection prevention
- ✅ Parameterized queries
- ✅ Input validation

### Performance
- ✅ PreparedStatement caching
- ✅ Batch operations
- ✅ Connection pooling concepts

### Transaction Management
- ✅ ACID properties
- ✅ Commit and rollback
- ✅ Savepoints

### MyBatis Framework
- ✅ XML-based SQL mapping
- ✅ Mapper interfaces
- ✅ Result mapping
- ✅ Dynamic SQL
- ✅ Configuration management

---

## 💡 Best Practices

1. **Always use PreparedStatement** for user inputs to prevent SQL injection
2. **Use try-with-resources** for automatic resource management
3. **Handle exceptions appropriately** with proper logging and rollback
4. **Close resources** in the correct order (ResultSet → Statement → Connection)
5. **Use transactions** for multiple related operations
6. **Consider using connection pooling** in production applications
7. **Use ORM frameworks** like MyBatis for complex applications

---

## 📚 Additional Resources

### Official Documentation
- [JDBC API Documentation](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
- [Oracle JDBC Developer's Guide](https://docs.oracle.com/en/database/oracle/oracle-database/21/jjdbc/)
- [MyBatis Documentation](https://mybatis.org/mybatis-3/)

### Tutorials
- [Oracle JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [MyBatis Getting Started](https://mybatis.org/mybatis-3/getting-started.html)

### Tools
- [Oracle Database XE Download](https://www.oracle.com/database/technologies/xe-downloads.html)
- [Eclipse IDE Download](https://www.eclipse.org/downloads/)

---

## 🤝 Contributing

Feel free to:
- Report issues
- Suggest improvements
- Add more learning examples
- Share your learning experiences

---

## 📝 License

These projects are for educational purposes. Please respect the licenses of third-party libraries:
- Oracle JDBC Driver: Oracle Free Use Terms and Conditions
- MyBatis: Apache License 2.0

---

## ⚠️ Important Notes

1. **JAR files are not included** in this repository due to licensing restrictions
2. You must download them separately from official sources
3. Each project's `lib/README.md` contains download instructions
4. Update database connection parameters before running examples
5. Ensure Oracle Database is running before executing any project

---

**Happy Learning! 🎓**
