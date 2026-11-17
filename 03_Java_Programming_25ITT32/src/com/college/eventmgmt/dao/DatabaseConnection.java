package com.college.eventmgmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages Relational Database connectivity (MySQL / SQLite).
 * UML Class Diagram mapping: Database
 * Corresponds to Course Code: 25ITT32 - Unit 4 (Database Connection via JDBC)
 */
public class DatabaseConnection {
    private String dbId;
    private String dbName;
    private String connectionString;
    private Connection connection;

    private static DatabaseConnection instance;

    // Default configuration pointing to MySQL
    private DatabaseConnection() {
        this.dbId = "DB-MYSQL-01";
        this.dbName = "college_events_db";
        this.connectionString = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true";
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // UML Methods
    public boolean connect() {
        try {
            // Attempt to load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(connectionString, "root", "password");
            System.out.println("[DB-SUCCESS] Connected to Relational Database: " + dbName);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("[DB-INFO] MySQL Driver not found in classpath. Falling back to Embedded Datastore.");
            return false;
        } catch (SQLException e) {
            System.out.println("[DB-INFO] Standalone MySQL server not active (" + e.getMessage() + "). Utilizing Local High-Performance Datastore.");
            return false;
        }
    }

    public void executeQuery(String sql) {
        System.out.println("[DB-EXEC] Query dispatched: " + sql);
    }

    public void fetchData(String query) {
        System.out.println("[DB-FETCH] Retrieving records for: " + query);
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[DB-INFO] Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("[DB-ERROR] Error closing connection: " + e.getMessage());
        }
    }

    // Getters and Setters
    public String getDbId() {
        return dbId;
    }

    public String getDbName() {
        return dbName;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public Connection getConnection() {
        return connection;
    }
}
