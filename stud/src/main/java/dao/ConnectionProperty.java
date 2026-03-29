package dao;

import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperty {
    private static final Properties PROPERTY_CONFIG = new Properties();
    
    static {
        try {
            // Загружаем как ресурс из classpath
            InputStream input = ConnectionProperty.class.getClassLoader()
                .getResourceAsStream("config.properties");
            
            if (input == null) {
                System.err.println("config.properties not found in classpath!");
                System.err.println("Classpath: " + System.getProperty("java.class.path"));
                throw new RuntimeException("config.properties not found");
            }
            
            PROPERTY_CONFIG.load(input);
            input.close();
            
            System.out.println("config.properties loaded successfully!");
            System.out.println("db.url=" + PROPERTY_CONFIG.getProperty("db.url"));
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
    
    public static String getProperty(String key) {
        return PROPERTY_CONFIG.getProperty(key);
    }
}