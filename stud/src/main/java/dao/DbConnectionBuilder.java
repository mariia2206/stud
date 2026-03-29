package dao;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionBuilder implements ConnectionBuilder {

    public DbConnectionBuilder() {
        try {
            String driverClass = ConnectionProperty.getProperty("db.driver.class");
            Class.forName(driverClass);
            System.out.println("PostgreSQL Driver loaded: " + driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                "Database driver not found. Check: 1) driver JAR in classpath, 2) db.driver.class in config.properties", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}
