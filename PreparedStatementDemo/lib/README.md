# JDBC Library Dependencies

This folder should contain the following JAR files:

## ojdbc11.jar
**Oracle JDBC Driver for JDK 11+**

### Download from:
- Official Oracle: https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html
- Maven Central: https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11

### Version Compatibility:
- Requires JDK 11 or higher
- Compatible with Oracle Database 12c, 18c, 19c, 21c, and later

### Maven Coordinates:
```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
    <version>21.9.0.0</version>
</dependency>
```

## Usage
After downloading, place `ojdbc11.jar` in this directory. The Eclipse .classpath file is already configured to reference this location.
