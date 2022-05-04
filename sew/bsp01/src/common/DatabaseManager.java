package common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager implements AutoCloseable {

    private Connection connection;
    private LessonDAOInterface lessonDAO;

    public DatabaseManager(String propertiesFile) throws SQLException, IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(propertiesFile))) {
            Properties properties = new Properties();
            properties.load(bis);
            initialize(properties.getProperty("lin-url"), properties.getProperty("username"), properties.getProperty("password"));
        }
    }

    public DatabaseManager(String url, String username, String password) throws SQLException {
        initialize(url, username, password);
    }

    private void initialize(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        lessonDAO = new LessonDAO(this);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) connection.close();
        if (lessonDAO != null) lessonDAO.close();
    }

    protected Connection getConnection() {
        return connection;
    }

    public LessonDAOInterface getLessonDAO() {
        return lessonDAO;
    }
}
