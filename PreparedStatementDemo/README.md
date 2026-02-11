# PreparedStatement Demo Project

Advanced JDBC learning project focusing on PreparedStatement, SQL injection prevention, and transaction management.

## Prerequisites
- JDK 11 or higher
- Oracle Database
- ojdbc11.jar (Oracle JDBC Driver)

## Project Structure
```
PreparedStatementDemo/
├── lib/
│   └── ojdbc11.jar                    # Oracle JDBC Driver
├── src/
│   └── com/jdbc/prepared/
│       ├── PreparedStatementDemo.java      # Basic PreparedStatement usage
│       ├── SQLInjectionPrevention.java     # Security best practices
│       └── TransactionDemo.java            # Transaction management
└── bin/                                # Compiled classes
```

## Learning Topics

### 1. PreparedStatementDemo.java
- Parameter binding with PreparedStatement
- Reusing PreparedStatement for efficiency
- Batch processing for bulk operations

**Key Concepts:**
```java
PreparedStatement pstmt = conn.prepareStatement("INSERT INTO table VALUES (?, ?)");
pstmt.setInt(1, value1);
pstmt.setString(2, value2);
pstmt.executeUpdate();
```

### 2. SQLInjectionPrevention.java
- Demonstrates how PreparedStatement prevents SQL injection
- Comparison with unsafe String concatenation
- Best practices for handling user input

**Security Benefit:**
PreparedStatement treats all parameters as literal values, preventing malicious SQL code injection.

### 3. TransactionDemo.java
- Transaction management with commit/rollback
- ACID properties in practice
- Error handling and recovery

**Transaction Pattern:**
```java
conn.setAutoCommit(false);  // Start transaction
try {
    // Multiple database operations
    conn.commit();           // Commit on success
} catch (SQLException e) {
    conn.rollback();         // Rollback on error
}
```

## Running the Examples

### Compile
```bash
javac -d bin -cp lib/ojdbc11.jar src/com/jdbc/prepared/*.java
```

### Run PreparedStatement Demo
```bash
java -cp bin:lib/ojdbc11.jar com.jdbc.prepared.PreparedStatementDemo
```

### Run SQL Injection Prevention Demo
```bash
java -cp bin:lib/ojdbc11.jar com.jdbc.prepared.SQLInjectionPrevention
```

### Run Transaction Demo
```bash
java -cp bin:lib/ojdbc11.jar com.jdbc.prepared.TransactionDemo
```

## Key Advantages of PreparedStatement

1. **Security**: Prevents SQL injection attacks
2. **Performance**: Pre-compiled SQL statements are cached
3. **Readability**: Cleaner code with parameter binding
4. **Type Safety**: Automatic type conversion
5. **Batch Processing**: Efficient bulk operations

## Best Practices

- ✅ Always use PreparedStatement for user input
- ✅ Close resources in finally block or use try-with-resources
- ✅ Use transactions for multiple related operations
- ✅ Handle exceptions appropriately with rollback
- ✅ Use batch updates for bulk operations

## References
- [PreparedStatement Java Docs](https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html)
- [JDBC Transaction Management](https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html)
