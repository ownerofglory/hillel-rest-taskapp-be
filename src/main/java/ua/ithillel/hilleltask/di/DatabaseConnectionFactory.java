package ua.ithillel.hilleltask.di;

import org.glassfish.hk2.api.Factory;
import ua.ithillel.hilleltask.db.JdbcConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionFactory implements Factory<Connection> {
    private static String MYSQL_CONN_STR = System.getenv("HILLEL_TASK_APP_MYSQL_CONN_STR");
    private static String MYSQL_USER = System.getenv("HILLEL_TASK_APP_MYSQL_USER");
    private static String MYSQL_PASSWORD = System.getenv("HILLEL_TASK_APP_MYSQL_PASSWORD");

    private static Connection CONN;

    @Override
    public Connection provide() {
        try {
            if (CONN == null) {
                CONN = JdbcConnectionUtil.getConnection(MYSQL_CONN_STR, MYSQL_USER, MYSQL_PASSWORD);
            }
            return CONN;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dispose(Connection connection) {
        try {
            CONN.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
