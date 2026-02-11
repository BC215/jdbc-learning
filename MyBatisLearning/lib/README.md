# MyBatis Library Dependencies

This folder should contain the following JAR files:

## mybatis-3.5.17.jar
**MyBatis SQL Mapper Framework**

### Download from:
- GitHub Releases: https://github.com/mybatis/mybatis-3/releases/tag/mybatis-3.5.17
- Maven Central: https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.17

### Maven Coordinates:
```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.17</version>
</dependency>
```

### File Details:
- **Filename**: mybatis-3.5.17.jar
- **Approximate Size**: ~750 KB
- **License**: Apache License 2.0

---

## ojdbc11.jar
**Oracle JDBC Driver for JDK 11+**

### Download from:
- Official Oracle: https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html
- Maven Central: https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11

### Maven Coordinates:
```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
    <version>21.9.0.0</version>
</dependency>
```

### File Details:
- **Filename**: ojdbc11.jar
- **Approximate Size**: ~4-5 MB
- **License**: Oracle Free Use Terms and Conditions (FUTC)

---

## Usage
After downloading both JAR files:
1. Place them in this directory
2. The Eclipse .classpath file is already configured to reference these files
3. No additional configuration needed

## Alternative: Maven/Gradle
For production projects, consider using dependency management tools:
- Maven: Add dependencies to pom.xml
- Gradle: Add dependencies to build.gradle
