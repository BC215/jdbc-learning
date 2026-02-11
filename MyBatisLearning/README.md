# MyBatis Learning Project

Comprehensive MyBatis framework learning project with Oracle Database integration.

## Prerequisites
- JDK 11 or higher
- Oracle Database
- mybatis-3.5.17.jar
- ojdbc11.jar (Oracle JDBC Driver)

## Project Structure
```
MyBatisLearning/
├── lib/
│   ├── mybatis-3.5.17.jar       # MyBatis framework
│   └── ojdbc11.jar              # Oracle JDBC driver
├── src/
│   └── main/
│       ├── java/
│       │   └── com/mybatis/
│       │       ├── entity/
│       │       │   └── User.java            # Entity/POJO class
│       │       ├── mapper/
│       │       │   └── UserMapper.java      # Mapper interface
│       │       └── MyBatisDemo.java         # Main demo application
│       └── resources/
│           ├── mybatis-config.xml           # MyBatis configuration
│           └── mapper/
│               └── UserMapper.xml           # SQL mapper file
└── bin/                          # Compiled classes
```

## Setup Instructions

### 1. Download Required JARs

**MyBatis 3.5.17:**
- Download from: https://github.com/mybatis/mybatis-3/releases
- Maven Central: https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.17

**Oracle JDBC Driver:**
- Download ojdbc11.jar from Oracle's website
- Place both JARs in the `lib/` directory

### 2. Database Setup

Create the users table in your Oracle database:
```sql
CREATE TABLE users (
    id NUMBER(10) PRIMARY KEY,
    username VARCHAR2(50) NOT NULL,
    email VARCHAR2(100),
    age NUMBER(3)
);
```

### 3. Configure Database Connection

Edit `src/main/resources/mybatis-config.xml`:
```xml
<property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"/>
<property name="username" value="your_username"/>
<property name="password" value="your_password"/>
```

### 4. Import in Eclipse
1. File → Import → Existing Projects into Workspace
2. Select this directory
3. Eclipse will use .classpath to configure dependencies

## MyBatis Architecture

### 1. Configuration Layer
- **mybatis-config.xml**: Global MyBatis configuration
  - Database connection settings
  - Type aliases
  - Mapper locations

### 2. Entity Layer
- **User.java**: Plain Java object (POJO) representing database table

### 3. Mapper Layer
- **UserMapper.java**: Interface defining data access methods
- **UserMapper.xml**: SQL statements and result mappings

### 4. Application Layer
- **MyBatisDemo.java**: Demonstrates MyBatis usage

## Key Concepts

### SQL Mapper (UserMapper.xml)
```xml
<select id="selectUserById" parameterType="int" resultMap="UserResultMap">
    SELECT * FROM users WHERE id = #{id}
</select>
```

### Mapper Interface (UserMapper.java)
```java
User selectUserById(int id);
```

### Using MyBatis (MyBatisDemo.java)
```java
SqlSession session = sqlSessionFactory.openSession();
UserMapper mapper = session.getMapper(UserMapper.class);
User user = mapper.selectUserById(1);
```

## Running the Demo

### Compile
```bash
javac -d bin -cp "lib/mybatis-3.5.17.jar:lib/ojdbc11.jar" \
  src/main/java/com/mybatis/*.java \
  src/main/java/com/mybatis/entity/*.java \
  src/main/java/com/mybatis/mapper/*.java
```

### Copy Resources
```bash
cp -r src/main/resources/* bin/
```

### Run
```bash
java -cp "bin:lib/mybatis-3.5.17.jar:lib/ojdbc11.jar" com.mybatis.MyBatisDemo
```

## MyBatis Advantages

1. **Eliminates JDBC Boilerplate**: No manual ResultSet processing
2. **SQL Control**: Write SQL explicitly (unlike full ORMs)
3. **Dynamic SQL**: Build queries dynamically
4. **Caching**: First and second-level caching
5. **Type Safety**: Strong typing with mapper interfaces
6. **Easy Testing**: Simple to mock mapper interfaces

## Learning Path

1. **Basic Configuration**: Understand mybatis-config.xml
2. **Simple CRUD**: Start with basic operations
3. **Result Maps**: Learn complex object mappings
4. **Dynamic SQL**: Use if/choose/foreach for dynamic queries
5. **Association & Collection**: Map one-to-many relationships
6. **Caching**: Understand MyBatis caching strategies
7. **Plugins**: Extend MyBatis functionality

## References
- [MyBatis Official Documentation](https://mybatis.org/mybatis-3/)
- [MyBatis GitHub Repository](https://github.com/mybatis/mybatis-3)
- [MyBatis Dynamic SQL](https://mybatis.org/mybatis-3/dynamic-sql.html)

## Common Issues

**Resources not found:**
- Ensure resources folder is in the classpath
- Check mybatis-config.xml path in code

**Mapper binding errors:**
- Verify mapper namespace matches interface fully qualified name
- Check mapper registration in mybatis-config.xml
